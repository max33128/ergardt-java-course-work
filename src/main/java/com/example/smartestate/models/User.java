package com.example.smartestate.models;

import com.example.smartestate.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    @Column(name = "passport_number", nullable = true, unique = true)
    private Long passportNumber;

    @Column(name = "inn_number", nullable = true, unique = true)
    private Long innNumber;

    @Column(name = "license_number", nullable = true)
    private Long licenseNumber;

    @Column(name = "active")
    private boolean active;

    @Column(name = "password", length = 1000)
    private String password;

    @Column(name = "chosen_role")
    private String chosenRole;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "agent")
    private List<RealEstateObject> objectsToBuy = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "developer")
    private List<RealEstateObject> objectsToSell = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Transaction> buyTransactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "developer")
    private List<Transaction> sellTransactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Application> applications = new ArrayList<>();

    @Column(name = "only_primal", nullable = true)
    private boolean onlyPrimal;

    @Column(name = "filled_profile")
    private boolean filledProfile;

    public User() {
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isAgent() {
        return roles.contains(Role.AGENT);
    }

    public boolean isCustomer() {
        return roles.contains(Role.CUSTOMER);
    }

    public boolean isNotCustomer() {
        return !roles.contains(Role.CUSTOMER);
    }

    public boolean isDeveloper() {
        return roles.contains(Role.DEVELOPER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    @PrePersist
    public void init() {
        this.filledProfile = false;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Long getPassportNumber() {
        return this.passportNumber;
    }

    public Long getInnNumber() {
        return this.innNumber;
    }

    public Long getLicenseNumber() {
        return this.licenseNumber;
    }

    public boolean isActive() {
        return this.active;
    }

    public String getPassword() {
        return this.password;
    }

    public String getChosenRole() {
        return this.chosenRole;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public List<RealEstateObject> getObjectsToBuy() {
        return this.objectsToBuy;
    }

    public List<RealEstateObject> getObjectsToSell() {
        return this.objectsToSell;
    }

    public List<Transaction> getBuyTransactions() {
        return this.buyTransactions;
    }

    public List<Transaction> getSellTransactions() {
        return this.sellTransactions;
    }

    public List<Application> getApplications() {
        return this.applications;
    }

    public boolean isOnlyPrimal() {
        return this.onlyPrimal;
    }

    public boolean isFilledProfile() {
        return this.filledProfile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setInnNumber(Long innNumber) {
        this.innNumber = innNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setChosenRole(String chosenRole) {
        this.chosenRole = chosenRole;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setObjectsToBuy(List<RealEstateObject> objectsToBuy) {
        this.objectsToBuy = objectsToBuy;
    }

    public void setObjectsToSell(List<RealEstateObject> objectsToSell) {
        this.objectsToSell = objectsToSell;
    }

    public void setBuyTransactions(List<Transaction> buyTransactions) {
        this.buyTransactions = buyTransactions;
    }

    public void setSellTransactions(List<Transaction> sellTransactions) {
        this.sellTransactions = sellTransactions;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void setOnlyPrimal(boolean onlyPrimal) {
        this.onlyPrimal = onlyPrimal;
    }

    public void setFilledProfile(boolean filledProfile) {
        this.filledProfile = filledProfile;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$surname = this.getSurname();
        final Object other$surname = other.getSurname();
        if (this$surname == null ? other$surname != null : !this$surname.equals(other$surname)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$phoneNumber = this.getPhoneNumber();
        final Object other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber))
            return false;
        final Object this$passportNumber = this.getPassportNumber();
        final Object other$passportNumber = other.getPassportNumber();
        if (this$passportNumber == null ? other$passportNumber != null : !this$passportNumber.equals(other$passportNumber))
            return false;
        final Object this$innNumber = this.getInnNumber();
        final Object other$innNumber = other.getInnNumber();
        if (this$innNumber == null ? other$innNumber != null : !this$innNumber.equals(other$innNumber)) return false;
        final Object this$licenseNumber = this.getLicenseNumber();
        final Object other$licenseNumber = other.getLicenseNumber();
        if (this$licenseNumber == null ? other$licenseNumber != null : !this$licenseNumber.equals(other$licenseNumber))
            return false;
        if (this.isActive() != other.isActive()) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$chosenRole = this.getChosenRole();
        final Object other$chosenRole = other.getChosenRole();
        if (this$chosenRole == null ? other$chosenRole != null : !this$chosenRole.equals(other$chosenRole))
            return false;
        final Object this$roles = this.getRoles();
        final Object other$roles = other.getRoles();
        if (this$roles == null ? other$roles != null : !this$roles.equals(other$roles)) return false;
        final Object this$objectsToBuy = this.getObjectsToBuy();
        final Object other$objectsToBuy = other.getObjectsToBuy();
        if (this$objectsToBuy == null ? other$objectsToBuy != null : !this$objectsToBuy.equals(other$objectsToBuy))
            return false;
        final Object this$objectsToSell = this.getObjectsToSell();
        final Object other$objectsToSell = other.getObjectsToSell();
        if (this$objectsToSell == null ? other$objectsToSell != null : !this$objectsToSell.equals(other$objectsToSell))
            return false;
        final Object this$buyTransactions = this.getBuyTransactions();
        final Object other$buyTransactions = other.getBuyTransactions();
        if (this$buyTransactions == null ? other$buyTransactions != null : !this$buyTransactions.equals(other$buyTransactions))
            return false;
        final Object this$sellTransactions = this.getSellTransactions();
        final Object other$sellTransactions = other.getSellTransactions();
        if (this$sellTransactions == null ? other$sellTransactions != null : !this$sellTransactions.equals(other$sellTransactions))
            return false;
        final Object this$applications = this.getApplications();
        final Object other$applications = other.getApplications();
        if (this$applications == null ? other$applications != null : !this$applications.equals(other$applications))
            return false;
        if (this.isOnlyPrimal() != other.isOnlyPrimal()) return false;
        if (this.isFilledProfile() != other.isFilledProfile()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $surname = this.getSurname();
        result = result * PRIME + ($surname == null ? 43 : $surname.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $phoneNumber = this.getPhoneNumber();
        result = result * PRIME + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        final Object $passportNumber = this.getPassportNumber();
        result = result * PRIME + ($passportNumber == null ? 43 : $passportNumber.hashCode());
        final Object $innNumber = this.getInnNumber();
        result = result * PRIME + ($innNumber == null ? 43 : $innNumber.hashCode());
        final Object $licenseNumber = this.getLicenseNumber();
        result = result * PRIME + ($licenseNumber == null ? 43 : $licenseNumber.hashCode());
        result = result * PRIME + (this.isActive() ? 79 : 97);
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $chosenRole = this.getChosenRole();
        result = result * PRIME + ($chosenRole == null ? 43 : $chosenRole.hashCode());
        final Object $roles = this.getRoles();
        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
        final Object $objectsToBuy = this.getObjectsToBuy();
        result = result * PRIME + ($objectsToBuy == null ? 43 : $objectsToBuy.hashCode());
        final Object $objectsToSell = this.getObjectsToSell();
        result = result * PRIME + ($objectsToSell == null ? 43 : $objectsToSell.hashCode());
        final Object $buyTransactions = this.getBuyTransactions();
        result = result * PRIME + ($buyTransactions == null ? 43 : $buyTransactions.hashCode());
        final Object $sellTransactions = this.getSellTransactions();
        result = result * PRIME + ($sellTransactions == null ? 43 : $sellTransactions.hashCode());
        final Object $applications = this.getApplications();
        result = result * PRIME + ($applications == null ? 43 : $applications.hashCode());
        result = result * PRIME + (this.isOnlyPrimal() ? 79 : 97);
        result = result * PRIME + (this.isFilledProfile() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", name=" + this.getName() + ", surname=" + this.getSurname() + ", email=" + this.getEmail() + ", phoneNumber=" + this.getPhoneNumber() + ", passportNumber=" + this.getPassportNumber() + ", innNumber=" + this.getInnNumber() + ", licenseNumber=" + this.getLicenseNumber() + ", active=" + this.isActive() + ", password=" + this.getPassword() + ", chosenRole=" + this.getChosenRole() + ", roles=" + this.getRoles() + ", objectsToBuy=" + this.getObjectsToBuy() + ", objectsToSell=" + this.getObjectsToSell() + ", buyTransactions=" + this.getBuyTransactions() + ", sellTransactions=" + this.getSellTransactions() + ", applications=" + this.getApplications() + ", onlyPrimal=" + this.isOnlyPrimal() + ", filledProfile=" + this.isFilledProfile() + ")";
    }
}
