package servlet;

import dto.UserDto;
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

@WebServlet("/registrationServlet")
public class UserRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("/user-registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String mailBox = req.getParameter("addresMailBox");

        UserDto userDto = new UserDto(name, password, mailBox);
        List<String> validateResult = UserValidator.getInstance().validate(name, password, mailBox);
        if (validateResult.isEmpty()) {
            User user = UserService.getInstance().save(userDto);
            if (user != null) {
                resp.sendRedirect("/saveSuccesfulServlet");
            } else {
                getServletContext()
                        .getRequestDispatcher(JspPath.get("user-registration"))
                        .forward(req, resp);
            }
        } else {
            req.setAttribute("errors", validateResult);
            req.setAttribute("name", name);
            req.setAttribute("password", password);
            req.setAttribute("addresMailBox", mailBox);

            getServletContext()
                    .getRequestDispatcher(JspPath.get("user-registration"))
                    .forward(req, resp);
        }
    }
}
