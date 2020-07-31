package com.urms.interceptor;

import com.urms.exception.PermissionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 吴权鸿
 */
@Configuration
public class LoginInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration ir = registry.addInterceptor(new LoginFilter());
        String[] allowPaths = new String[]{
                "/static/**",
                "/css/**",
                "/js/**",
                "/login.html",
                "/#/login",
                "/login",
                "/user/no/permission"
        };
        for (String path : allowPaths){
            ir.excludePathPatterns(path);
        }

        ir.addPathPatterns("/**");
    }

    private class LoginFilter implements HandlerInterceptor{

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
            if (!(handler instanceof HandlerMethod)){
                return true;
            }
            System.out.println(request.getRequestURI());
            boolean pass = false;
            String token = request.getHeader("token");
            String[] menus = null;
            try {
                menus =  token.split(",");
            }catch (Exception e){
//                response.sendRedirect("/#/login");
                throw PermissionException.NO_TOKEN_EXCEPTION;
            }
            String[] url = request.getRequestURI().split("/");
            String header = url[1];
            String ender = url[2];

            if (null != menus && menus.length > 0){
                for (String menu : menus ){
                    String[] uUrl = menu.split("/");
                    String uHeader = uUrl[0];
                    String uEnder = uUrl[1];
                    Boolean checked = (uHeader.equals("") || uHeader.equals(header)) && (uEnder.equals("*") || uEnder.equals(ender));
                    if (checked){
                        pass = true;
                        break;
                    }
                }
            }
//            if (pass){
//                return true;
//            }else {
////                throw PermissionException.NO_TOKEN_EXCEPTION;
//            }
            return pass;

        }
    }

}


