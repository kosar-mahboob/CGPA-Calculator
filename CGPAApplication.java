import java.util.Scanner;

public class CGPAApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static CGPACalculator calculator = new CGPACalculator();

    public static void main(String[] args) {
        System.out.println("Comprehensive CGPA Calculator");
        
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt(
            );
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addSemester();
                    break;
                case 2:
                    addCourseToSemester();
                    break;
                case 3:
                    viewReport();
                    break;
                case 4:
                    whatIfAnalysis();
                    break;

                case 5:
                    setGradingScale();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        System.out.println("Thank you for using the CGPA Calculator!");
    }

    private static void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Semester");
        System.out.println("2. Add Course to Semester");
        System.out.println("3. View GPA Report");
        System.out.println("4. What-If Analysis");
        System.out.println("5. Set Grading Scale");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addSemester() {
        System.out.print("Enter semester name (e.g., Fall 2023): ");
        String name = scanner.nextLine();
        calculator.addSemester(new Semester(name));
        System.out.println("Semester added successfully!");
    }
    private static void addCourseToSemester() {
        if (calculator.getSemesters().isEmpty()) {
            System.out.println("No semesters available. Please add a semester first.");
            return;
        }

        boolean addingCourses = true;
        while (addingCourses) {
            System.out.println("\nAvailable Semesters:");
            for (int i = 0; i < calculator.getSemesters().size(); i++) {
                System.out.println((i + 1) + ". " + calculator.getSemesters().get(i).getName());
            }

            System.out.print("Select semester (or 0 to return to main menu): ");
            int semesterIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (semesterIndex == -1) {
                addingCourses = false;
                continue;
            }

            if (semesterIndex < -1 || semesterIndex >= calculator.getSemesters().size()) {
                System.out.println("Invalid semester selection.");
                continue;
            }

            // Course details input
            System.out.print("Enter course name: ");
            String name = scanner.nextLine();

            System.out.print("Enter credit hours: ");
            double creditHours = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter grade (e.g., A, B+, C-): ");
            String grade = scanner.nextLine();

            System.out.print("Count in GPA? (Y/N): ");
            boolean countedInGPA = scanner.nextLine().equalsIgnoreCase("Y");

            Course course = new Course(name, creditHours, grade, countedInGPA);
            calculator.getSemesters().get(semesterIndex).addCourse(course);
            System.out.println("Course added successfully!");

            // Prompt to add another course
            System.out.print("\nAdd another course? (Y/N): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                addingCourses = false;
            }
        }
    }

    private static void viewReport() {
        System.out.println("\n" + calculator.generateReport());
    }

    private static void whatIfAnalysis() {
        if (calculator.getSemesters().isEmpty()) {
            System.out.println("No semesters available. Please add a semester first.");
            return;
        }
        
        // Create a copy for what-if scenario
        CGPACalculator scenarioCalculator = new CGPACalculator();
        for (Semester semester : calculator.getSemesters()) {
            Semester copy = new Semester(semester.getName());
            for (Course course : semester.getCourses()) {
                copy.addCourse(new Course(course.getName(), course.getCreditHours(), 
                    course.getGrade(), course.isCountedInGPA()));
            }
            scenarioCalculator.addSemester(copy);
        }
        
        // Add a new hypothetical semester
        System.out.print("Enter name for hypothetical semester: ");
        String name = scanner.nextLine();
        Semester hypothetical = new Semester(name);
        scenarioCalculator.addSemester(hypothetical);
        
        boolean addingCourses = true;
        while (addingCourses) {
            System.out.print("Add hypothetical course? (Y/N): ");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) {
                addingCourses = false;
                continue;
            }
            
            System.out.print("Enter course name: ");
            String courseName = scanner.nextLine();
            
            System.out.print("Enter credit hours: ");
            double creditHours = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter expected grade (e.g., A, B+, C-): ");
            String grade = scanner.nextLine();
            
            Course course = new Course(courseName, creditHours, grade, true);
            hypothetical.addCourse(course);
        }
        
        System.out.println("\nWhat-If Scenario Results:");
        System.out.println("Current CGPA: " + String.format("%.2f", calculator.calculateCGPA()));
        System.out.println("Projected CGPA after hypothetical semester: " + 
            String.format("%.2f", scenarioCalculator.calculateCGPA()));
    }

    private static void setGradingScale() {
        System.out.println("Current grading scale: " + calculator.getGradingScale());
        System.out.print("Enter new grading scale (e.g., 4.0, 5.0, 10.0): ");
        String scale = scanner.nextLine();
        calculator.setGradingScale(scale);
        System.out.println("Grading scale updated.");
    }
}

