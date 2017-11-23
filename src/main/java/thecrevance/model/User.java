package thecrevance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User implements UserDetails {
    private static final long serialVersionUID = -4515291880483274385L;

    @Transient
    private LocalDateTime now = LocalDateTime.now();

    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Role role;
    @Transient
    private long expires;
    private LocalDateTime lastLogin = now;
    private boolean enabled = true;
    private LocalDateTime createdAt = now;
    private LocalDateTime modifiedAt = now;
    private boolean deleted = false;

    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private UserBankDetails userBankDetails;

    public User(String firstname, String lastname, String password, String email, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String firstname, String lastname, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }

    public User(User user) {
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.password = user.password;
        this.email = user.email;
    }

    public User() {
    }

    @Transient
    @JsonIgnore
    public boolean isPersonalInfoUpdated(){
        return personalInfo != null ;
    }

    @Transient
    @JsonIgnore
    public boolean isBankDetailsUpdated(){
        return userBankDetails != null;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min=2, max=30, message="The length of firstname should be within the range of 2 to 30.")
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @NotNull
    @Size(min=2, max=30, message="The length lastname should be within the range of 2 to 30.")
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @NotNull
    @Size(min=2, max=100, message="The length of password should be within the range of 2 to 30.")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", unique = true)
    @Email(message = "Email is not valid.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name="role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "created_at")
    @JsonIgnore
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "modified_at")
    @JsonIgnore
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Column(name = "expires")
    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @Column(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    @Column(name = "last_login")
    @JsonIgnore
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;

    }

    @Transient
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role != null) {
            String roleName = role.getName();
            if (!roleName.startsWith("ROLE_")) {
                roleName = "ROLE_" + roleName;
            }
            authorities.add(new SimpleGrantedAuthority(roleName));
        }
        return authorities;
    }

    @Transient
    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Transient
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public UserBankDetails getUserBankDetails() {
        return userBankDetails;
    }

    public void setUserBankDetails(UserBankDetails userBankDetails) {
        this.userBankDetails = userBankDetails;
    }
}