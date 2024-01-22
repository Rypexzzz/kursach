package servlets;

import models.*;
import services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/article/*"})
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String[] pathList = path.split("/");

        try {
            switch (pathList[1]) {
                case "all": {
                    List<Article> articles = new ArticleDAO().getAll();
                    req.setAttribute("articles", articles);
                    resp.setContentType("text/html");
                    req.getRequestDispatcher("/allArticles.jsp").forward(req, resp);
                    return;
                }
                default: {
                    int articleID = Integer.parseInt(pathList[1]);
                    Article article = new ArticleDAO().getByID(articleID);
                    if (article == null) {
                        resp.sendError(403);
                        return;
                    }
                    req.setAttribute("article", article);
                    List<Comment> comments = new CommentDAO().getByArticle(articleID);
                    req.setAttribute("comments", comments);
                    List<Coauthor> coauthors = new CoauthorDAO().getByArticle(articleID);
                    req.setAttribute("coauthors", coauthors);
                    List<Source> sources = new SourceDAO().getByArticle(articleID);
                    req.setAttribute("sources", sources);
                    boolean yours = (article.getAuthor() == (int) req.getSession().getAttribute("id"));
                    if (yours) {
                        List<User> users = new UserDAO().getAll();
                        req.setAttribute("users", users);
                    }
                    req.setAttribute("yours", yours);
                    resp.setContentType("text/html");
                    req.getRequestDispatcher("/article.jsp").forward(req, resp);
                    return;
                }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String[] pathList = path.split("/");


        try {
            int articleID = Integer.parseInt(pathList[1]);
            Article article = new ArticleDAO().getByID(articleID);
            if (article != null) {
                Object delete = req.getParameter("delete");
                Object comment = req.getParameter("comment");
                Object coauthor = req.getParameter("coauthor");
                Object source = req.getParameter("source");

                if (delete != null && comment == null && coauthor == null && source == null) {
                    new ArticleDAO().delete(articleID);
                    resp.sendRedirect(req.getContextPath() + "/article/all");
                    return;
                } else if (delete == null && comment != null && coauthor == null && source == null) {
                    String text = req.getParameter("text");
                    if (text != null) {
                        new CommentDAO().create(new Comment(articleID, (int) req.getSession().getAttribute("id"), text));
                    }
                    resp.sendRedirect(req.getContextPath() + "/article/" + articleID);
                    return;
                } else if (delete == null & comment == null && coauthor != null && source == null) {
                    int coauthorID = Integer.parseInt(req.getParameter("coauthorID"));
                    new CoauthorDAO().create(new Coauthor(articleID, coauthorID));
                    resp.sendRedirect(req.getContextPath() + "/article/" + articleID);
                    return;
                } else if (delete == null & comment == null && coauthor == null && source != null) {
                    String sourceText = req.getParameter("sourceText");
                    new SourceDAO().create(new Source(articleID, sourceText));
                    resp.sendRedirect(req.getContextPath() + "/article/" + articleID);
                    return;
                } else {
                    resp.sendError(405);
                    return;
                }
            } else {
                resp.sendError(403);
                return;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            resp.sendError(405);
        }
    }
}
