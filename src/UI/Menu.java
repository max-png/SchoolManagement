package UI;

import Dao.CourseDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import schoolmgmt.domain.Course;
import schoolmgmt.domain.Education;
import schoolmgmt.domain.Teacher;

public class Menu {

    private static Scanner sc = new Scanner(System.in);

    public static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to explore?");
            System.out.println("1. Courses\n2. Educations\n3. Students\n4. Teachers\n0. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    CoursesManager.main();
                    break;
                case 2:
                    EducationsManager.main();
                    break;
                case 3:
                    StudentsManager.main();
                    break;
                case 4:
                    System.out.println("Not supported yet.");
                    break;
//                    teachersManager();
                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        }
    }

}
