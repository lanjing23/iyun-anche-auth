package iyun.anche.auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import iyun.anche.auth.common.enumeration.AuthExceptionEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 无效的token自定义返回值
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint
{
    /**
     * @param response 返回值
     * {
     *     "msg": "无效的token",
     *     "path": "/leap/bus",
     *     "code": 401,
     *     "data": "Invalid access token: 79888fb5-e878-4836-bf85-0e99efaf45391",
     *     "success": false,
     *     "timestamp": "1571043526661"
     * }
     * {
     *     "msg": "访问此资源需要token验证",
     *     "path": "/leap/bus",
     *     "code": 401,
     *     "data": "Full authentication is required to access this resource",
     *     "success": false,
     *     "timestamp": "1571043648579"
     * }
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Map<String, Object> map = new HashMap<String, Object>();

        String msg = authException.getMessage();

        if (msg.equals(AuthExceptionEnum.AUTHORIZED_FAIL.getRaw())) {
            map.put("code", 0);
            map.put("msg", AuthExceptionEnum.AUTHORIZED_FAIL.getMsg());
        } if (msg.contains(AuthExceptionEnum.INVALID_ACCESS_TOKEN.getRaw())) {
            map.put("code", 0);
            map.put("msg", AuthExceptionEnum.INVALID_ACCESS_TOKEN.getMsg());
        } else {
            map.put("code", 0);
        }

//        Throwable cause = authException.getCause();
//        if(cause instanceof InvalidTokenException) {
////            map.put("code", RespCode.INVALID_TOKEN);//401
//            map.put("code", 401);//401
//            map.put("msg", "无效的token");
//        } else {
////            map.put("code", RespCode.UN_LOGIN);//401
//            map.put("code", 401);//401
//            map.put("msg", "访问此资源需要token验证");
//        }
        map.put("data", authException.getMessage());
        map.put("success", false);
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(new Date().getTime()));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
