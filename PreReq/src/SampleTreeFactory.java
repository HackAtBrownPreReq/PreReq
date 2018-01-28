
import java.util.ArrayList;

import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;

/**
 * Creates "Sample" trees, e.g. to be used in demonstrations.
 * 
 * @author Udo Borkowski (ub@abego.org)
 */
public class SampleTreeFactory {
	static DefaultTreeForTreeLayout<TextInBox> tree;
	
	static ArrayList<TextInBox> allNames;
	
	public static void associatePrereqs(Course inputCourse) {
		Course[] preReqs = inputCourse.getPrereqs();

		if (preReqs == null) {
			return;
		}
		
		for (Course c : preReqs) {
			for (int i = 0; i < allNames.size(); i++) {
				if (allNames.get(i).text.equals(c.name)) {
					for (int a = 0; a < allNames.size() ; a++) {
						if (allNames.get(a).text.equals(inputCourse.getName())) {
							tree.addChild(allNames.get(a), allNames.get(i));
						}
					}
				}
			}
			allNames.add(0, new TextInBox(c.getName(), 300, 30));
			
			for (int a = 0; a < allNames.size() ; a++) {
				if (allNames.get(a).text.equals(inputCourse.getName())) {
					tree.addChild(allNames.get(a), allNames.get(0));
				}
			}			
			associatePrereqs(c);
		}
		
	}
	/**
	 * @return a "Sample" tree with {@link TextInBox} items as nodes.
	 */
	public static TreeForTreeLayout<TextInBox> createSampleTree(Course inputCourse) {
		
		allNames = new ArrayList<TextInBox>();
		Course[] preReqs = inputCourse.getPrereqs();
		

		allNames.add(new TextInBox(inputCourse.name, 300, 30));
		
		tree = new DefaultTreeForTreeLayout<TextInBox>(allNames.get(0));

		associatePrereqs(inputCourse);
		
		/*
		TextInBox root = new TextInBox("root", 40, 20);
		TextInBox n1 = new TextInBox("n1", 30, 20);
		TextInBox n1_1 = new TextInBox("n1.1\n(first node)", 80, 36);
		TextInBox n1_2 = new TextInBox("n1.2", 40, 20);
		TextInBox n1_3 = new TextInBox("n1.3\n(last node)", 80, 36);
		TextInBox n2 = new TextInBox("n2", 30, 20);
		TextInBox n2_1 = new TextInBox("n2", 30, 20);

		tree.addChild(root, n1);
		tree.addChild(n1, n1_1);
		tree.addChild(n1, n1_2);
		tree.addChild(n1, n1_3);
		tree.addChild(root, n2);
		tree.addChild(n2, n2_1);
		*/
		return tree;
	}
}