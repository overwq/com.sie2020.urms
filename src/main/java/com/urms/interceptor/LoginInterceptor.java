package com.urms.interceptor;

import com.urms.exception.PermissionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author 吴权鸿
 */
@Configuration
public class LoginInterceptor implements WebMvcConfigurer {
    private static String[] allowPaths = new String[]{
            "/static/**",
            "/css/**",
            "/js/**",
            "/login.html",
            "/#/login",
            "/login",
            "/user/no/permission"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration ir = registry.addInterceptor(new LoginFilter());
        for (String path : allowPaths){
            ir.excludePathPatterns(path);
        }
        ir.addPathPatterns("/**");
    }

    private class LoginFilter implements HandlerInterceptor{


        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            if (!(handler instanceof HandlerMethod)){
                return true;
            }
            boolean pass = false;
            String token = request.getHeader("token");
            String[] menus = null;
            String header = null;
            String ender = null;
            String[] url = request.getRequestURI().split("/");
            try {
                menus =  token.split(",");
                header = url[1];
                ender = url[2];
            }catch (Exception e){
                System.out.println("******获取菜单信息或菜单地址时发生异常 ！*******");
                return false;
//                throw PermissionException.NO_TOKEN_EXCEPTION;
            }
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
            return pass;

        }
    }

}


