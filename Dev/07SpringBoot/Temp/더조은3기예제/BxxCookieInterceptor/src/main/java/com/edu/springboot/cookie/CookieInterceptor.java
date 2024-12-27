package com.edu.springboot.cookie;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().equals(request.getContextPath() + "/")) {
            return true;
        }
        
        Cookie[] cookies = request.getCookies();
        boolean hasCookie = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("WineVillageCookie")) {
                    hasCookie = true;
                    break;
                }
            }
        }

        if (!hasCookie) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        return true;
    }
}


/*

package com.winevillage.winevillage.cookie;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().equals(request.getContextPath() + "/")) {
            return true;
        }
        
        Cookie[] cookies = request.getCookies();
        boolean hasCookie = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("WineVillageCookie")) {
                    hasCookie = true;
                    break;
                }
            }
        }

        if (!hasCookie) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        return true;
    }
}
// cookieinterceptor.java

*/