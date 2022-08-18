package com.vetrov.fastnet.web.command.common;

import com.vetrov.fastnet.Path;
import com.vetrov.fastnet.web.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;


public class LogoutCommand implements ICommand {
    private static final Logger log = Logger.getLogger(LogoutCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("ICommand starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("ICommand finished");
        return Path.PAGE_INDEX;
    }
}
