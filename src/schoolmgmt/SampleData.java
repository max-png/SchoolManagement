package schoolmgmt;

import Dao.EducationDao;
import schoolmgmt.domain.Course;
import schoolmgmt.domain.Education;
import schoolmgmt.domain.Student;
import schoolmgmt.domain.Teacher;

public class SampleData {

    public static void add() {
        EducationDao eDao = new EducationDao();

        Education e1 = new Education("Grundutbildning i Ostvetenskap");

        Course c1 = new Course("Ost - vad är det?");
        Course c2 = new Course("Mjölkbakterier i världen");
        Course c3 = new Course("Ost och hälsa");

        e1.addCourse(c1);
        e1.addCourse(c2);
        e1.addCourse(c3);

        Teacher t1 = new Teacher("Brünolf Käse");
        t1.addCourse(c1);
        t1.addCourse(c2);
        t1.addCourse(c3);

        Teacher t2 = new Teacher("Ullmor Padano");
        t2.addCourse(c3);

        Education e2 = new Education("Brandmansutbildning");
        Course c4 = new Course("Brandsäkerhet");
        Course c5 = new Course("Slanghantering");
        e2.addCourse(c4);
        e2.addCourse(c5);

        Teacher t3 = new Teacher("Janne Gnista");
        t3.addCourse(c4);
        t3.addCourse(c5);

        Education e3 = new Education("Filosofi A");
        Course c6 = new Course("Kritiskt tänkande");
        Course c7 = new Course("Värdeteori");
        Course c8 = new Course("Etik");
        e3.addCourse(c6);
        e3.addCourse(c7);
        e3.addCourse(c8);

        Student s1 = new Student("Cheezy McJeezy");
        e1.addStudent(s1);
        Student s2 = new Student("Jonny Bolton");
        e1.addStudent(s2);
        Student s3 = new Student("Stakka Bo");
        e2.addStudent(s3);
        Student s4 = new Student("Stewie Sniffin");
        e2.addStudent(s4);
        Student s5 = new Student("Klara Go");
        e3.addStudent(s5);
        Student s6 = new Student("Pelle Pellshund");
        e3.addStudent(s6);

        eDao.add(e1);
        eDao.add(e2);
        eDao.add(e3);
        
    }

}
