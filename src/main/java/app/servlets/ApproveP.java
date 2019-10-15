package app.servlets;

import app.DAO.PrenDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/Appr"})
public class ApproveP extends HttpServlet {

    private static final long serialVersionUID = 235L;
    private PrenDao prenDao;

    @Override
    public void init() throws ServletException {
        super.init();
        prenDao = new PrenDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        int idut= Integer.parseInt(request.getParameter("idu"));
        int idp= Integer.parseInt(request.getParameter("id"));
        try {
            switch (action) {
                case "approva":
                    approvPren(request,response,idut,idp);
                    break;
                case "napprova":
                    approvPren(request,response,idut,idp);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void approvPren(HttpServletRequest request, HttpServletResponse response,int idu, int id)
            throws SQLException, IOException {
        prenDao.ApprPren(id);
        response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
    }

    private void napprovPren(HttpServletRequest request, HttpServletResponse response,int idu, int id)
            throws SQLException, IOException {
        prenDao.ApprPren(id);
        response.sendRedirect(request.getContextPath() + "/Prenotazione?id="+idu);
    }

}
