package de.greferhosting.selfservice.service;

import de.greferhosting.selfservice.entity.Account;
import de.greferhosting.selfservice.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;


@Service
public class MailUserService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] split = username.split("@");

        Account account = userRepository.findByUsernameAndDomain(split[0], split[1])
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new MailUserDetails(account);
    }

    @Override
    @Transactional
    public UserDetails updatePassword(UserDetails user, String newPassword) {

        Account account = userRepository.findById(((MailUserDetails) user).getAccount().getId()).get();

        account.setPassword(newPassword);

        account = userRepository.saveAndFlush(account);

        return new MailUserDetails(account);
    }

    @RequiredArgsConstructor
    public static class MailUserDetails implements UserDetails {
        @Getter
        private final Account account;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
        }

        @Override
        public String getPassword() {
            return account.getPassword();
        }

        @Override
        public String getUsername() {
            return account.getUsername() + "@" + account.getDomain();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return account.isEnabled();
        }
    }
}
