package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;


public class AuthorizationFilter implements Filter {

       
                
    
	public AuthorizationFilter() { 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
                        String reqURI1 = reqt.getRequestURI();
                        String urlStr = reqt.getRequestURI().toString().toLowerCase();
                            
                       if (reqURI.indexOf("/login.xhtml") >= 0
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource")){
				chain.doFilter(request, response);
                             
                                
                        }else{
			resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
                        }
               
                                
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
        
        private boolean noProteger(String urlStr){

            if (urlStr.endsWith("registro.xhtml")){
               
                return true;
            }
      
            
            return false;
        }
        
        public void login() {

	}
      
}