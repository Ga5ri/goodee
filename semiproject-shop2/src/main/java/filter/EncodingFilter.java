package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		if(request instanceof HttpServletRequest) {	// 형 변환 해도 되는지 검사 -> instanceof 연산자
			
			HttpServletRequest req = (HttpServletRequest) request;
			if(req.getMethod().equals("POST")) {
				
				// 인코딩 : UTF-8
				req.setCharacterEncoding("UTF-8");
			}
		}
		
		// 디버깅
		// System.out.println("UTF-8 인코딩");
		
		chain.doFilter(request, response);	
	
	}


}
