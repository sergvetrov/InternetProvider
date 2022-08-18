package com.vetrov.fastnet.web.listener;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class ContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(ContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log("Servlet context initialization starts");

        ServletContext servletContext = sce.getServletContext();
        initLog4J(servletContext);
        initCommandFactory();
        initI18N(servletContext);

        log("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log("Servlet context destruction starts");
        // do nothing
        log("Servlet context destruction finished");
    }

    /**
     * Initializes i18n subsystem.
     */
    private void initI18N(ServletContext servletContext) {
        log.info("I18N subsystem initialization started");

        String localesValue = servletContext.getInitParameter("locales");
        if (localesValue == null || localesValue.isEmpty()) {
            log.warning("'locales' init parameter is empty, the default encoding will be used");
        } else {
            List<String> locales = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(localesValue);
            while (st.hasMoreTokens()) {
                String localeName = st.nextToken();
                locales.add(localeName);
            }

            log.info("Application attribute set: locales --> " + locales);
            servletContext.setAttribute("locales", locales);
        }

        log.info("I18N subsystem initialization finished");
    }

    /**
     * Initializes CommandFactory.
     */
    private void initCommandFactory() {
        log.info("ICommand container initialization started");

        // initialize commands container
        // just load class to JVM
        try {
            Class.forName("com.vetrov.fastnet.web.command.CommandFactory");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        log.info("ICommand container initialization finished");
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        PropertyConfigurator.configure("C:\\Users\\Sergey\\IdeaProjects\\InternetProvider\\src\\main\\resources\\log4j.properties");
        log("Log4J initialization finished");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
