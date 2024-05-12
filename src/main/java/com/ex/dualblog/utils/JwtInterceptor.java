package com.ex.dualblog.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.ex.dualblog.service.UserService;
import com.ex.dualblog.model.User;
import com.ex.dualblog.schema.Result;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    // private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private UserService userService;
    
    // private RedisTemplate<String, Object> redisTemplate;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        // 1. 从http请求的header中获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            // 如果没拿到，我再去参数里面拿一波试试  /api/admin?token=xxxxx
            token = request.getParameter("token");
        }
        // 2. 开始执行认证
        if (StrUtil.isBlank(token)) {
            // throw new CustomException("无token，请重新登录");
            returnNoLogin(response);
            return false;
        }
        // 获取 token 中的userId
        String userId;
        User user;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            // 根据token中的userid查询数据库
            user = userService.findUserByID(userId);
        } catch (Exception e) {
            // String errMsg = "token验证失败，请重新登录";
            // log.error(errMsg + ", token=" + token, e);
            returnNoLogin(response);
            // throw new CustomException(errMsg);
            return false;
        }

        if (user == null) {
            // throw new CustomException("用户不存在，请重新登录");
            returnNoLogin(response);
            return false;
        }
        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            // throw new CustomException("token验证失败，请重新登录");
            returnNoLogin(response);
            return false;
        }

        if(!userService.findTokenbyUUID(userId)){ // token不在白名单中
            returnNoLogin(response);
            return false; 
        }
        return true;
    }

    private void returnNoLogin(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        // 设置返回401 和响应编码
        response.setStatus(401);
        response.setContentType("Application/json;charset=utf-8");
        var err = Result.error("5003","未登陆，请先登陆");
        // 构造返回响应体
        // Result<String> result = Result.<String>builder()
        //         .code(HttpStatus.UNAUTHORIZED.value())
        //         .errorMsg("未登陆，请先登陆")
        //         .build();
        String resultString = JSONUtil.toJsonStr(err);
        System.out.println(resultString);
        outputStream.write(resultString.getBytes(StandardCharsets.UTF_8));
    }

}