package una.bolsadeempleo.logic.DTO;

public class PasswordRequestDTO {
    private Integer id;
    private String password;

    public PasswordRequestDTO() {
    }

    public PasswordRequestDTO(Integer id, String password) {
        this.id = id;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
