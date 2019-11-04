package com.honji.exhibition.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class SessionTimeoutInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {

        HttpSession session = request.getSession();
        Object admin = session.getAttribute("admin");

        if (admin != null) {
            //TODO 校验用户是否存在？
            return true;
        }
        response.sendRedirect("/admin/toLogin");

        return false;
    }

}
