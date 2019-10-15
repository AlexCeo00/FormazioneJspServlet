package app.servlets;

import app.DAO.CodDao;
import app.entities.Codicesc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/Codice"})
public class CodicesServ  extends HttpServlet {
    private static final long serialVersionUID = 12345L;
    private CodDao codDao;

    public void init() {
        codDao = new CodDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String idCod = request.getParameter("idCod");
            if(idCod==null) {
                insertCod(request,response);
            } else {
                updateCod(request,response);
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
                    listCod(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listCod(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List <Codicesc> listCod = codDao.getAllCod();
        request.setAttribute("listCod", listCod);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/codices.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCod(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int prezzo= Integer.parseInt(request.getParameter("cifra"));
        Codicesc newCod = new Codicesc(prezzo);
        codDao.saveCod(newCod);
        response.sendRedirect(request.getContextPath() + "/Codice");
    }

    private void updateCod(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int prezzo = Integer.parseInt(request.getParameter("cifra"));
        Codicesc cod = new Codicesc(prezzo);
        cod.setId(id);
        codDao.updateCod(cod);
        response.sendRedirect(request.getContextPath() + "/Codice");
    }

    private void deleteCod(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        codDao.deleteCod(id);
        response.sendRedirect(request.getContextPath() + "/Codice");
    }
}
