package schoolmgmt.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

/**
 * @author Max Rune
 */
@Entity
public class Education {

    public Education(String educationName) {
        this.educationName = educationName;
    }

    public Education() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String educationName;

    @OneToMany(mappedBy = "education", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Course> courses;

    @OneToMany(mappedBy = "education", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> students;

    //Kan inte ta bort education pga education har barn så detta måste finnas.
    
    @PreRemove
    private void preRemove() {
        for (Course c : courses) {
            c.setEducation(null);
        }
        for (Student s : students) {
            s.setEducation(null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public List<Course> getCourses() {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        getCourses().add(course);
        course.setEducation(this);
    }

    public void removeCourse(Course course) {
        getCourses().remove(course);
        course.setEducation(null);
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        getStudents().add(student);
        student.setEducation(this);
    }

    public void removeStudent(Student student) {
        getStudents().remove(student);
        student.setEducation(null);
    }

}
