package thecrevance.model;

import thecrevance.enums.AccountType;
import thecrevance.enums.EmploymentStatus;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class UserBankDetails implements Serializable {

    private static final long serialVersionUID = -4515291880483274385L;

    @NotNull
    @Column(name = "bank_name")
    private String bank;

    @NotNull
    @Pattern(regexp="^\\d+$", message="{accountNumber.valid}")
    @Column(name = "account_number")
    private String account_number;

    @NotNull
    @Column(name = "account_name")
    private String account_name;

    @NotNull
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    @NotNull
    @Pattern(regexp="^\\d+$", message="{bvn.valid}")
    @Column(name = "bvn")
    private String bvn;

    @NotNull
    @Column(name = "name_of_employer")
    private String nameOfEmployer;

    @NotNull
    @Column(name = "employment_status")
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @NotNull
    @Column(name = "monthly_salary")
    private BigDecimal monthlySalary;



    public UserBankDetails() {
    }

    public UserBankDetails(String bank, AccountType account_type, String account_number, String account_name, String bvn) {
        this.bank = bank;
        this.account_type = account_type;
        this.account_number = account_number;
        this.account_name = account_name;
        this.bvn = bvn;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public AccountType getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountType account_type) {
        this.account_type = account_type;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getNameOfEmployer() {
        return nameOfEmployer;
    }

    public void setNameOfEmployer(String nameOfEmployer) {
        this.nameOfEmployer = nameOfEmployer;
    }
}

