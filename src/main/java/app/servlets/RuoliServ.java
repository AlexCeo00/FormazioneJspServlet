package app.servlets;

import app.DAO.RuoliDao;
import app.entities.Ruoli;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/Ruoli"})
public class RuoliServ extends HttpServlet {
    private static final long serialVersionUID = 123456L;
    private RuoliDao ruoDao;

    public void init() {
        ruoDao = new RuoliDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String idRuo = request.getParameter("idRuo");
            if(idRuo==null) {
                insertRuo(request,response);
            } else {
                updateRuo(request,response);
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
                    listRuo(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listRuo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Ruoli> listRuo = ruoDao.getAllRuo();
        request.setAttribute("listRuo", listRuo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/ruoli.jsp");
        dispatcher.forward(request, response);
    }

    private void insertRuo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String dettagli= request.getParameter("dettagli");
        String stato= request.getParameter("stato");
        Ruoli newRuo = new Ruoli(dettagli,stato);
        newRuo.setDele("no");
        ruoDao.saveRuo(newRuo);
        response.sendRedirect(request.getContextPath() + "/Ruoli");
    }

    private void updateRuo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String dettagli= request.getParameter("dettagli");
        String stato= request.getParameter("stato");
        Ruoli ruo = new Ruoli(dettagli,stato);
        ruo.setId(id);
        ruoDao.updateRuo(ruo);
        response.sendRedirect(request.getContextPath() + "/Ruoli");
    }

    private void deleteRuo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ruoDao.deleteRuo(id);
        response.sendRedirect(request.getContextPath() + "/Ruoli");
    }
}