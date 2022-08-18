package com.vetrov.fastnet.web.filter;

import javax.servlet.*;
import java.io.IOException;
import org.apache.log4j.Logger;

public class EncodingFilter implements Filter {
    private static final Logger log = Logger.getLogger(EncodingFilter.class);
    private String encoding;

    public void destroy() {
        log.info("Filter destruction starts");
        // do nothing
        log.info("Filter destruction finished");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        log.info("Filter starts");

        String requestEncoding = req.getCharacterEncoding();

        if (requestEncoding == null) {
            log.info("Request encoding = null, set encoding --> " + encoding);
            req.setCharacterEncoding(encoding);
        }
        log.info("Filter finished");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) {
        log.info("Filter initialization starts");
        encoding = config.getInitParameter("encoding");
        log.info("Encoding from web.xml --> " + encoding);
        log.info("Filter initialization finished");
    }
}
