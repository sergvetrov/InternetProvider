package com.vetrov.fastnet.web.command.outofcontrol;

import com.vetrov.fastnet.Path;
import com.vetrov.fastnet.db.entity.Role;
import com.vetrov.fastnet.db.entity.User;
import com.vetrov.fastnet.db.services.IUserService;
import com.vetrov.fastnet.db.services.UserServiceImpl;
import com.vetrov.fastnet.utils.ReportBuilder;
import com.vetrov.fastnet.web.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginCommand implements ICommand {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // error handler
        String errorMessage;
        String forward = Path.PAGE_LOGIN;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login or password can't be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.info("Invalid login or password");
            return forward;
        }

        IUserService service = new UserServiceImpl();
        User user = service.findByLogin(login);
        log.info("User is found");

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login or password";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            Role userRole = Role.getRole(user);

            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND_MAIN;
            }

            if (userRole == Role.CLIENT) {
                forward = Path.COMMAND_ACCOUNT;
            }

            session.setAttribute("user", user);
            session.setAttribute("userRole", userRole);
            log.info("Success login");
        }
        return forward;
    }
}
