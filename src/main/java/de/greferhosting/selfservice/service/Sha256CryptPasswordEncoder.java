package de.greferhosting.selfservice.service;

import lombok.SneakyThrows;
import org.apache.commons.codec.digest.Sha2Crypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

public class Sha256CryptPasswordEncoder implements PasswordEncoder {

    @Override
    @SneakyThrows
    public String encode(CharSequence rawPassword) {
        return Sha2Crypt.sha256Crypt(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    @SneakyThrows
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String s = Sha2Crypt.sha256Crypt(rawPassword.toString().getBytes(StandardCharsets.UTF_8), encodedPassword);

        return encodedPassword.equals(s);
    }

}
