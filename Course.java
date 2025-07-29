public class Course {
    private String name;
    private double creditHours;
    private String grade;
    private double gradePoint;
    private boolean countedInGPA;

    public Course(String name, double creditHours, String grade, boolean countedInGPA) {
        this.name = name;
        this.creditHours = creditHours;
        this.grade = grade;
        this.countedInGPA = countedInGPA;
        this.gradePoint = convertGradeToPoint(grade);
    }

    private double convertGradeToPoint(String grade) {
        // Default 4.0 scale - can be made configurable
        switch (grade.toUpperCase()) {
            case "A+": case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return 0.0; // For pass/fail or other non-grade courses
        }
    }

    // Getters and setters
    public String getName() { return name; }
    public double getCreditHours() { return creditHours; }
    public String getGrade() { return grade; }
    public double getGradePoint() { return gradePoint; }
    public boolean isCountedInGPA() { return countedInGPA; }

    public void setName(String name) { this.name = name; }
    public void setCreditHours(double creditHours) { this.creditHours = creditHours; }
    public void setGrade(String grade) { 
        this.grade = grade; 
        this.gradePoint = convertGradeToPoint(grade);
    }
    public void setCountedInGPA(boolean countedInGPA) { this.countedInGPA = countedInGPA; }

    @Override
    public String toString() {
        return String.format("%-20s %5.2f %5s %5.2f %s", 
            name, creditHours, grade, gradePoint, 
            countedInGPA ? "" : "(Not counted in GPA)");
    }
}