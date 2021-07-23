package project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name field should not be blank")
    @Size(min = 2, max = 30, message = "Please correct name size")
    private String name;

    @Column(name = "surname", nullable = false)
    @NotEmpty(message = "Surname field should not be blank")
    @Size(min = 2, max = 30, message = "Please correct surname size")
    private String surname;

    @Column(name = "salary")
    @Min(value = 0, message = "Age should be a positive value")
    private int salary;

    @Column(name = "department")
    private String department;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "Email field should not be blank")
    @Email
//    @Size(min = 2, max = 30, message = "Please correct email size")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password must not be blank")
    @Size(min = 4, max = 30, message = "Please correct password size")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(Long id, String name, String surname, int salary, String department, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.department = department;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return name;
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
        return true;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
