package app.DAO;

import app.entities.Auto;
import app.entities.Codicesc;
import app.entities.Pren;
import app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PrenDao {
    private AutoDao autoDao;
    private CodDao codDao;
    public PrenDao(){}
    /**
     * Save Pren
     * @param pren
     */
    public void savePren(Pren pren) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // save the student object
            session.save(pren);
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
     * Update Pren
     * @param pren
     */
    public void updatePren(Pren pren) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(pren);
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
    public void updatePren12(int id1, String s, Date d,int idauto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            session.beginTransaction();// solo per modifiche nel DB non per normali select
            Pren p= session.get(Pren.class,id1);
            p.setDettagli(s);
            p.setDatap(d);
            int idprova=idauto;
            Auto a=new Auto();
            AutoDao autd=new AutoDao();
            a=autd.getAuto(idauto);
            p.setAuto(a);
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
     * Update Pren
     * @param id1
     */
    public void updatePren123(int id1, String s, Date d,int idauto,int cod1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            session.beginTransaction();// solo per modifiche nel DB non per normali select
            Pren p= session.get(Pren.class,id1);
            p.setDettagli(s);
            p.setDatap(d);
            int idprova=idauto;
            Codicesc sconto=new Codicesc();
            CodDao cod=new CodDao();
            sconto=cod.getCod(cod1);
            p.setCodicesc(sconto);
            Auto a=new Auto();
            AutoDao autd=new AutoDao();
            a=autd.getAuto(idauto);
            p.setAuto(a);
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
     * Delete Pren
     * @param id
     */
    public void deletePren(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Delete a user object
            Pren pren = session.get(Pren.class, id);
            if (pren != null) {
                session.delete(pren);
                System.out.println("reservation is deleted");
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
     * Get Pren By ID
     * @param id
     * @return
     */
    public Pren getPren(int id) {
        Transaction transaction = null;
        Pren pren= null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            pren = session.get(Pren.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return pren;
    }


    /**
     * Get Pren By Name
     * @param nome
     * @return
     */
    public Pren getPrenN(String nome) {

        Transaction transaction = null;
        Pren pren = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            pren = session.get(Pren.class, nome);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return pren;
    }

    /**
     * Get all Pren
     * @return
     */
    @SuppressWarnings("unchecked")
    public List< Pren > getAllPren() {
        Transaction transaction = null;
        List < Pren > listOfPren = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            listOfPren = session.createQuery("from Pren").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfPren;
    }


    /**
     * Get Pren by ID
     * @return
     */
    public List< Pren > getPrenid(int id) {
        Transaction transaction = null;
        List < Pren > listOfPren = null;
        int id1=id;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
//            transaction = session.beginTransaction(); solo per modifiche nel DB non per normali select
            // get an user object
           TypedQuery<Pren> query = session.createQuery("from Pren p where p.user.id= :id", Pren.class);
           query.setParameter("id", id);
           listOfPren = query.getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfPren;
    }

    /**
     * Approva prenotazione
     */
    public void ApprPren(int idP) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            session.beginTransaction();// solo per modifiche nel DB non per normali select
            Pren p= session.get(Pren.class,idP);
            p.setStato("Si");
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
     * non approva prenotazione
     * @return
     */
    public void NApprPren(int idP) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            session.beginTransaction();// solo per modifiche nel DB non per normali select
            Pren p= session.get(Pren.class,idP);
            p.setStato("No");
            session.update(p);
            session.getTransaction().commit();
        } catch (Exception e) {
            if ( session.getTransaction()!= null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
