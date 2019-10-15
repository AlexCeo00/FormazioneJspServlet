package app.servlets;

import app.DAO.UserDao;
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

@WebServlet(urlPatterns = {"/User"})
public class UserServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           String idUtente = request.getParameter("idUtente");
           if(idUtente==null) {
               insertUser(request,response);
           } else {
               updateUser(request,response);
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
                    listUser(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDao.getAllUser();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin.jsp");
        dispatcher.forward(request, response);
    }


    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String passut = request.getParameter("passut");
        String tipologia = request.getParameter("tipologia");
        String datanst = request.getParameter("datan");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//surround below line with try catch block as below code throws checked exception
        Date datan = null;
        try {
            datan = sdf.parse(datanst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User newUser = new User(nome, email, passut, tipologia, datan);
        userDao.saveUser(newUser);
        response.sendRedirect(request.getContextPath() + "/User");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String passut = request.getParameter("passut");
        String tipologia = request.getParameter("tipologia");
        String datanst = request.getParameter("datan");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //surround below line with try catch block as below code throws checked exception
        Date datan = null;
        try {
            datan = sdf.parse(datanst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User(nome, email, passut, tipologia, datan);
        user.setId(id);
        userDao.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/User");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/User");
    }


}

