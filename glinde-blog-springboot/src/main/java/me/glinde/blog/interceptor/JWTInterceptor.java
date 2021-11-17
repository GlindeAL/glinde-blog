package me.glinde.blog.interceptor;

import com.alibaba.fastjson.JSON;
import me.glinde.blog.util.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String,Object> res = new HashMap<>();
        String token = request.getHeader("token");

        try{
            JWTUtils.verify(token);
            return true;
        }catch (Exception e){
            res.put("msg",-1);
        }
        res.put("flag","error");

        String res_json = JSON.toJSONString(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(res_json);

        return false;
    }
}
