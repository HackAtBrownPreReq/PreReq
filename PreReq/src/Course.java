
public class Course {
	String name;
	String code;
	double hours;
	double rating;
	Course[] prereqs;
	boolean isReal = false;
	
	public Course(String courseName, String courseCode, double courseHours, double courseRating, Course[] coursePrereqs) {
		name = courseName;
		code = courseCode;
		hours = courseHours;
		rating = courseRating;
		prereqs = coursePrereqs;
		isReal = true;
	}
	
	public Course(String fakeCode) {
		isReal = false;
		code = fakeCode;
	}
	
	public Course[] getPrereqs() {
		return prereqs;
	}
	
	public String getName() {
		return name;
	}
	
}
