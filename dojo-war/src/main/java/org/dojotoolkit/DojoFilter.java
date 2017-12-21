
package org.dojotoolkit;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class DojoFilter implements Filter {
    private String _cacheControl = "public, max-age=2419200";

    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        String tmp = context.getInitParameter("cacheControl");
        if (tmp != null) {
            _cacheControl = tmp;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse)response).setHeader("Cache-Control", _cacheControl);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
