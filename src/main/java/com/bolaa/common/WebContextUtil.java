package com.bolaa.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bolaa.interceptor.MySessionContext;
import com.bolaa.manage.entity.SysUser;

/**
 * @decription
 * @author Administrator
 * @date 2017年5月14日 下午3:57:09
 */
public class WebContextUtil extends ContextLoaderListener {

    private static MySessionContext myc = MySessionContext.getInstance();

    private static final String WEB_CURRENT_USER = "WEB_CURRENT_USER"; // 前端用户

    private static ApplicationContext applicationContext = null; // spring上下文

    private static ServletContext servletContext = null; // servlet容器

    /**
     * spring容器加载监听,同时提供spring容器中实例的获取
     * 
     * @param event
     */
    public void contextInitialized(ServletContextEvent event) {
	super.contextInitialized(event);
	servletContext = event.getServletContext();
	applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
    }

    /**
     * 获取spring容器的bean实例
     * 
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
	return applicationContext.getBean(beanName);
    }

    /**
     * 获取servlet容器相对路径
     * 
     * @param path
     * @return
     */
    public static String getRealPath(String path) {
	return servletContext.getRealPath(path);
    }

    /**
     * 将前端用户信息寫入session,并返回sessionId
     */
    public static String setWebUser(SysUser user) {
	HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
		.getSession();
	session.setAttribute(WEB_CURRENT_USER, user);
	myc.addSession(session);
	String id = session.getId();

	return id;
    }

    /**
     * 根据sessionId获取session
     * 
     * @return
     */
    public static SysUser getWebUser(String sessionId) {
	HttpSession session = myc.getSession(sessionId);
	if (session != null) {
	    return (SysUser) session.getAttribute(WEB_CURRENT_USER);
	}
	return null;
    }

}
