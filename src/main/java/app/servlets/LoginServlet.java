package app.servlets;
import app.DAO.LoginDao;
import app.entities.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 123L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("nome");
        String password = request.getParameter("passut");
        User user = new User();
        User user1 = new User();
        user.setNome(username);
        user.setPassut(password);
        try {if(loginDao.isPresent(user)!=null) {
            user1 = loginDao.isPresent(user);
            HttpSession session = request.getSession();
            String s = user1.getTipologia();
            session.setAttribute("user", user1);
            if (s.equals("Admin"))
                response.sendRedirect(request.getContextPath() + "/User");
            else if (s.equals("Customer"))
                response.sendRedirect(request.getContextPath() + "/Prenotazione?id=" + user1.getId());
        }else{
            request.setAttribute("er1",true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");//il dispatcher permette il passaggio dell'attributo settato
            dispatcher.forward(request, response);}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
