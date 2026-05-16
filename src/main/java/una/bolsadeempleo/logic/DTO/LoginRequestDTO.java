package una.bolsadeempleo.logic.DTO;

public class LoginRequestDTO {

    private String correo;
    private String password;

    public LoginRequestDTO() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}