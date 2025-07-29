import java.util.ArrayList;
import java.util.List;

public class Semester {
    private String name;
    private List<Course> courses;

    public Semester(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public double calculateSGPA() {
        double totalPoints = 0;
        double totalCredits = 0;
        
        for (Course course : courses) {
            if (course.isCountedInGPA()) {
                totalPoints += course.getCreditHours() * course.getGradePoint();
                totalCredits += course.getCreditHours();
            }
        }
        
        return totalCredits > 0 ? totalPoints / totalCredits : 0;
    }

    // Getters and setters
    public String getName() { return name; }
    public List<Course> getCourses() { return courses; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Semester: ").append(name).append("\n");
        for (Course course : courses) {
            sb.append(course.toString()).append("\n");
        }
        sb.append(String.format("SGPA: %.2f", calculateSGPA())).append("\n");
        return sb.toString();
    }
}