package thecrevance.dto;

/**
 * Created by greg on 10/11/17.
 */
public class UserRole {
    private String token;
    private String roles;

    public UserRole(String token, String roles) {
        this.token = token;
        this.roles = roles;
    }

    public UserRole() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
