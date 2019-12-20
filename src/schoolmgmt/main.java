package schoolmgmt;

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

//        em.getTransaction().begin();
//        em.remove(musicEducation);
//        em.getTransaction().commit();
//        
        Student max = new Student();
        Student mack = new Student();
        mack.setStudentName("Markus Mörk");
        mack.setEducation(languageEdu);

        max.setStudentName("Max Rune");
        max.setEducation(musicEducation);

        em.getTransaction().begin();

        em.persist(musicEducation);
        em.persist(max);
        em.persist(mack);
        em.persist(languageEdu);
        em.getTransaction().commit();

        Query q = em.createQuery("Select s From Student s where s.studentName = :name");
        q.setParameter("name", "Max Rune");
        try {
            Student s = (Student) q.getSingleResult();

            System.out.println(s.getStudentName() + " is studying " + s.getEducation().getEducationName());
            System.out.println("The courses in " + s.getEducation().getEducationName() + " are: ");
            for (Course temp : s.getEducation().getCourses()) {
                System.out.println(temp.getCourseName());
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        Query q1 = em.createQuery("Select s From Student s where s.studentName = :name");
        q1.setParameter("name", "Markus Mörk");
        try {
            Student s = (Student) q1.getSingleResult();

            System.out.println(s.getStudentName() + " is studying " + s.getEducation().getEducationName());
            System.out.println("The courses in " + s.getEducation().getEducationName() + " are: ");
            for (Course temp : s.getEducation().getCourses()) {
                System.out.println(temp.getCourseName());
                System.out.println("The teacher for " + temp.getCourseName() + " is ");
                for (Teacher t : temp.getTeachers()) {
                    System.out.println(t.getTeacherName());
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
