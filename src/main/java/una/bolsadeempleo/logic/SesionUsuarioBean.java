package una.bolsadeempleo.logic;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SesionUsuarioBean {
    private String email;
    private Boolean verified;
    private String rol;

    public void login(String email, String rol) {
        this.email = email;
        this.verified = true;
        this.rol = rol;
    }

    public void logout() {
        email = null;
        verified = false;
        rol = null;
    }

    public Boolean isLogged() {
        return email != null;
    }

    public Boolean isVerified() {
        return verified != null && verified;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }


}

