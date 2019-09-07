package de.greferhosting.selfservice.service;

import lombok.SneakyThrows;
import okio.Okio;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DoveadmPasswordEncoder implements PasswordEncoder {

    @Override
    @SneakyThrows
    public String encode(CharSequence rawPassword) {

        Process process = Runtime.getRuntime().exec(new String[]{
                "doveadm", "pw", "-s", "SHA512-CRYPT", "-p", rawPassword.toString()
        });

        process.waitFor();

        return Okio.buffer(Okio.source(process.getInputStream())).readByteString().utf8().strip();
    }

    @Override
    @SneakyThrows
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Process process = Runtime.getRuntime().exec(new String[]{
                "doveadm", "pw", "-t", encodedPassword, "-p", rawPassword.toString()
        });

        return process.waitFor() == 0;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return !encodedPassword.startsWith("{SHA512-CRYPT}");
    }
}
