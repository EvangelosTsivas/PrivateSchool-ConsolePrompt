package privateschool;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lists {

    private static ArrayList<Student> studentsList;
    private static ArrayList<Trainer> trainersList;
    private static ArrayList<Assignment> assignmentsList;
    private static ArrayList<Course> coursesList;

    public static void addStudents(Student student) {
        if (studentsList == null) {
            studentsList = new ArrayList();
        }
        studentsList.add(student);
    }

    public static ArrayList<Student> getStudentList() {
        return studentsList;
    }

    public static void addTrainers(Trainer trainer) {
        if (trainersList == null) {
            trainersList = new ArrayList();
        }
        trainersList.add(trainer);
    }

    public static ArrayList<Trainer> getTrainerList() {
        return trainersList;
    }

    public static ArrayList<Assignment> getAssignments() {
        return assignmentsList;
    }

    public static void addAssignments(Assignment assignment) {
        if (assignmentsList == null) {
            assignmentsList = new ArrayList();
        }
        assignmentsList.add(assignment);
    }

    public static void addCourses(Course course) {
        if (coursesList == null) {
            coursesList = new ArrayList();
        }
        coursesList.add(course);
    }

    public static ArrayList<Course> getCourses() {
        return coursesList;
    }

    public static void printList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), list[i]);
        }
    }

    public static void printStudents(ArrayList<Student> students) {
        System.out.println("\n              **** Students List ****");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void printTrainers(ArrayList<Trainer> trainers) {
        System.out.println("\n              **** Trainers List ****");
        for (Trainer trainer : trainers) {
            System.out.println(trainer);
        }
    }

    public static void printAssignments(ArrayList<Assignment> assignments) {
        System.out.println("\n              **** Assignments List ****");
        for (Assignment assignment : assignments) {
            System.out.println(assignment);
        }
    }

    public static void printCourses(ArrayList<Course> courses) {
        System.out.println("\n              **** Courses List ****");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public static void printStudentsPerCourse() {
        System.out.println("\n              ***** Printing Students Per Course *****");       
        for (Course course : Lists.getCourses()) {
            System.out.println("*** Course: " + course.getTitle() + " " + course.getStream() + " " + course.getType() + " ***");
            if(course.getStudentPerCourse()==null){
                        System.out.println("No students are enrolled in this course!");
                    }
            else if (course.getStudentPerCourse() != null) {
                for (Student student : course.getStudentPerCourse()) {                    
                       System.out.println("Student: "+ student);       
                }
            }
        }
    }

    public static void printTrainersPerCourse() {
        System.out.println("\n              ***** Printing Trainers Per Course *****");
        for (Course course : Lists.getCourses()) {
            System.out.println("*** Course: " + course.getTitle() + " " + course.getStream() + " " + course.getType() + " ***");
            if(course.getTrainersPerCourse()==null){
                        System.out.println("No trainers belong in this course!");
            }
            else if (course.getTrainersPerCourse() != null) {
                for (Trainer trainer : course.getTrainersPerCourse()) {
                        System.out.println("Trainer: "+ trainer);        
                }
            }
        }
    }

    public static void printAssignmentsPerCourse() {
        System.out.println("\n              ***** Printing Assignments Per Course *****");
        for (Course course : Lists.getCourses()) {
            System.out.println("*** Course: " + course.getTitle() + " " + course.getStream() + " " + course.getType() + " ***");
            if(course.getAssignmentsPerCourse()==null){
                System.out.println("No assignments in this course!");
            }
            else if (course.getAssignmentsPerCourse() != null) {
                for (Assignment assignment : course.getAssignmentsPerCourse()) {
                    System.out.println("Assignment: " + assignment);
                }
            }
        }
    }
    
    public static void printAssignmentsPerStudent() {
        System.out.println("\n              ***** Printing Assignments Per Student *****");
        for (Student s : Lists.getStudentList()) {
            System.out.printf("***Student: %s %s***\n", s.getFirstName(), s.getLastName());
            for (Assignment a : s.getAssignmentsPerStudent()) {          
                    System.out.println("Assignment: "+a);      
            }
        }
    }

    public static ArrayList<Student> getAutoStudentList() {
        
        Student s1 = new Student("Tom", "Bombandil", LocalDate.of(1989, 5, 23), 2200);
        Student s2 = new Student("Jack", "Jefferson", LocalDate.of(1990, 9, 5), 2500);
        Student s3 = new Student("Mary", "Longbottom", LocalDate.of(1995, 6, 6), 1000);
        Student s4 = new Student("Ann", "Perish", LocalDate.of(1992, 3, 25), 3000);
        addStudents(s1);
        addStudents(s2);
        addStudents(s3);
        addStudents(s4);

        return studentsList;
    }

    public static ArrayList<Trainer> getAutoTrainerList() {

        Trainer t1 = new Trainer("Michael", "Fern", "Java");
        Trainer t2 = new Trainer("John", "Johnson", "C#");
        Trainer t3 = new Trainer("Rose", "Foggel", "Javascript");
        Trainer t4 = new Trainer("Dianna", "Rosewell", "HTML");
        addTrainers(t1);
        addTrainers(t2);
        addTrainers(t3);
        addTrainers(t4);

        return trainersList;
    }

    public static ArrayList<Course> getAutoCoursesList() {

        Course c1 = new Course("CB8", "Java", "Full-time", LocalDate.of(2020, 5, 4), LocalDate.of(2020, 8, 3));
        Course c2 = new Course("CB9", "C#", "Part-time", LocalDate.of(2020, 5, 15), LocalDate.of(2020, 11, 12));
        Course c3 = new Course("CB10", "C#", "Full-time", LocalDate.of(2020, 5, 10), LocalDate.of(2020, 8, 10));
        Course c4 = new Course("CB11", "Java", "Part-time", LocalDate.of(2020, 6, 1), LocalDate.of(2020, 12, 4));
        addCourses(c1);
        addCourses(c2);
        addCourses(c3);
        addCourses(c4);

        return coursesList;
    }

    public static ArrayList<Assignment> getAutoAssignmentList() {

        Assignment a1 = new Assignment("Project1", "Java", LocalDate.of(2020, 6, 1), 70);
        Assignment a2 = new Assignment("Project2", "C#", LocalDate.of(2020, 7, 15), 67);
        Assignment a3 = new Assignment("Assignment1", "Java", LocalDate.of(2020, 9, 4), 90);
        Assignment a4 = new Assignment("Assignment2", "C#", LocalDate.of(2020, 10, 15), 50);
        addAssignments(a1);
        addAssignments(a2);
        addAssignments(a3);
        addAssignments(a4);

        return assignmentsList;
    }

}
