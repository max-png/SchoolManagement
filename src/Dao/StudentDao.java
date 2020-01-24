package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import schoolmgmt.domain.Student;

public class StudentDao implements Dao<Student> {

    protected EntityManagerFactory emf;
    protected EntityManager em;

    public StudentDao() {
        emf = Persistence.createEntityManagerFactory("SCHOOL_PU");
    }

    @Override
    public void add(Student t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(t);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in StudentDao method add(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Student t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Student update = em.find(Student.class, t.getId());
            update.setEducation(t.getEducation());
            update.setId(t.getId());
            update.setStudentName(t.getStudentName());
            em.merge(update);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in StudentDao update(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(Student t) {
        em = emf.createEntityManager();
        try {
            Student remove = em.find(Student.class, t.getId());
            em.remove(remove);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in StudentDao remove(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void removeById(Long id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student remove = em.find(Student.class, id);
            em.remove(remove);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in StudentDao removeById(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public Student getById(Long id) {
        em = emf.createEntityManager();
        try {
            Student student = em.find(Student.class, id);
            return student;
        } catch (Exception e) {
            System.out.println("Error in StudentDao getById(): " + e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> getAll() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("select c from Student c");
            List<Student> allStudents = (List<Student>) q.getResultList();
            em.getTransaction().commit();
            return allStudents;
        } catch (Exception e) {
            System.out.println("Error in StudentDao getAll(): " + e);
            return null;
        } finally {
            em.close();
        }
    }

}
