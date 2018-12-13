package com.se.fishbook.interceptor;

import com.se.fishbook.model.User;
import com.se.fishbook.util.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interceptor for user validation
 */
public class SysInterceptor implements HandlerInterceptor {

    //validate user information before controller
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            //response.sendRedirect(request.getContextPath()+"/index");
            return true;
        } else {
            return true;
        }
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (response.getStatus() == 500) {
            modelAndView.setViewName("/error/500");
        } else if (response.getStatus() == 404) {
            modelAndView.setViewName("/error/404");
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}  