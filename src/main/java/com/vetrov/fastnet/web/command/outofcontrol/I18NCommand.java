package com.vetrov.fastnet.web.command.outofcontrol;

import com.vetrov.fastnet.Path;
import com.vetrov.fastnet.web.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;


public class I18NCommand implements ICommand {

    private static final org.apache.log4j.Logger log = Logger.getLogger(I18NCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
        String defaultLocale = "defaultLocale";

        if (request.getParameter("ru") != null) {
            Config.set(session, fmtLocale, Path.LOCALE_NAME_RU);
            session.setAttribute(defaultLocale, "ru");
            log.info("Language changed on RU");
        } else {
            Config.set(session, fmtLocale, "en");
            session.setAttribute(defaultLocale, Path.LOCALE_NAME_EN);
            log.info("Language changed on EN");
        }
        return Path.PAGE_INDEX;
    }
}