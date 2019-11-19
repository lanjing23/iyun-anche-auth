package iyun.anche.auth.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import iyun.anche.auth.response.BaseResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class HttpUtils {

    public static void writerError(BaseResponse bs, HttpServletResponse response) throws IOException {
        response.setContentType("application/json,charset=utf-8");
        response.setStatus(bs.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(),bs);
    }

}
