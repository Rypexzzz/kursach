package servlets;

import models.Article;
import services.ArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        HttpSession session = req.getSession();
        req.setAttribute("username", session.getAttribute("username"));
        List<Article> articles = new ArticleDAO().getByAuthor((int) session.getAttribute("id"));
        req.setAttribute("articles", articles);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");

        if (req.getParameter("publish") != null) {
            if (title != null && text != null) {
                new ArticleDAO().create(new Article((int) req.getSession().getAttribute("id"), title, text));
            }
            resp.sendRedirect(req.getContextPath() + "/profile");
            return;
        }
    }
}
