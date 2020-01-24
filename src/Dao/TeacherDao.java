package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import schoolmgmt.domain.Teacher;

public class TeacherDao implements Dao<Teacher> {

    protected EntityManagerFactory emf;
    protected EntityManager em;

    public TeacherDao() {
        emf = Persistence.createEntityManagerFactory("SCHOOL_PU");
    }

    @Override
    public void add(Teacher t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(t);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in TeacherDao add(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Teacher t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Teacher update = em.find(Teacher.class, t.getId());
            update.setCourses(t.getCourses());
            update.setId(t.getId());
            update.setTeacherName(t.getTeacherName());
            em.merge(update);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in TeacherDao update(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(Teacher t) {
        em = emf.createEntityManager();
        try {
            Teacher remove = em.find(Teacher.class, t.getId());
            em.remove(remove);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in TeacherDao remove(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void removeById(Long id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Teacher remove = em.find(Teacher.class, id);
            em.remove(remove);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in TeacherDao removeById(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public Teacher getById(Long id) {
        em = emf.createEntityManager();
        try {
            Teacher teacher = em.find(Teacher.class, id);
            return teacher;
        } catch (Exception e) {
            System.out.println("Error in TeacherDao getById(): " + e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Teacher> getAll() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("Select c from Teacher c");
            List<Teacher> allTeachers = (List<Teacher>) q.getResultList();
            em.getTransaction().commit();
            return allTeachers;
        } catch (Exception e) {
            System.out.println("Error in TeacherDao getAll(): " + e);
            return null;
        } finally {
            em.close();
        }
    }

}
