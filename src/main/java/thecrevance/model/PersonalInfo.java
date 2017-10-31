package thecrevance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import thecrevance.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Greg on 10/31/17.
 */
@Embeddable
public class PersonalInfo implements Serializable{

    private static final long serialVersionUID = -4515291880483274385L;

    @NotNull
    @Pattern(regexp="(\\+)?[0-9]{11,20}$", message="{phoneNumber.valid}")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Past
    @NotNull
    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @NotNull
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NotNull
    @Column(name = "address")
    private String address;

    public Date getDateOfBirth() {
        if (dateOfBirth == null) {
            return null;
        }
        return new Date(dateOfBirth.getTime());
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            this.dateOfBirth = null;
        } else {
            this.dateOfBirth = new Date(dateOfBirth.getTime());
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PersonalInfo() {}

    @Transient
    @JsonIgnore
    public boolean isPersonalInfoUpdated(){
        return sex != null &&
                phoneNumber != null &&
                dateOfBirth != null &&
                address != null;
    }

    public PersonalInfo(String phoneNumber, Date dateOfBirth, Sex sex, String address) {
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        this.sex = sex;
        this.address = address;

    }
}
