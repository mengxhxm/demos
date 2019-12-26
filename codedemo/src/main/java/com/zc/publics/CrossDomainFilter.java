package com.zc.publics;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置跨域请求
 */
public class CrossDomainFilter implements Filter{
	public void destroy() {
		// TODO Auto-generated method stub
	}
 
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {    
         //设置跨域请求  
        HttpServletResponse response = (HttpServletResponse) res; 
        HttpServletRequest request=(HttpServletRequest)req;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,Token,Accept, Connection, User-Agent, Cookie,Authorization");
        response.setHeader("Access-Control-Max-Age", "3628800");   
        chain.doFilter(req, response);    
    }
 
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}

