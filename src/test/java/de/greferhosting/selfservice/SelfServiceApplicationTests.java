package de.greferhosting.selfservice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SelfServiceApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void testPasswordEncoder() {
        assertThat(passwordEncoder.matches("test", "{SHA512-CRYPT}$6$b3UqoMhksPFG6vU7$ZG.3NIpcGxmtpCosK8pYsUtVrBxsFZtOZHvMjCQLMp3ACEiJRlir8U7daKGJCfe07MVTb1Z6zBs1hUei/u8Xr0")).isTrue();
        assertThat(passwordEncoder.matches("test", "{SHA512-CRYPT}$6$196Z2mALq4wakPZx$u85pkArXz4E5OksRCV..KKbdWMJb2aZ/B3DgGxgFk4yK0vyI//Xt84VqOJsVDRC9r41LNJoaoHocnuQenKJeQ0")).isTrue();
    }
}
