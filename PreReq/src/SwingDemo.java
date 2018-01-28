
import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;

import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;

/**
 * Demonstrates how to use the {@link TreeLayout} to render a tree in a Swing
 * application.
 * <p>
 * Intentionally the sample code is kept simple. I.e. it does not include stuff
 * like anti-aliasing and other stuff one would add to make the output look
 * nice.
 * <p>
 * Screenshot:
 * <p>
 * <img src="doc-files/swingdemo.png" alt="A tree rendered using Swing">
 * 
 * @author Udo Borkowski (ub@abego.org)
 */
public class SwingDemo {

	private static void showInDialog(JComponent panel) {
		JDialog dialog = new JDialog();
		Container contentPane = dialog.getContentPane();
		((JComponent) contentPane).setBorder(BorderFactory.createEmptyBorder(
				10, 10, 10, 10));
		contentPane.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private static TreeForTreeLayout<TextInBox> getSampleTree(String courseCode) throws IOException {
		TreeForTreeLayout<TextInBox> tree;
		
		Course MATH0100 = new Course("Introductory Calculus, Part II", "MATH0100", 3.71, 1.64, new Course[0]);
		Course MATH0180 = new Course("Intermediate Calculus", "MATH0180", 3.87, 1.15, new Course[]{MATH0100});
		Course MATH1010 = new Course("Real Analysis", "MATH1010", 7.1, 2, new Course[]{MATH0180});
		Course MATH0350 = new Course("Advanced Calculus", "MATH0350", -1, -1, new Course[0]);
		
		Course APMA1650 = new Course("Statistical Inference I", "APMA1650", -1, -1, new Course[]{MATH0180, MATH0350});
		Course APMA1710 = new Course("Information Theory", "APMA1710", -1, -1, new Course[] {APMA1650});
		Course APMA1940X = new Course("Information Theory and Coding Theory", "APMA1940X", -1, -1, new Course[]{APMA1710});
		
		tree = SampleTreeFactory.createSampleTree(APMA1940X);
		
		return tree;
	}

	/**
	 * Shows a dialog with a tree in a layout created by {@link TreeLayout},
	 * using the Swing component {@link TextInBoxTreePane}.
	 * 
	 * @param args args[0]: treeName (default="")
	 * @throws IOException 
	 */
	
	
	public static void main(String[] args) throws IOException {
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ColorTest.createAndShowGUI();
            }
        });
        
		CoursesCreator.listOfCourses = new ArrayList<Course>();
		CoursesCreator.doEverything();
	    
		//System.out.println(CoursesCreator.readXLSXFile("MATH1010"));
		
		
		// get the sample tree
		String treeName = (args.length > 0) ? args[0] : "";
		TreeForTreeLayout<TextInBox> tree = getSampleTree(treeName);
				
		// setup the tree layout configuration
		double gapBetweenLevels = 50;
		double gapBetweenNodes = 10;
		DefaultConfiguration<TextInBox> configuration = new DefaultConfiguration<TextInBox>(
				gapBetweenLevels, gapBetweenNodes);

		// create the NodeExtentProvider for TextInBox nodes
		TextInBoxNodeExtentProvider nodeExtentProvider = new TextInBoxNodeExtentProvider();

		// create the layout
		TreeLayout<TextInBox> treeLayout = new TreeLayout<TextInBox>(tree,
				nodeExtentProvider, configuration);

		// Create a panel that draws the nodes and edges and show the panel
		TextInBoxTreePane panel = new TextInBoxTreePane(treeLayout);
		showInDialog(panel);
	} 
}

