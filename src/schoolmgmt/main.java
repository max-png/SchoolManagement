package schoolmgmt;

import Dao.*;
import java.util.List;
import schoolmgmt.domain.*;

public class main {

    public static void main(String[] args) {

        Course c1 = new Course();
        c1.setCourseName("Ostkurs");
        Course c2 = new Course();
        c2.setCourseName("Jannekurs");

        CourseDao cDao = new CourseDao();

        Student ragnar = new Student();
        ragnar.setStudentName("Ragnar");

        Teacher t1 = new Teacher();
        t1.setTeacherName("Pappa Jonas");

        Education e1 = new Education();
        Education e2 = new Education();

        e2.setEducationName("Blandfärskurs");

        c1.addTeacher(t1);

        e1.setEducationName("Ost- och yougautbildning");

        e1.addStudent(ragnar);

        e1.addCourse(c1);
        e1.addCourse(c2);
        e2.addCourse(c2);

        cDao.add(c1);

        c2.setCourseName("Kalaskurs");
        cDao.update(c2);

        c1.setCourseName("Saftkurs");
        cDao.update(c1);

        List<Course> courses = cDao.getAll();

        for (Course nextCourse : courses) {
            System.out.println(nextCourse.getCourseName() + " belongs to education " + nextCourse.getEducation().getEducationName());
        }

//        Menu.mainMenu();
//        Education musicEducation = new Education();
//        musicEducation.setEducationName("Musical Education");
//
//        Education languageEdu = new Education();
//        languageEdu.setEducationName("Language Education");
//
//        Teacher teacher = new Teacher();
//        teacher.setTeacherName("Bertil Fält");
//
//        Teacher teacher2 = new Teacher();
//        teacher2.setTeacherName("Johan Sundberg");
//
//        Course course = new Course();
//        course.setCourseName("Guitar for Beginners");
//
//        Course course1 = new Course();
//        course1.setCourseName("Guitar: Advanced Level");
//
//        Course course2 = new Course();
//        course2.setCourseName("Swedish for Beginners");
//
//        teacher.addCourse(course);
//        teacher.addCourse(course1);
//        teacher2.addCourse(course1);
//        teacher2.addCourse(course2);
//
//        musicEducation.addCourse(course);
//        musicEducation.addCourse(course1);
//
//        languageEdu.addCourse(course2);
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCHOOL_PU");
//        EntityManager em = emf.createEntityManager();
//
////        em.getTransaction().begin();
////        em.remove(musicEducation);
////        em.getTransaction().commit();
////        
//        Student max = new Student();
//        Student mack = new Student();
//        mack.setStudentName("Markus Mörk");
//        mack.setEducation(languageEdu);
//
//        max.setStudentName("Max Rune");
//        max.setEducation(musicEducation);
//
//        em.getTransaction().begin();
//
//        em.persist(musicEducation);
//        em.persist(max);
//        em.persist(mack);
//        em.persist(languageEdu);
//        em.getTransaction().commit();
//
//        Query q = em.createQuery("Select s From Student s where s.studentName = :name");
//        q.setParameter("name", "Max Rune");
//        try {
//            Student s = (Student) q.getSingleResult();
//
//            System.out.println(s.getStudentName() + " is studying " + s.getEducation().getEducationName());
//            System.out.println("The courses in " + s.getEducation().getEducationName() + " are: ");
//            for (Course temp : s.getEducation().getCourses()) {
//                System.out.println(temp.getCourseName());
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        Query q1 = em.createQuery("Select s From Student s where s.studentName = :name");
//        q1.setParameter("name", "Markus Mörk");
//        try {
//            Student s = (Student) q1.getSingleResult();
//
//            System.out.println(s.getStudentName() + " is studying " + s.getEducation().getEducationName());
//            System.out.println("The courses in " + s.getEducation().getEducationName() + " are: ");
//            for (Course temp : s.getEducation().getCourses()) {
//                System.out.println(temp.getCourseName());
//                System.out.println("The teacher for " + temp.getCourseName() + " is ");
//                for (Teacher t : temp.getTeachers()) {
//                    System.out.println(t.getTeacherName());
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }
}
