package app.servlets;

import app.DAO.PrenDao;
import app.DAO.ProblDao;
import app.DAO.UserDao;
import app.entities.Pren;
import app.entities.Probl;
import app.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/Problema"})
public class ProblServ extends HttpServlet {
    private static final long serialVersionUID = 2335L;
    private ProblDao problDao;
    private UserDao userDao;
    private PrenDao prenDao;

    @Override
    public void init() throws ServletException {
        super.init();
        problDao = new ProblDao();
        userDao = new UserDao();
        prenDao= new PrenDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idut= Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id",idut);
        try{
           // int idprenotazione1= Integer.parseInt(request.getParameter("idprenotazione"));
            String idProbl= request.getParameter("idProbl");
            if(idProbl==null) {
                insertProbl1(request,response,idut);
            } else {
                int c=Integer.parseInt(idProbl);
                updateProbl1(request,response,c,idut);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                default:
                    listProbl1(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listPren(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Probl> listProbl = this.problDao.getAllProbl();
        request.setAttribute("listProbl", listProbl);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/problemi.jsp");
        dispatcher.forward(request, response);
    }

    private void listProbl1(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        List <Probl> listProbl = this.problDao.getProblid(id);
        request.setAttribute("listProbl", listProbl);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/problemi.jsp");
        dispatcher.forward(request, response);
    }
//    private void listProbl1byauto(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        List <Probl> listProbl = this.problDao.getProblidauto(id);
//        request.setAttribute("listProbl", listProbl);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/problemi.jsp");
//        dispatcher.forward(request, response);
//    }

//    private void insertProbl(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        //User usert=new User();
//        // usert.setUser(User(request.getParameter("usern")));
//        int id = Integer.parseInt(request.getParameter("id"));
//        String nome = request.getParameter("descrizione");
//        String datanst = request.getParameter("dataprobl");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////surround below line with try catch block as below code throws checked exception
//        Date dataprobl = null;
//        try {
//            dataprobl = sdf.parse(datanst);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Probl newProbl = new Probl(nome, dataprobl);
//        problDao.saveProbl(newProbl);
//        response.sendRedirect(request.getContextPath() + "/Problema");
//    }
private void insertProbl1(HttpServletRequest request, HttpServletResponse response,int idut)
        throws SQLException, IOException {
    String nome = request.getParameter("descrizione");
    String datanst = request.getParameter("dataprobl");
    int idpreno = Integer.parseInt(request.getParameter("idprenotazione"));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//surround below line with try catch block as below code throws checked exception
    Date dataprobl = null;
    try {
        dataprobl = sdf.parse(datanst);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Probl newProbl = new Probl(nome, dataprobl);
    Pren p=prenDao.getPren(idpreno);
    newProbl.setPren(p);
    problDao.saveProbl(newProbl);
    response.sendRedirect(request.getContextPath() + "/Problema?id="+idut);
}

    private void updateProbl(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("descrizione");
        String datanst = request.getParameter("dataprobl");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //surround below line with try catch block as below code throws checked exception
        Date dataprobl = null;
        try {
            dataprobl = sdf.parse(datanst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Probl probl = new Probl(nome, dataprobl);
        probl.setId(id);
        problDao.updateProbl(probl);
        response.sendRedirect(request.getContextPath() + "/Problema");
    }

    private void updateProbl1(HttpServletRequest request, HttpServletResponse response, int idprobl, int idut) //uso questo
            throws SQLException, IOException {
        String nome = request.getParameter("descrizione");
        String datanst = request.getParameter("dataprobl");
        int idpreno = Integer.parseInt(request.getParameter("idprenotazione"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //surround below line with try catch block as below code throws checked exception
        Date datap = null;
        try {
            datap = sdf.parse(datanst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        problDao.updateProbl12(idprobl,nome,datap,idpreno);
        response.sendRedirect(request.getContextPath() + "/Problema?id="+idut);
    }

    private void deletePren(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        problDao.deleteProbl(id);
        response.sendRedirect(request.getContextPath() + "/Problema");
    }

}
