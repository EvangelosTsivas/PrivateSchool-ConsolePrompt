package privateschool;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainClass {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("              ****** Welcome. Let's create a private school! ******");
        while (true) {
            System.out.println("\nWhat would you like to do?");
            String[] list = {"Enter students.", "Enter trainers.", "Enter courses.", "Enter assignments.",
                "Continue with premade lists.(Any entities added by user will be added to premade lists)", "Continue with what you entered so far and let Computer fill any empty lists.","Continue with what you entered."};
            Lists.printList(list);
            int choice = enterChoice();
            if (choice == 1) {
                addStudents();
            } else if (choice == 2) {
                addTrainers();
            } else if (choice == 3) {
                addCourses();
            } else if (choice == 4) {
                addAssignments();
            } else if (choice == 5) {
                //premade lists
                //if user added entities before choosing 5 they will be added with the premade lists.
                Lists.getAutoStudentList();
                Lists.getAutoTrainerList();
                Lists.getAutoCoursesList();
                Lists.getAutoAssignmentList();
                break;
            } else if (choice == 6) {
                //exit choices with users inputs
                //any lists null get filled from premade lists.
                if (Lists.getStudentList() == null) {
                    Lists.getAutoStudentList();
                }
                if (Lists.getTrainerList() == null) {
                    Lists.getAutoTrainerList();
                }
                if (Lists.getAssignments() == null) {
                    Lists.getAutoAssignmentList();
                }
                if (Lists.getCourses() == null) {
                    Lists.getAutoCoursesList();
                }
                break;
            }else if(choice == 7){
                //exit choices with user's inputs only
                break;
            }
        }
        //adding students to courses
        addStudentsToCourses();
        //adding trainers to courses
        addTrainersToCourses();
        //adding assignments to courses
        addAssignmentsToCourses();
        //adding assignments to each student based on courses they are enrolled
        assignmentsPerStudent();
        //giving user the option to print which list he wants
        printOptions();
        //printing a list of students and the assignments they need to submit on the same calendar week as the given date
        findAssignmentsPerDate();
       //closing scanner
        input.close();
    }

   
    
    public static int enterChoice() {
        //check input to be in range (1-7)
        int choice;
        do {
            System.out.print("\nEnter the corresponting number (1-6): ");
            while (!input.hasNextInt()) {
                System.out.print("\nPlease enter a valid number: ");
                input.next();
            }
            choice = input.nextInt();
        } while (choice < 1 || choice > 7);
        return choice;
    }

    public static void addStudents() {
        //adding student entity
        System.out.println("Please add students. You can enter more than one at a time.");
        while (true) {
             System.out.print("Enter First name of student or type \"next\" to add another entity: ");
            String firstName = input.next();
            if (firstName.equals("next")) {
                break;
            }
            System.out.print("Enter Last name of student: ");
            String lastName = input.next();   
            //checking date and fees 
            LocalDate dateOfBirth = getDate("Enter date of birthday (dd/MM/yyyy): ");
            int fees = getFees();
            //adding student to list
            Student student = new Student(firstName, lastName, dateOfBirth, fees);
            Lists.addStudents(student);

        }
    }

    public static int getFees() {
        //checking fees input to be >0 and number
        int fees;
        do {
            System.out.print("Enter tuition fees: ");
            while (!input.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                input.next();
            }
            fees = input.nextInt();
        } while (fees < 0);
        return fees;
    }

    public static LocalDate getDate(String message) {
        //checking date format to be dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print(message);
        LocalDate date;
        while (true) {
            String inputDate = input.next();
            try {
                date = LocalDate.parse(inputDate, formatter);
                break;
            } catch (DateTimeParseException dtpe) {
                System.out.print("Please enter date in correct format (dd/MM/yyyy): ");
            }
        }
        return date;
    }

    public static void addTrainers() {
        //adding trainer entity
        System.out.println("Please add trainers. You can enter more than one at a time.");
        while (true) {
            System.out.print("Enter First name of trainer or type \"next\" to add another entity: ");
            String firstName = input.next();
            if (firstName.equals("next")) {
                break;
            }
            System.out.print("Enter Last name of trainer: ");
            String lastName = input.next();
            System.out.print("Please enter the trainer's subject: ");
            String subject = input.next();
            //adding trainer to list
            Trainer trainer = new Trainer(firstName, lastName, subject);
            Lists.addTrainers(trainer);

        }
    }

    public static void addCourses() {
        //adding course entity
        System.out.println("Please add courses. You can enter more than one at a time.");
        while (true) {
            System.out.print("Enter title of course or type \"next\" to add another entity: ");
            String title = input.next();
            if (title.equals("next")) {

                break;
            }
            System.out.print("Enter courses's stream: ");
            String stream = input.next();
            System.out.print("Enter courses's type: ");
            String type = input.next();
            //check dates 
            LocalDate startDate = getDate("Enter starting date (dd/MM/yyyy): ");
            LocalDate endDate = getDate("Enter ending date (dd/MM/yyyy): ");
            //adding course to list
            Course course = new Course(title, stream, type, startDate, endDate);
            Lists.addCourses(course);

        }
    }

    public static void addAssignments() {
        //adding assignment entity
        System.out.println("Please add assignments. You can enter more than one at a time.");
        while (true) {
            System.out.print("Enter title of assignment or type \"next\" to enter another entity: ");
            String title = input.next();
            if (title.equals("next")) {
                break;
            }
            System.out.print("Enter assignment's description: ");
            String description = input.next();
            //check date and mark
            LocalDate subDate = getDate("Enter submission date (dd/MM/yyyy): ");
            int totalMark = getTotMark();
            //adding assignment to list
            Assignment assignment = new Assignment(title, description, subDate, totalMark);
            Lists.addAssignments(assignment);

        }
    }

    public static int getTotMark() {
        //checking total mark to be number and between 0-100 range
        int mark;
        do {
            System.out.print("Enter total mark: ");
            while (!input.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                input.next();
            }
            mark = input.nextInt();
        } while (mark < 0 || mark > 100);
        return mark;
    }

    public static void addStudentsToCourses() {
        System.out.println("\n              ******** Add students to courses ********");
        
        Course c;
        while (true) {
            System.out.println("\nPlease select a course to add students to. \n");
            int i;
            for (i = 0; i < Lists.getCourses().size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), Lists.getCourses().get(i));
            }

            int choice1;
            do {
                System.out.print("\nPlease enter the corresponding number or type 0 to exit: ");                
                while (!input.hasNextInt()) {
                    System.out.print("Please enter a valid number: ");
                    input.next();
                }
                choice1 = input.nextInt();
            } while (choice1 < 0 || choice1 > Lists.getCourses().size());
            if (choice1 == 0) {
                break;
            }
            
            c = Lists.getCourses().get(choice1 - 1);

            System.out.println("\nNow choose which students are in " + c.getTitle() + " course.");
            System.out.println("You can choose more than once .\n");
            for (i = 0; i < Lists.getStudentList().size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), Lists.getStudentList().get(i));
            }
            while (true) {

                int choice2;
                do {

                    System.out.print("\nChoose a student to add to " + c.getTitle() + " or type 0 to return to course selection: ");
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter a valid number: ");
                        input.next();
                    }
                    choice2 = input.nextInt();
                } while (choice2 < 0 || choice2 > Lists.getStudentList().size());
                if (choice2 == 0) {
                    break;
                }
                Student s = Lists.getStudentList().get(choice2 - 1);
                
                if (c.getStudentPerCourse() == null || !c.getStudentPerCourse().contains(s)) {
                    c.addStudentsPerCourse(s);
                    System.out.println("Student " + s.getFirstName() + " " + s.getLastName() + " added to " + c.getTitle() + "!");
                } else {
                    System.out.println("You have entered this student. Choose another one!\n");
                }
            }
        }
    }

    public static void addTrainersToCourses() {

        System.out.println("\n              ******** Add trainers to courses ********");
        
        Course c;
        while (true) {
            System.out.println("\nPlease select a course to add trainers to. \n");

            int i;
            for (i = 0; i < Lists.getCourses().size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), Lists.getCourses().get(i));
            }

            int choice1;
            do {

                System.out.print("\nPlease enter the corresponding number or type 0 to exit: ");
                while (!input.hasNextInt()) {
                    System.out.print("Please enter a valid number: ");
                    input.next();
                }
                choice1 = input.nextInt();
            } while (choice1 < 0 || choice1 > Lists.getCourses().size());
            if (choice1 == 0) {
                break;
            }
            
            c = Lists.getCourses().get(choice1 - 1);

            System.out.println("\nNow choose which trainers are in " + c.getTitle() + " course.");
            System.out.println("You can choose more than once .\n");

            for (i = 0; i < Lists.getTrainerList().size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), Lists.getTrainerList().get(i));
            }
            while (true) {

                int choice2;
                do {
                    System.out.println();
                    System.out.print("\nChoose a trainer to add to " + c.getTitle() + " or type 0 to return to course selection: ");
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter a valid number: ");
                        input.next();
                    }
                    choice2 = input.nextInt();
                } while (choice2 < 0 || choice2 > Lists.getTrainerList().size());
                if (choice2 == 0) {
                    break;
                }
                Trainer t = Lists.getTrainerList().get(choice2 - 1);
                
                if (c.getTrainersPerCourse() == null || !c.getTrainersPerCourse().contains(t)) {
                    c.addTrainersPerCourse(t);
                    System.out.println("Trainer " + t.getFirstName() + " " + t.getLastName() + " added to " + c.getTitle() + "!");
                } else {
                    System.out.println("You have entered this trainer in this course. Choose another one!\n");
                }
            }
        }
    }

    public static void addAssignmentsToCourses() {

        System.out.println("\n              ******** Add assignments to courses ********");
        
        Course c;
        while (true) {
            System.out.println("\nPlease select a course to add assignments to. \n");
            int i;
            for (i = 0; i < Lists.getCourses().size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), Lists.getCourses().get(i));
            }

            int choice1;
            do {
                System.out.print("\nPlease enter the corresponding number or type 0 to exit: ");
                while (!input.hasNextInt()) {
                    System.out.print("Please enter a valid number: ");
                    input.next();
                }
                choice1 = input.nextInt();
            } while (choice1 < 0 || choice1 > Lists.getCourses().size());
            if (choice1 == 0) {
                break;
            }
            
            c = Lists.getCourses().get(choice1 - 1);

            System.out.println("\nNow choose which assignments are in " + c.getTitle() + " course.\n");
            System.out.println("You can choose more than once .");
            for (i = 0; i < Lists.getAssignments().size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), Lists.getAssignments().get(i));
            }
            while (true) {

                int choice2;
                do {
                    System.out.println();
                    System.out.print("\nChoose an assignment to add to " + c.getTitle() + " or type 0 to return to course selection: ");
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter a valid number: ");
                        input.next();
                    }
                    choice2 = input.nextInt();
                } while (choice2 < 0 || choice2 > Lists.getAssignments().size());
                if (choice2 == 0) {
                    break;
                }
                Assignment a = Lists.getAssignments().get(choice2 - 1);
                
                if (c.getAssignmentsPerCourse() == null || !c.getAssignmentsPerCourse().contains(a)) {
                    c.addAssignmentsPerCourse(a);
                    System.out.println("Assignment " + a.getTitle() + " added to " + c.getTitle() + "!");
                } else {
                    System.out.println("You have entered this assignment in this course. Choose another one!\n");                   
                }
            }
        }
    }

    public static void assignmentsPerStudent() {
        //adding the assignments that belong to a course to students that belong to that course
        for (Course c : Lists.getCourses()) {
            if (c.getStudentPerCourse() != null && c.getAssignmentsPerCourse() != null) {
                for (Student s : c.getStudentPerCourse()) {
                    s.getAssignmentsPerStudent().addAll(c.getAssignmentsPerCourse());
                }
            }
        }
    }

    
    public static void printOptions() {
        System.out.println("\n              ***** Congratulations! You created a private school! *****");
        OUTER:
        while (true) {
            System.out.println("\nWhat would you like to do?");
            String[] list = {"Print all the students.", "Print all the trainers.", "Print all the assignments.", "Print all courses.",
                "Print all students per course.", "Print all trainers per course.", "Print all the assignments per course.",
                "Print all the assignments per student.", "Print all students that belong to more than one course.", "Exit printing."};
            Lists.printList(list);
            int choice = enterOption();
            switch (choice) {
                case 1:
                    Lists.printStudents(Lists.getStudentList());
                    break;
                case 2:
                    Lists.printTrainers(Lists.getTrainerList());
                    break;
                case 3:
                    Lists.printAssignments(Lists.getAssignments());
                    break;
                case 4:
                    Lists.printCourses(Lists.getCourses());
                    break;
                case 5:
                    Lists.printStudentsPerCourse();
                    break;
                case 6:
                    Lists.printTrainersPerCourse();
                    break;
                case 7:
                    Lists.printAssignmentsPerCourse();
                    break;
                case 8:
                    Lists.printAssignmentsPerStudent();
                    break;
                case 9:
                    printStudentsInMultipleCourses();
                    break;
                case 10:
                    break OUTER;
                default:
                    break;
            }
        }
    }

    public static int enterOption() {
        int choice;
        do {
            System.out.print("Enter the corresponting number (1-10): ");
            while (!input.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                input.next();
            }
            choice = input.nextInt();
        } while (choice < 1 || choice > 10);
        return choice;
    }
    
    public static void printStudentsInMultipleCourses() {
        //adding all students from all courses to one list
        ArrayList<Student> studentsInMultipleCourses = new ArrayList();
        for (Course course : Lists.getCourses()) {
            if (course.getStudentPerCourse() != null) {
                studentsInMultipleCourses.addAll(course.getStudentPerCourse());
            }
        }
        //creating a Set which has only students appearing multiple times in the list 
        Set<Student> dups = findDuplicates(studentsInMultipleCourses);
        //printing students that belong to multiple courses
        System.out.println("\n              ***** Printing students that belong to multiple courses *****");
        if (dups.isEmpty()) {
            System.out.println("No students belong to multiple courses!");
        }else {
            for (Student dup : dups) { 
                System.out.println("***Student: "+dup.getFirstName()+" "+ dup.getLastName()+" "+" is enrolled in courses : ");
                for(Course c : Lists.getCourses()){
                    if(c.getStudentPerCourse()!=null && c.getStudentPerCourse().contains(dup)){
                        System.out.println(c.getTitle()+" "+ c.getStream()+ " "+ c.getType());
                    }
                }
                
            }
           
        }

    }

    public static Set<Student> findDuplicates(ArrayList<Student> listContainingDuplicates) {
        //checking a list of duplicates and adding to HashSet.       
        final Set<Student> setToReturn = new HashSet();
        final Set<Student> set1 = new HashSet();
        //if a value cannot be added in set1 cause its duplicate  it will be added to setToReturn
        for (Student s : listContainingDuplicates) {
            if (!set1.add(s)) {
                setToReturn.add(s);
            }
        }
        return setToReturn;
    }
    
    public static void findAssignmentsPerDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\nNow lets find out if any students need to submit one or more assignments"
                + " on the same calendar week as the given date");
        LocalDate firstDateOfWeek = getDate("\nPlease enter a date (dd/MM/yyyy): ");

        while (firstDateOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstDateOfWeek = firstDateOfWeek.minusDays(1);
        }
        System.out.println("Monday: " + firstDateOfWeek.format(formatter));
        LocalDate lastDateOfWeek = firstDateOfWeek.plusDays(4);
        System.out.println("Friday: " + lastDateOfWeek.format(formatter));
        System.out.println("\n              ***** Printing students and assignments they need to submit *****");
        for (Student student : Lists.getStudentList()) {
            for (Assignment a : student.getAssignmentsPerStudent()) {
                //minusDays(1) and PlusDays(1) are used to include Monday and Friday 
                if (a.getSubDateTime().isAfter(firstDateOfWeek.minusDays(1)) && a.getSubDateTime().isBefore(lastDateOfWeek.plusDays(1))) {
                    System.out.println("***"+student.getFirstName()+" "+student.getLastName()+"***");
                    String message = String.format("Title: %-15s Description: %-10s Submission date: %-15s", a.getTitle(),a.getDescription(),a.getSubDateTime().format(formatter));
                    System.out.println(message);
                }
            }
        }
    }
}
