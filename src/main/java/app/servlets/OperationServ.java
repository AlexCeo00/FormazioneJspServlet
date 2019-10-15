package app.servlets;

import app.DAO.*;
import app.entities.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/Operation"})
public class OperationServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;
    private AutoDao autoDao;
    private PrenDao prenDao;
    private ProblDao problDao;
    private CodDao codDao;
    private RuoliDao ruoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
        autoDao = new AutoDao();
        prenDao = new PrenDao();
        problDao = new ProblDao();
        codDao = new CodDao();
        ruoDao = new RuoliDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String operation = request.getParameter("operation");
            String tipo = request.getParameter("tipo");
            switch (operation) {
                case "new": {
                    switch (tipo) {
                        case "utente":
                            showNewFormUtente(request, response);
                            break;
                        case "auto":
                            showNewFormAuto(request, response);
                            break;
                        case "prenotazione":
                            int id= Integer.parseInt(request.getParameter("id"));
                            showNewFormPren(request, response,id);
                            break;
                        case "problema":
                            int id1= Integer.parseInt(request.getParameter("id"));
                            showNewFormProbl(request, response,id1);
                            break;
                        case "codicesc":
                            showNewFormCod(request, response);
                            break;
                        case "ruolo":
                            showNewFormRuo(request, response);
                            break;
                    }
                    break;
                }
                case "update": {
                    switch (tipo) {
                        case "utente":
                            int idUtente = Integer.parseInt(request.getParameter("id"));
                            showEditFormUtente(request, response, idUtente);
                            break;
                        case "auto":
                            int idAuto = Integer.parseInt(request.getParameter("id"));
                            showEditFormAuto(request, response, idAuto);
                            break;
                        case "prenotazione":
                            int idPren = Integer.parseInt(request.getParameter("idPren"));
                            int id= Integer.parseInt(request.getParameter("id"));
                            showEditFormPren(request, response, idPren,id);
                            break;
                        case "problema":
                            int idProbl = Integer.parseInt(request.getParameter("idProbl"));
                            int idut = Integer.parseInt(request.getParameter("idutente"));
                            showEditFormProbl(request, response, idProbl,idut);
                            break;
                        case "codicesc":
                            int idCod = Integer.parseInt(request.getParameter("id"));
                            showEditFormCod(request, response, idCod);
                            break;
                        case "ruolo":
                            int idRuo = Integer.parseInt(request.getParameter("id"));
                            showEditFormRuo(request, response, idRuo);
                            break;
                    }
                    break;
                }
                case "delete": {
                    switch (tipo) {
                        case "utente":
                            int idUtente = Integer.parseInt(request.getParameter("id"));
                            deleteUser(request, response, idUtente);
                            break;
                        case "auto":
                            int idAuto = Integer.parseInt(request.getParameter("id"));
                            deleteAuto(request, response, idAuto);
                            break;
                        case "prenotazione":
                            int idPren = Integer.parseInt(request.getParameter("id"));
                            deletePren(request, response, idPren);
                            break;
                        case "problema":
                            int idProbl = Integer.parseInt(request.getParameter("id"));
                            int idutente = Integer.parseInt(request.getParameter("idutente"));
                            deleteProbl(request, response, idProbl,idutente);
                            break;
                        case "codicesc":
                            int idCod = Integer.parseInt(request.getParameter("id"));
                            deleteCod(request, response, idCod);
                            break;
                        case "ruolo":
                            int idRuo = Integer.parseInt(request.getParameter("id"));
                            deleteRuo(request, response, idRuo);
                            break;
                        case "ruolodel":
                            int idRuo1 = Integer.parseInt(request.getParameter("id"));
                            Moddelruoli(request, response, idRuo1);
                            break;
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

//utente
    private void showNewFormUtente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formutente.jsp");
        List<Ruoli> listRuo = ruoDao.getAllRuo();
        request.setAttribute("listRuo", listRuo);
        dispatcher.forward(request, response);
    }

    private void showEditFormUtente(HttpServletRequest request, HttpServletResponse response, int idUtente)
            throws SQLException, ServletException, IOException {
        User existingUser = userDao.getUser(idUtente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formutente.jsp");
        List<Ruoli> listRuo = ruoDao.getAllRuo();
        request.setAttribute("listRuo", listRuo);
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response, int idUtente)
            throws SQLException, IOException {
        userDao.deleteUser(idUtente);
        response.sendRedirect(request.getContextPath() + "/User");
    }

//auto
    private void showNewFormAuto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formauto.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormAuto(HttpServletRequest request, HttpServletResponse response, int idAuto)
            throws SQLException, ServletException, IOException {
        Auto existingAuto = autoDao.getAuto(idAuto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formauto.jsp");
        request.setAttribute("auto", existingAuto);
        dispatcher.forward(request, response);
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response, int idAuto)
            throws SQLException, IOException {
        autoDao.deleteAuto(idAuto);
        response.sendRedirect(request.getContextPath() + "/Park");
    }

//prenotazione
    private void showNewFormPren(HttpServletRequest request, HttpServletResponse response,int id)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formpren.jsp");
        List <Auto> listAuto = autoDao.getAllAuto();
        request.setAttribute("listAuto", listAuto);
        request.setAttribute("id", id);
        dispatcher.forward(request, response);
    }

    private void showEditFormPren(HttpServletRequest request, HttpServletResponse response, int idPren,int id)
            throws SQLException, ServletException, IOException {
        Pren existingPren = prenDao.getPren(idPren);
        List <Auto> listAuto = autoDao.getAllAuto();//ottengo la lista delle auto
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formpren.jsp");
        request.setAttribute("id", id);
        request.setAttribute("pren", existingPren); //nella prossima pagina setto questi attributi
        request.setAttribute("listAuto", listAuto);//set dell'attributo
        dispatcher.forward(request, response);//redirect
    }

    private void deletePren(HttpServletRequest request, HttpServletResponse response, int idPren)
            throws SQLException, IOException {
        Pren p=new Pren();
        p=prenDao.getPren(idPren);
        User u=new User();
        u=p.getUser();
        prenDao.deletePren(idPren);
        response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+u.getId());
    }

//problema
private void showNewFormProbl(HttpServletRequest request, HttpServletResponse response,int id)
        throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("views/formprobl.jsp");
    request.setAttribute("id", id);
    List<Pren> listPren = prenDao.getPrenid(id);
    request.setAttribute("listPren", listPren);
    dispatcher.forward(request, response);
}

    private void showEditFormProbl(HttpServletRequest request, HttpServletResponse response, int idProbl, int id)
            throws SQLException, ServletException, IOException {
        Probl existingProbl = problDao.getProbl(idProbl);
        List<Pren> listPren = prenDao.getPrenid(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formprobl.jsp");
        request.setAttribute("id", id);
        request.setAttribute("probl", existingProbl);
        request.setAttribute("listPren", listPren);
        dispatcher.forward(request, response);
    }

    private void deleteProbl(HttpServletRequest request, HttpServletResponse response, int idProbl,int idutente)
            throws SQLException, IOException {
        problDao.deleteProbl(idProbl);
        response.sendRedirect(request.getContextPath() + "/Problema?id="+idutente);
    }


    //codicesc
    private void showNewFormCod(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formcodicesc.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormCod(HttpServletRequest request, HttpServletResponse response, int idCod)
            throws SQLException, ServletException, IOException {
        Codicesc existingCod = codDao.getCod(idCod);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formcodicesc.jsp");
        request.setAttribute("cod", existingCod);
        dispatcher.forward(request, response);
    }

    private void deleteCod(HttpServletRequest request, HttpServletResponse response, int idCod)
            throws SQLException, IOException {
        codDao.deleteCod(idCod);
        response.sendRedirect(request.getContextPath() + "/Codice");
    }

    //ruolo
    private void showNewFormRuo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formruolo.jsp");
        List<Ruoli> listRuo = ruoDao.getAllRuo();
        request.setAttribute("listRuo", listRuo);
        dispatcher.forward(request, response);
    }

    private void showEditFormRuo(HttpServletRequest request, HttpServletResponse response, int idRuo)
            throws SQLException, ServletException, IOException {
        Ruoli existingRuo = ruoDao.getRuo(idRuo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/formruolo.jsp");
        List<Ruoli> listRuo = ruoDao.getAllRuo();
        request.setAttribute("listRuo", listRuo);
        request.setAttribute("ruo", existingRuo);
        dispatcher.forward(request, response);
    }

    private void deleteRuo(HttpServletRequest request, HttpServletResponse response, int idRuo)
            throws SQLException, IOException {
        ruoDao.deleteRuo(idRuo);
        response.sendRedirect(request.getContextPath() + "/Ruoli");
    }
    private void Moddelruoli(HttpServletRequest request, HttpServletResponse response, int idRuo)
            throws SQLException, ServletException, IOException {
        Ruoli nr=new Ruoli();
        nr=ruoDao.getRuo(idRuo);
        nr.setDele("si");
        ruoDao.updateRuo(nr);
        response.sendRedirect(request.getContextPath() + "/Ruoli");
    }
}