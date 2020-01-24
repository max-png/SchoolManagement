package UI;

import Dao.CourseDao;
import Dao.Dao;
import Dao.EducationDao;
import java.util.List;
import java.util.Scanner;
import schoolmgmt.domain.*;

public class EducationsManager {

    private static Scanner sc = new Scanner(System.in);
    public static EducationDao eDao = new EducationDao();

    public static void main() {
        System.out.println("\n** Educations **");
        System.out.println("1. Add\n2. Update\n3. Show specific\n4. Show all\n5. Remove\n0. Back\n");
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
                    showAllDetailed();
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

    public static Education selectEducation() {
        List<Education> educations = EducationsManager.eDao.getAll();
        for (Education edus : educations) {
            System.out.println(edus.getId() + " " + edus.getEducationName());
        }

        Long id = sc.nextLong();
        Education edu = EducationsManager.eDao.getById(id);
        return edu;
    }

    private static void add() {
        sc.nextLine();
        System.out.println("Add new: ");
        Education t = new Education();
        System.out.println("Name: ");
        t.setEducationName(sc.nextLine());

        try {
            eDao.add(t);
            System.out.println(t.getEducationName() + " added!");
        } catch (Exception e) {
            System.out.println("Error while adding new education: " + e);
        }

    }

    private static void update() {
        System.out.println("Update which education?");
        showAllDetailed();

        Long id = sc.nextLong();
        Education edu = eDao.getById(id);
        
        if (edu == null) {
            System.out.println("No such ID");
            update();
        } else {
            sc.nextLine();
            System.out.println("Updating education: " + edu.getEducationName());
            System.out.println("Set new name: ");
            String oldName = edu.getEducationName();
            edu.setEducationName(sc.nextLine());
            System.out.println("Changed name from " + oldName + " to " + edu.getEducationName());
            try {
                eDao.update(edu);
            } catch (Exception e) {
                System.out.println("Error when updating education: " + e);
            } finally {
                main();
            }
        }

    }

    private static void show() {
        showAll();
        System.out.println("Show education details: ");
        Long choice = sc.nextLong();
        Education show = eDao.getById(choice);
        List<Teacher> teachers;

        System.out.println("\nCourses in " + show.getEducationName() + ":");
        for (Course temp : show.getCourses()) {
            teachers = temp.getTeachers();

            System.out.printf("%n%n %s", temp.getCourseName());

            if (teachers.isEmpty()) {
                System.out.printf("%s", " has no assigned teachers.");
                System.out.printf("%n%s%n", "Students:");
                for (Student tmpStudent : show.getStudents()) {
                    System.out.printf("%s%n", tmpStudent.getStudentName());
                }
            } else {
                System.out.printf("%s%n", ", Teacher(s):");
                for (Teacher nextTeacher : teachers) {
                    System.out.printf("%s%n", nextTeacher.getTeacherName());
                }
                System.out.printf("%n%s%n", "Students:");
                for (Student tmpStudent : show.getStudents()) {
                    System.out.printf("%s%n", tmpStudent.getStudentName());
                }

            }
        }
    }

    private static void showAll() {
        System.out.println("\nAll educations:");
        List<Education> educations = eDao.getAll();
        for (Education e : educations) {
            System.out.println(e.getId() + ". " + e.getEducationName());
        }
    }

    private static void showAllDetailed() {
        System.out.println("\nAll educations:");
        List<Education> educations = eDao.getAll();
        for (Education e : educations) {
            System.out.println("\n" + e.getId() + ". " + e.getEducationName() + " (" + e.getStudents().size() + " students)");
            for (Course c : e.getCourses()) {
                System.out.printf("%s%n", c.getCourseName());
            }
        }
    }

    private static void remove() {
        sc.nextLine();
        showAll();
        System.out.println("\nRelated courses will also be deleted.");
        System.out.println("Remove which education by ID?");
        Long id = sc.nextLong();
        System.out.println("Removing: " + eDao.getById(id).getEducationName());
        eDao.removeById(id);
        main();
    }

}
