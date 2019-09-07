package de.greferhosting.selfservice.view;

import de.greferhosting.selfservice.MailUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.Objects;

@Component
@ViewScoped
public class UserBean {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailUserService mailUserService;

    public MailUserService.MailUserDetails getAccount() {
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

        mailUserService.updatePassword((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), encodedPassword);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Passwort erfolgreich geändert"));

    }
}
