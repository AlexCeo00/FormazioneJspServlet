package app.servlets;

import app.DAO.AutoDao;
import app.entities.Auto;

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

@WebServlet(urlPatterns = {"/Park"})
public class AdminParkServ extends HttpServlet {
    private static final long serialVersionUID = 12L;
    private AutoDao autoDao;

    public void init() {
        autoDao = new AutoDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String idAuto = request.getParameter("idAuto");
            if(idAuto==null) {
                insertAuto(request,response);
            } else {
                updateAuto(request,response);
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
                    listAuto(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Auto> listAuto = autoDao.getAllAuto();
        request.setAttribute("listAuto", listAuto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/ParcoAutoadmin.jsp");
        dispatcher.forward(request, response);
    }

    private void insertAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String tipo = request.getParameter("tipo");
        String targa = request.getParameter("targa");
        float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        String dispon = request.getParameter("dispon");
        Auto newAuto = new Auto(nome, tipo, targa, prezzo, dispon);
        autoDao.saveAuto(newAuto);
        response.sendRedirect(request.getContextPath() + "/Park");
    }

    private void updateAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String tipo = request.getParameter("tipo");
        String targa = request.getParameter("targa");
        float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        String dispon = request.getParameter("dispon");
        Auto auto = new Auto(nome, tipo, targa, prezzo, dispon);
        auto.setId(id);
        autoDao.updateAuto(auto);
        response.sendRedirect(request.getContextPath() + "/Park");
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        autoDao.deleteAuto(id);
        response.sendRedirect(request.getContextPath() + "/Park");
    }

}
