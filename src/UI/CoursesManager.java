package UI;

import Dao.CourseDao;
import java.util.List;
import java.util.Scanner;
import schoolmgmt.domain.Course;
import schoolmgmt.domain.Education;
import schoolmgmt.domain.Student;
import schoolmgmt.domain.Teacher;

public class CoursesManager {

    private static Scanner sc = new Scanner(System.in);
    public static CourseDao cDao = new CourseDao();

    public static void main() {
        System.out.println("\n** Courses **");
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
        Course c = new Course();
        System.out.println("Name: ");
        c.setCourseName(sc.nextLine());
        System.out.println("Add course to which education?");
        List<Education> educations = EducationsManager.eDao.getAll();
        for (Education edus : educations) {
            System.out.println(edus.getId() + " " + edus.getEducationName());
        }

        Long id = sc.nextLong();
        Education edu = EducationsManager.eDao.getById(id);

        c.setEducation(edu);
        edu.addCourse(c);

        try {
            cDao.add(c);
            EducationsManager.eDao.update(edu);

            System.out.println(c.getCourseName() + " added to " + c.getEducation().getEducationName());
        } catch (Exception e) {
            System.out.println("Error while adding new course: " + e);
        }

    }

    private static void update() {
        System.out.println("Update which course?");
        showAll();

        Long id = sc.nextLong();
        Course c = cDao.getById(id);

        if (c == null) {
            System.out.println("No such ID");
            update();
        } else {
            sc.nextLine();
            System.out.println("Updating course: " + c.getCourseName());
            System.out.println("Set new name: ");
            String oldName = c.getCourseName();

            c.setCourseName(sc.nextLine());

            System.out.println("Changed name from " + oldName + " to " + c.getCourseName());
            try {
                cDao.update(c);
            } catch (Exception e) {
                System.out.println("Error when updating course: " + e);
            } finally {
                main();
            }
        }

    }

    private static void show() {
        showAll();
        System.out.println("Show course details: ");
        Long choice = sc.nextLong();

        Course show = cDao.getById(choice);
        List<Student> students = show.getEducation().getStudents();
        System.out.println();
        System.out.println(show.getCourseName() + " [" + show.getEducation().getEducationName() + "]");
        System.out.println("Teacher(s):");

        for (Teacher teachers : show.getTeachers()) {
            System.out.printf("%28s%n", teachers.getTeacherName());
        }

        System.out.printf("%n%s", "Students:");
        for (Student studs : students) {
            System.out.printf("%n%28s", studs.getStudentName());
        }

    }

    private static void showAll() {

        List<Course> courses = cDao.getAll();
        System.out.printf("%n%2s%35s%55s%n%n", "ID", "Name", "Education");
        for (Course temp : courses) {
            if (temp.getEducation() == null) {
                System.out.printf("%2s%35s%55s%n", temp.getId(), temp.getCourseName(), "Educationless");
            } else {
                System.out.printf("%2s%35s%55s%n", temp.getId(), temp.getCourseName(), temp.getEducation().getEducationName());
            }
        }
    }

    private static void remove() {
        sc.nextLine();
        showAll();
        System.out.println("Remove which course by ID?");
        Long id = sc.nextLong();

        System.out.println("Removing: " + cDao.getById(id).getCourseName());
        cDao.removeById(id);

        main();
    }

}
