package de.greferhosting.selfservice.view;

import de.greferhosting.selfservice.service.MailUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.util.Objects;

@Component
@ViewScoped
public class UserBean {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailUserService mailUserService;

    public MailUserService.MailUserDetails getUser() {
        return (MailUserService.MailUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Getter
    @Setter
    private String password1, password2;

    public void updatePassword() {

        if (!Objects.equals(password1, password2)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwörter stimmen nicht überein", null));

            return;
        }

        String encodedPassword = passwordEncoder.encode(password1);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mailUserService.updatePassword(userDetails, encodedPassword);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Passwort erfolgreich geändert"));

    }
}
