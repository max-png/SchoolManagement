package schoolmgmt;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import schoolmgmt.domain.*;

public class main {

    public static void main(String[] args) {

        Education musicEducation = new Education();
        musicEducation.setEducationName("Musical Education");

        Education languageEdu = new Education();
        languageEdu.setEducationName("Language Education");

        Teacher teacher = new Teacher();
        teacher.setTeacherName("Bertil Fält");

        Teacher teacher2 = new Teacher();
        teacher2.setTeacherName("Johan Sundberg");

        Course course = new Course();
        course.setCourseName("Guitar for Beginners");

        Course course1 = new Course();
        course1.setCourseName("Guitar: Advanced Level");

        Course course2 = new Course();
        course2.setCourseName("Swedish for Beginners");

        teacher.addCourse(course);
        teacher.addCourse(course1);
        teacher2.addCourse(course1);
        teacher2.addCourse(course2);

        musicEducation.addCourse(course);
        musicEducation.addCourse(course1);

        languageEdu.addCourse(course2);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCHOOL_PU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(musicEducation);
        em.persist(languageEdu);
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.remove(musicEducation);
        em.getTransaction().commit();

    }

}
