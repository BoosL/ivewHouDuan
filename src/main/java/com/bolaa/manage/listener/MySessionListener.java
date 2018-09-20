package com.bolaa.manage.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bolaa.interceptor.MySessionContext;

public class MySessionListener implements HttpSessionListener {
    private Integer sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	sessionCount++;
	MySessionContext.getInstance().addSession(httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
	sessionCount--;
	HttpSession session = httpSessionEvent.getSession();
	MySessionContext.getInstance().delSession(session);
    }

}
