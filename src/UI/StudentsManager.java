package UI;

import Dao.StudentDao;
import static UI.EducationsManager.eDao;
import java.util.Scanner;
import schoolmgmt.domain.Course;
import schoolmgmt.domain.Education;
import schoolmgmt.domain.Student;

public class StudentsManager {

    private static Scanner sc = new Scanner(System.in);
    public static StudentDao sDao = new StudentDao();

    public static void main() {
        System.out.println("\n** Students **");
        System.out.println("1. Add\n2. Move\n3. Show specific\n4. Show all\n5. Remove\n0. Back\n");
        boolean loop = true;
        while (loop) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    show();
                    break;
                case 4:
                    showAll();
                    break;
                case 5:
                    remove();
                    break;
                case 0:
                    loop = false;
                    Menu.mainMenu();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }

    private static void add() {
        sc.nextLine();
        Student s = new Student();
        System.out.println("Name: ");
        s.setStudentName(sc.nextLine());

        System.out.println("Add student to which education: ");

        Education edu = EducationsManager.selectEducation();
        edu.addStudent(s);
        try {
            EducationsManager.eDao.update(edu);
            System.out.println(s.getStudentName() + " added.");

        } catch (Exception e) {
            System.out.println("Error while adding new education: " + e);
        }

    }

    private static void update() {
        System.out.println("Update which student?");
        showAll();

        Long id = sc.nextLong();
        Student s = sDao.getById(id);
        if (s == null) {
            System.out.println("No such ID");
            update();
        } else {
            sc.nextLine();
            System.out.println("Updating student: " + s.getStudentName());
            System.out.println("Select new education: ");

            Education oldEdu = s.getEducation();

            Education newEdu = EducationsManager.selectEducation();

            s.setEducation(newEdu);

            newEdu.addStudent(s);

            try {
                oldEdu.removeStudent(s);
                eDao.update(oldEdu);
                sDao.update(s);
                eDao.update(newEdu);

                System.out.println("Moved " + s.getStudentName() + " to " + newEdu.getEducationName());
            } catch (Exception e) {
                System.out.println("Error when updating education: " + e);
            } finally {
                main();
            }
        }

    }

    private static void show() {
        showAll();
        System.out.println("\nShow student details: ");
        Long choice = sc.nextLong();
        Student show = sDao.getById(choice);
        if (show.getEducation() == null) {
            System.out.println("Is not enrolled.");
        } else {
            System.out.println(show.getStudentName() + " is enrolled to " + show.getEducation().getEducationName());
            for (Course c : show.getEducation().getCourses()) {
                System.out.println(c.getCourseName());
            }
        }
    }

    private static void showAll() {
        System.out.printf("%n%s%30s%35s%n", "ID", "Name", "Education");
        for (Student s : sDao.getAll()) {
            if (s.getEducation() == null) {
                System.out.printf("%s%30s%40s%n", s.getId(), s.getStudentName(), "No education");
            } else {
                System.out.printf("%s%30s%40s%n", s.getId(), s.getStudentName(), s.getEducation().getEducationName());
            }
        }
    }

    private static void remove() {
        sc.nextLine();
        showAll();
        System.out.println("Remove which student by ID?");
        Long id = sc.nextLong();

        System.out.println("Removing: " + sDao.getById(id).getStudentName());
        sDao.removeById(id);

        main();
    }

}
