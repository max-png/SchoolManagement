package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import schoolmgmt.domain.*;

public class CourseDao implements Dao<Course> {

    protected EntityManagerFactory emf;
    protected EntityManager em;

    public CourseDao() {
//        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
//        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
        emf = Persistence.createEntityManagerFactory("SCHOOL_PU");
    }

    @Override
    public void add(Course t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(t);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in CourseJpaDao method add(): " + e);
        }
    }

    @Override
    public void update(Course t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Course update = em.find(Course.class, t.getId());
            update.setCourseName(t.getCourseName());
            update.setEducation(t.getEducation());
            update.setTeachers(t.getTeachers());
            update.setId(t.getId());
            em.merge(update);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in CourseJpaDao method update(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(Course t) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Course remove = em.find(Course.class, t.getId());
            em.remove(remove);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in remove CourseDao: " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void removeById(Long id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Course course = em.find(Course.class, id);
            em.remove(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in removeById CourseJpaDao: " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public Course getById(Long id) {
        em = emf.createEntityManager();
        try {
            em = emf.createEntityManager();
            Course course = em.find(Course.class, id);
            return course;
        } catch (Exception e) {
            System.out.println("Error in getById JpaDao: " + e);
            return null;
        }
    }

    @Override
    public List<Course> getAll() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("select c from Course c");
            List<Course> allCourses = (List<Course>) q.getResultList();
            em.getTransaction().commit();
            return allCourses;
        } catch (Exception e) {
            System.out.println("Error in getAll: " + e);
            return null;
        } finally {
            em.close();
        }
    }

}
