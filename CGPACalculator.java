import java.util.ArrayList;
import java.util.List;


public class CGPACalculator {
    private List<Semester> semesters;
    private String gradingScale;

    public CGPACalculator() {
        this.semesters = new ArrayList<>();
        this.gradingScale = "4.0"; // Default scale
    }

    public void addSemester(Semester semester) {
        semesters.add(semester);
    }

    public double calculateCGPA() {
        double totalPoints = 0;
        double totalCredits = 0;
        
        for (Semester semester : semesters) {
            for (Course course : semester.getCourses()) {
                if (course.isCountedInGPA()) {
                    totalPoints += course.getCreditHours() * course.getGradePoint();
                    totalCredits += course.getCreditHours();
                }
            }
        }
        
        return totalCredits > 0 ? totalPoints / totalCredits : 0;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Comprehensive GPA Report\n");
        report.append("Grading Scale: ").append(gradingScale).append("\n\n");
        
        for (Semester semester : semesters) {
            report.append(semester.toString()).append("\n");
        }
        
        report.append(String.format("Overall CGPA: %.2f", calculateCGPA()));
        return report.toString();
    }

    // Getters and setters
    public List<Semester> getSemesters() { return semesters; }
    public String getGradingScale() { return gradingScale; }
    public void setGradingScale(String gradingScale) { this.gradingScale = gradingScale; }
}