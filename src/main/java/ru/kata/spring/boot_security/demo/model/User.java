package ru.kata.spring.boot_security.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String lastname;
    private String password;
    private int age;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String lastname, String password, Set<Role> roles, int age, String email) {
        this.roles = roles;
        this.username = username;
        this.lastname = lastname;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getRoleNames() {
        return roles.stream()
                .map(role -> role.getName()
                        .replace("ROLE_", ""))
                .collect(Collectors.joining(" "));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;

    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(roles, user.roles) && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(lastname, user.lastname) && Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, lastname, password, roles);
    }

}




