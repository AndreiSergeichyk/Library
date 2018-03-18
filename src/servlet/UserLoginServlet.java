package servlet;

import entity.User;
import service.UserService;
import util.JspPath;
import validate.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("user-login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        List<String> validateResult = UserValidator.getInstance().validate(name, password);
        if (validateResult.isEmpty()) {
            User user = UserService.getInstance().login(name, password);
            if (user != null) {
                resp.sendRedirect("/saveSuccesfulServlet");
            } else {
                getServletContext()
                        .getRequestDispatcher("/registrationServlet")
                        .forward(req, resp);
            }
        } else {
            req.setAttribute("errors", validateResult);
            req.setAttribute("name", name);
            req.setAttribute("password", password);

            getServletContext()
                    .getRequestDispatcher(JspPath.get("save-request"))
                    .forward(req, resp);
        }
    }
}
