package com.vetrov.fastnet.web.command.admin;

import com.vetrov.fastnet.Path;
import com.vetrov.fastnet.db.entity.User;
import com.vetrov.fastnet.db.services.*;
import com.vetrov.fastnet.web.command.ICommand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;


public class ProfileCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();

        String forward = Path.PAGE_PROFILE;

        if (session.getAttribute("newUser") != null) {
            User newUser = (User) session.getAttribute("newUser");
            request.setAttribute("fullUser", newUser);
        }

        if (request.getParameter("user_id") != null) {
            long id = Long.parseLong(request.getParameter("user_id"));
            show(request, id);
        }

        if (servletContext.getAttribute("user_id") != null) {
            Long id = (Long) servletContext.getAttribute("user_id");
            show(request, id);
        }
        return forward;
    }

    private void show(HttpServletRequest request, long id) {
        IUserService userService = new UserServiceImpl();
        IContactDetailsService detailsService = new ContactDetailsServiceImpl();
        IAccountService accountService = new AccountServiceImpl();

        User user = userService.find(id);
        user.setRoleId(user.getRoleId());
        user.setDetails(detailsService.find(user.getDetails().getId()));
        user.setAccount(accountService.find(user.getAccount().getId()));
        user.setTariffs(new HashSet<>(userService.findUserTariffs(user)));
        request.setAttribute("fullUser", user);
    }
}
