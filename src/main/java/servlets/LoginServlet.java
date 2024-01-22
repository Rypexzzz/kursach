package servlets;

import models.User;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/register", "/logout"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getRequestURI()) {
            case "/logout": {
                HttpSession session = req.getSession();
                if (session != null && session.getAttribute("id") != null) {
                    session.removeAttribute("id");
                }
                if (session != null && session.getAttribute("username") != null) {
                    session.removeAttribute("username");
                }
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            case "/login":
            case "/register": {
                resp.setContentType("text/html");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            default: {
                resp.sendError(404);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var loginSubmit = req.getParameter("loginSubmit");
        var registerSubmit = req.getParameter("registerSubmit");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (loginSubmit != null && registerSubmit == null) {

            if (username != null && password != null) {
                User user = new LoginService().login(username, password);
                if (user != null && user.getId() != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("id", user.getId());
                    session.setAttribute("username", user.getUsername());
                    resp.sendRedirect(req.getContextPath() + "/profile");
                    return;
                } else {
                    req.setAttribute("errorText", "Incorrect username or password");
                    resp.setContentType("text/html");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    return;
                }
            } else {
                req.setAttribute("errorText", "Incorrect username or password");
                resp.setContentType("text/html");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
        } else if (loginSubmit == null && registerSubmit != null) {
            if (username != null && password != null) {
                User user = new LoginService().register(username, password);

                if (user != null && user.getId() != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("id", user.getId());
                    session.setAttribute("username", user.getUsername());
                    resp.sendRedirect(req.getContextPath() + "/profile");
                    return;
                } else {
                    req.setAttribute("errorText", "Username is already taken");
                    resp.setContentType("text/html");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    return;
                }
            } else {
                req.setAttribute("errorText", "Username is already taken");
                resp.setContentType("text/html");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
        }

        resp.sendError(400);
    }
}
