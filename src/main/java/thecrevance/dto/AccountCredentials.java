package thecrevance.dto;

import javax.validation.constraints.NotNull;

public class AccountCredentials {

    @NotNull
    private String email;
    @NotNull
    private String password;

    public AccountCredentials() {
    }

    public AccountCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
