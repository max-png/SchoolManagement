package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import schoolmgmt.domain.*;

public class EducationDao implements Dao<Education> {

    protected EntityManagerFactory emf;
    protected EntityManager em;

    public EducationDao() {
        emf = Persistence.createEntityManagerFactory("SCHOOL_PU");
    }

    @Override
    public void add(Education t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(t);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in EducationDao method add(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Education t) {
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Education update = em.find(Education.class, t.getId());
            update.setCourses(t.getCourses());
            update.setEducationName(t.getEducationName());
            update.setId(t.getId());
            update.setStudents(t.getStudents());
            em.merge(update);
            et.commit();
        } catch (Exception e) {
            System.out.println("Error in EducationDao method update(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(Education t) {
        em = emf.createEntityManager();
        try {
            Education remove = em.find(Education.class, t.getId());
            em.remove(remove);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in EducationDao remove(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public void removeById(Long id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Education remove = em.find(Education.class, id);
            em.remove(remove);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in EducationDao removeById(): " + e);
        } finally {
            em.close();
        }
    }

    @Override
    public Education getById(Long id) {
        em = emf.createEntityManager();
        try {
            Education edu = em.find(Education.class, id);
            return edu;
        } catch (Exception e) {
            System.out.println("Error in EducationDao getById(): " + e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Education> getAll() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("select e from Education e");
            List<Education> allEducations = (List<Education>) q.getResultList();
            em.getTransaction().commit();
            return allEducations;
        } catch (Exception e) {
            System.out.println("Error in EducationDao getAll: " + e);
            return null;
        } finally {
            em.close();
        }
    }

}
