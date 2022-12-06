package com.ayotycoon.security;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by oa on 6/2/2019.
 */
@Slf4j
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {


        log.info(request.getRemoteAddr()  +" accessed resource "+request.getRequestURI()+" @ "+getCurrentTime());
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime); //inject new attribute 'startTime' in request header

        //responseHeader can be modified similiarly as per need

        /*Take action base on incoming IP
        Suppose you want to redirect all ip to auth-failed
        which ip value starts with "192"
        */
//        if(request.getRemoteAddr().startsWith("192")) {
//            response.sendRedirect("/auth-failed"); //redirect to default
//            return false; //returning false ensure that the request is not
//            // further required to be intercepted,
//            // response is directly send to the user hereafter.
//        }
        return  true;
        //return new LogInterceptor().preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Request Processing ends on "+getCurrentTime());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {


        long endTime = System.currentTimeMillis();
        long startTime=Long.parseLong(request.getAttribute("startTime")+"");
        log.info("Total time taken to process request (in milliseconds(ms)) "+(endTime-startTime)+" ms");

    }


    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }

}
