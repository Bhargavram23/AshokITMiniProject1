package com.RamaIT.security;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class requestInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if ("/login".equals(request.getRequestURI()) || "/register".equals(request.getRequestURI())) {
			return true;
		}
		if (request.getSession().getAttribute("counsellor") != null) {
			return true;

		}
		else {
		response.sendRedirect("/login");
		}
		return false;
	}
}
