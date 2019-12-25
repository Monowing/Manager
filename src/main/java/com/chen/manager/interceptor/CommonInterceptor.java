package com.chen.manager.interceptor;




import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.chen.manager.annotation.CheckToken;
import com.chen.manager.annotation.PassToken;
import com.chen.manager.common.Constants;
import com.chen.manager.entity.Admin;
import com.chen.manager.service.AdminService;

/**
 * 自定义拦截器
 * 
 * created at 2019-11-26
 * 
 * @author Administrator
 *
 */
public class CommonInterceptor implements HandlerInterceptor {
	

	private static final String TOKEN_STR = "token";
	
	@Autowired
	AdminService adminService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
		boolean ajaxRequest = isAjaxRequest(request);
		
		// 从 http 请求头中取出 token
		String token = ajaxRequest?request.getHeader(TOKEN_STR):request.getParameter(TOKEN_STR);
        
		// 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken userLoginToken = method.getAnnotation(CheckToken.class);
            System.out.println("chek");
            
            if (userLoginToken.required()) {
                
            	
            	// 执行认证
                if (token == null) {
                	if(!ajaxRequest){
                		response.sendRedirect(request.getContextPath() + "/login");
                		return true;
                	}
                	throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String adminId;
                try {
                    adminId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                	if(!ajaxRequest){
                		response.sendRedirect(request.getContextPath() + "/login");
                		return true;
                	}
                    throw new RuntimeException("401");
                }
                System.out.println(adminId);
                Admin admin = adminService.get(Long.valueOf(adminId)).get();
                if (adminId == null || admin == null) {
                	if(!ajaxRequest){
                		response.sendRedirect(request.getContextPath() + "/login");
                		return true;
                	}
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                if(!token.equals(admin.getToken())){
                	if(!ajaxRequest){
                		response.sendRedirect(request.getContextPath() + "/login");
                		return true;
                	}
                	throw new RuntimeException("token过期，请重新登录");
                }
                /*// 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassWord())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }*/
                return true;
            }
        }
        
        // 只有返回true才会继续向下执行，返回false取消当前请求
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		 System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
		
		if (modelAndView != null && !isAjaxRequest(request)) {
//			System.out.println(response.getStatus());
			if (response.getStatus() == Constants.ERROR_PAGE_STATUS_CODE_500) {
				modelAndView.setViewName(Constants.ERROR_PAGE_URL_500);
			} else if (response.getStatus() == Constants.ERROR_PAGE_STATUS_CODE_404) {
				modelAndView.setViewName(Constants.ERROR_PAGE_URL_500);
			}	else if (response.getStatus() == Constants.ERROR_PAGE_STATUS_CODE_401) {
				modelAndView.setViewName(Constants.ERROR_PAGE_URL_401);
			}
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}

	/**
	 * 判断是否为ajax请求
	 * @param request
	 * @return
	 */
	public boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
		return isAjax;
	}

}