package ua.nure.moskovchenko.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * This filter specifies the encoding for all the input which is send to a servlet for processing.
 * UTF-8 encoding lets the software recognize most of non-ASCII characters, so it could process them,
 * store and show on the screen properly.
 */
public class EncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);
    private static final String DEFAULT_ENCODING = "UTF-8";
    private String encoding;

    /**
     * Sets the specified encoding to the request and response objects that transfer from one point to another.
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding(encoding);

//        LOG.info("Char encoding has been set");
        chain.doFilter(req, resp);
    }

    /**
     * Sets filter encoding to the one specified in web.xml.
     * If it is not specified, it utilizes the default one, which is UTF-8.
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
            LOG.info("Encoding from Filter Config --> " + encoding);
        }
        LOG.info("Filter initialization finished");
    }

}
