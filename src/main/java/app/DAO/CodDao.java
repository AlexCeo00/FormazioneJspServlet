package app.DAO;

import app.entities.Auto;
import app.entities.Codicesc;
import app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CodDao {
    public CodDao(){}
    /**
     * Save Codicesc
     * @param cod
     */
    public void saveCod(Codicesc cod) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // save the student object
            session.save(cod);
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
     * Update Codicesc
     * @param cod
     */
    public void updateCod(Codicesc cod) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(cod);
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
     * Delete Codicesc
     * @param id
     */
    public void deleteCod(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Delete a user object
            Codicesc cod = session.get(Codicesc.class, id);
            if (cod != null) {
                session.delete(cod);
                System.out.println("cod is deleted");
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
     * Get Codicesc by ID
     * @param id
     * @return
     */
    public Codicesc getCod(int id) {

        Transaction transaction = null;
        Codicesc cod = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an auto object
            cod = session.get(Codicesc.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cod;
    }

    /**
     * Get all Codes
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Codicesc> getAllCod() {
        Transaction transaction = null;
        List <Codicesc> listOfCod = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            listOfCod = session.createQuery("from Codicesc").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfCod;
    }
}
