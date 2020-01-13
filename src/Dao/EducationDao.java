package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Education getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Education> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
