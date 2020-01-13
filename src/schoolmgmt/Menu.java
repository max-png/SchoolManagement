package schoolmgmt;

import Dao.CourseDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import schoolmgmt.domain.Course;
import schoolmgmt.domain.Education;
import schoolmgmt.domain.Teacher;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static CourseDao dao = new CourseDao();

    public static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("What would you like to explore?");
            System.out.println("1. Courses\n2. Educations\n3. Students\n4. Teachers\n0. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    coursesManager();
                    break;
                case 2:
                    educationsManager();
                    break;
                case 3:
                    studentsManager();
                    break;
                case 4:
                    teachersManager();
                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        }
    }

    private static void addCourse() {
        String courseName;
        Education education;
        List<Teacher> teacherList;

        System.out.println("Enter course name: ");
        courseName = sc.nextLine();
        System.out.println("Enter education: ");
        //TODO Change from course to education
        List<Course> courses = dao.getAll();

        courses.forEach((nextCourse) -> {
            System.out.println(nextCourse.getId() + ": " + nextCourse.getCourseName());
        });

        System.out.println("0: new education");
        int choice = sc.nextInt();

        courses.forEach((nextCourse) -> {
            if (nextCourse.getId() == choice) {
                Course selectedCourse = nextCourse;
                System.out.println("Selected course " + nextCourse.getId() + ": " + nextCourse.getCourseName());
                final Course course = selectedCourse;
            }
        });
    }

    private static void coursesManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void educationsManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void studentsManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void teachersManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
