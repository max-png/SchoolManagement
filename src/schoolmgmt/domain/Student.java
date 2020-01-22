package schoolmgmt.domain;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * @author Max Rune
 */
@Entity
public class Student {

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public Student() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String studentName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Education education;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courses;

    @PreRemove
    private void removeStudentFromEducation() {
        education.getStudents().remove(this);
    }

    @PreUpdate
    private void moveStudentFromEducation() {
        this.setEducation(null); // Vid borttagning går detta bra, men vid flytt får den ingen ny education.
    }

    @PostUpdate
    private void postupdate() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
