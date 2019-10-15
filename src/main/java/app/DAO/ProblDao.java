package app.DAO;

import app.entities.Pren;
import app.entities.Probl;
import app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class ProblDao {
    private PrenDao prenDao;
    public ProblDao(){ }
    /**
     * Save Probl
     * @param probl
     */
    public void saveProbl(Probl probl) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // save the student object
            session.save(probl);
            // commit transaction
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Update Probl
     * @param probl
     */
    public void updateProbl(Probl probl) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(probl);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Update Pren
     * @param id1
     */
    public void updateProbl12(int id1, String s, Date d,int idpreno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            session.beginTransaction();// solo per modifiche nel DB non per normali select
            Probl p= session.get(Probl.class,id1);
            p.setDescrizione(s);
            p.setDataprob(d);
            Pren pr=new Pren();
            PrenDao prnd=new PrenDao();
            pr=prnd.getPren(idpreno);
            p.setPren(pr);
            session.update(p);
            session.getTransaction().commit();
        } catch (Exception e) {
            if ( session.getTransaction()!= null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Delete Probl
     * @param id
     */
    public void deleteProbl(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Delete a user object
            Probl probl = session.get(Probl.class, id);
            if (probl != null) {
                session.delete(probl);
                System.out.println("user is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get User By ID
     * @param id
     * @return
     */
    public Probl getProbl(int id) {
        Transaction transaction = null;
        Probl probl = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an Probl object
            probl = session.get(Probl.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return probl;
    }


    /**
     * Get all Probl
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < Probl > getAllProbl() {
        Transaction transaction = null;
        List < Probl > listProbl = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            listProbl = session.createQuery("from Probl").getResultList();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listProbl;
    }

    /**
     * Get Pren by ID
     * @return
     */
    public List< Probl > getProblid(int id) {
        Transaction transaction = null;
        List < Probl > listOfProbl = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
//            transaction = session.beginTransaction(); solo per modifiche nel DB non per normali select
            // get an user object
            Query<Probl> query = session.createQuery("select p from Probl p WHERE p.pren.user.id= :id", Probl.class);
            query.setParameter("id", id);
            listOfProbl = query.getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfProbl;
    }

}