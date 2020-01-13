package schoolmgmt;

import Dao.*;
import java.util.List;
import schoolmgmt.domain.*;

public class main {

    public static void main(String[] args) {

            SampleData.add();

//        Menu.mainMenu();
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
//            System.out.println("The courses in " + s.getEducation().getEducationName() + " are: ");
    }
}
