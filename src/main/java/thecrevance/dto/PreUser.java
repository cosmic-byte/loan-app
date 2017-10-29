package thecrevance.dto;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Document(indexName = "crevance-index", type = "user")
public class PreUser {


    @Id
    private Long id;

    @NotNull
    @Size(min=2, max=30, message="The length lastname should be within the range of 2 to 30.")
    private String firstname;

    @NotNull
    @Size(min=2, max=30, message="The length of password should be within the range of 2 to 30.")
    private String lastname;

    @NotNull
    @Email(message = "Email is not valid.")
    private String email;

    @NotNull
    @Size(min=2, max=30, message="The length of password should be within the range of 2 to 30.")
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public PreUser() {
    }

    public PreUser(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
