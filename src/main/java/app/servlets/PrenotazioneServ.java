package app.servlets;

import app.DAO.AutoDao;
import app.DAO.CodDao;
import app.DAO.PrenDao;
import app.DAO.UserDao;
import app.entities.Auto;
import app.entities.Codicesc;
import app.entities.Pren;
import app.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/Prenotazione"})
public class PrenotazioneServ extends HttpServlet {
    private static final long serialVersionUID = 23L;
    private PrenDao prenDao;
    private UserDao userDao;
    private AutoDao autoDao;
    private CodDao codDao;

    @Override
    public void init() throws ServletException {
        super.init();
        prenDao = new PrenDao();
        userDao = new UserDao();
        autoDao = new AutoDao();
        codDao = new CodDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action= request.getParameter("action");
        int idut= Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id",idut);
        try{
            String idPren = request.getParameter("idPren");
            if(idPren==null) {
                insertPren1(request,response,idut);
            } else {
               int c=Integer.parseInt(idPren);
               updatePren1(request,response,idut,c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action= request.getParameter("action");
        int idut= Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id",idut);
        try {
            if(action == null) {
                listPren1(request, response);}
                else {
                switch (action) {
                    case "approva":
                        String idPren = request.getParameter("idPren");
                        int p = Integer.parseInt(idPren);
                        approvPren(request, response, idut, p);
                        break;
                    case "napprova":
                        String idPren1 = request.getParameter("idPren");
                        int p1 = Integer.parseInt(idPren1);
                        napprovPren(request, response, idut, p1);
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listPren(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List <Pren> listPren = this.prenDao.getAllPren();
        request.setAttribute("listPren", listPren);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/prenotazioni.jsp");
        dispatcher.forward(request, response);
    }

    private void listPren1(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        List <Pren> listPren = this.prenDao.getPrenid(id);
        request.setAttribute("listPren", listPren);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/prenotazioni.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPren(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("dettagli");
        String datanst = request.getParameter("datap");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//surround below line with try catch block as below code throws checked exception
        Date datap = null;
        try {
            datap = sdf.parse(datanst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Pren newPren = new Pren(nome, datap);
        prenDao.savePren(newPren);
        response.sendRedirect(request.getContextPath() + "/Prenotazione");
    }

    private void insertPren1(HttpServletRequest request, HttpServletResponse response,int idu)
            throws SQLException, IOException {
        String nome = request.getParameter("dettagli");
        String datanst = request.getParameter("datap");
        int idauto = Integer.parseInt(request.getParameter("idauto"));
        int cod1;
        if(request.getParameter("codicesc").equals("")){
            cod1=-1;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//surround below line with try catch block as below code throws checked exception
            Date datap = null;
            try {
                datap = sdf.parse(datanst);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Pren newPren = new Pren(nome, datap);
            User u=userDao.getUser(idu);
            newPren.setUser(u);
            newPren.setStato("No");
            Auto a=new Auto();
            a=autoDao.getAuto(idauto);
            newPren.setAuto(a);
            prenDao.savePren(newPren);
            response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
        }else{
            cod1= Integer.parseInt(request.getParameter("codicesc"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//surround below line with try catch block as below code throws checked exception
            Date datap = null;
            try {
                datap = sdf.parse(datanst);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Pren newPren = new Pren(nome, datap);
            User u=userDao.getUser(idu);
            newPren.setUser(u);
            newPren.setStato("No");
            Codicesc sconto=new Codicesc();
            sconto=codDao.getCod(cod1);
            newPren.setCodicesc(sconto);
            Auto a=new Auto();
            a=autoDao.getAuto(idauto);
            newPren.setAuto(a);
            prenDao.savePren(newPren);
            response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
        }

    }

    private void updatePren(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("dettagli");
        String datanst = request.getParameter("datap");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //surround below line with try catch block as below code throws checked exception
        Date datap = null;
        try {
            datap = sdf.parse(datanst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Pren pren = new Pren(nome, datap);
        pren.setId(id);
        prenDao.updatePren(pren);
        response.sendRedirect(request.getContextPath() + "/Prenotazione");
    }

    private void updatePren1(HttpServletRequest request, HttpServletResponse response,int idu,int id1) //uso questo
            throws SQLException, IOException {
        String nome = request.getParameter("dettagli");
        String datanst = request.getParameter("datap");
        int idauto = Integer.parseInt(request.getParameter("idauto"));
        int cod1;
        if(request.getParameter("codicesc").equals("")) {
            cod1 = -1;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //surround below line with try catch block as below code throws checked exception
            Date datap = null;
            try {
                datap = sdf.parse(datanst);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            prenDao.updatePren12(id1,nome,datap,idauto);
            response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
        }else{
            cod1= Integer.parseInt(request.getParameter("codicesc"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //surround below line with try catch block as below code throws checked exception
            Date datap = null;
            try {
                datap = sdf.parse(datanst);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            prenDao.updatePren123(id1,nome,datap,idauto,cod1);
            response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
        }

    }
//
//private void approvePren(HttpServletRequest request, HttpServletResponse response)
//          throws SQLException, IOException {
//       int id = Integer.parseInt(request.getParameter("id"));
//       String stato = (request.getParameter("stato"));
//       prenDao.updatePren1(stato,id);
//       response.sendRedirect(request.getContextPath() + "/Prenotazione");
//  }


    private void approvPren(HttpServletRequest request, HttpServletResponse response,int idu, int id)
            throws SQLException, IOException {
        prenDao.ApprPren(id);
        response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
    }

    private void napprovPren(HttpServletRequest request, HttpServletResponse response,int idu, int id)
            throws SQLException, IOException {
        prenDao.NApprPren(id);
        response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
    }
}
