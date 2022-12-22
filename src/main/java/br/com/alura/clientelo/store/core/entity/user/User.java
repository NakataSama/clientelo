package br.com.alura.clientelo.store.core.entity.user;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserProfile> profiles;

    @Deprecated
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.profiles = new ArrayList<>();
    }
    public User(String email, String password, List<UserProfile> profiles) {
        this.email = email;
        this.password = password;
        this.profiles = profiles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<UserProfile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(() -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
}
