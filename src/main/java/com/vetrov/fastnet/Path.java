package com.vetrov.fastnet;

public final class Path {
    // pages
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_MAIN = "/WEB-INF/jsp/admin/main.jsp";
    public static final String PAGE_PROFILE = "/WEB-INF/jsp/admin/profile.jsp";
    public static final String PAGE_ACCOUNT = "/WEB-INF/jsp/client/account.jsp";

    public static final String COMMAND_REDIRECT = "redirect";

    // admin commands
    public static final String COMMAND_MAIN = "controller?action=main";

    public static final String COMMAND_PROFILE = "controller?action=profile";

    // client commands
    public static final String COMMAND_ACCOUNT = "controller?action=account";

    // i18n
    public static final String LOCALE_NAME_RU = "ru";
    public static final String LOCALE_NAME_EN = "en";
}
