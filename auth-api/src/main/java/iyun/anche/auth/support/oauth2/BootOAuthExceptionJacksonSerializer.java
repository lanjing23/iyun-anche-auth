package iyun.anche.auth.support.oauth2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * BootOAuth2Exception 序列化，捕捉认证过程中的异常OAuth2Exception，此处格式化异常内容，并返回
 * 调用方：BootOAuth2WebResponseExceptionTranslator
 * 注册方：AuthorizationServerConfig
 *        endpoints.exceptionTranslator(webResponseExceptionTranslator());
 *
 * http://localhost:8081/oauth/token?refresh_token=b12ca201-1b8a-4ddf-a3c7-52446a813f22q&grant_type=refresh_token
 * 该命令如果refresh_token无效的情况下，会进入该方法，此处可自定义提示内容，默认返回英文定义
 * {
 *     "code": 400,
 *     "msg": "Invalid refresh token: b12ca201-1b8a-4ddf-a3c7-52446a813f22q"
 * }
 */
public class BootOAuthExceptionJacksonSerializer extends StdSerializer<BootOAuth2Exception> {

    protected BootOAuthExceptionJacksonSerializer() {
        super(BootOAuth2Exception.class);
    }

    @Override
    public void serialize(BootOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("code", value.getHttpErrorCode());
//        String errorMessage = value.getOAuth2ErrorCode();
        String errorMessage = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        jgen.writeStringField("msg", errorMessage);
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }
}
