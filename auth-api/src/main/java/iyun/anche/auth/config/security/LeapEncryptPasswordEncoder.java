package iyun.anche.auth.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义加密算法
 */
public class LeapEncryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        String rawPassword = (String) charSequence;
        System.out.println("strEncode=" + rawPassword);
        return (String) charSequence;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        String strRawPassword = (String) rawPassword;
        System.out.println("match raw=" + strRawPassword + "\tencode=" + encodePassword);

        boolean key= encodePassword.equals((String) rawPassword);

        System.out.println("result=" + key);
        return key;
    }
}
