package com.ibm.diypopups.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.diypopups.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
   
	private static final long serialVersionUID = 1L;

	private Long id;

    private String firstname;
    
    private String lastname;
    
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;
    
    private String gender;
    
    private String type;
    
    private int Upcredits;
    private int Downcredits;

    
	private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String firstname, String lastname, String username, String email, String password, String gender, Collection<? extends GrantedAuthority> authorities, String type) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.authorities = authorities;
        this.type = type;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getGender(),
                authorities,
                user.getType()
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }
    
    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    
    public String getGender() {
        return gender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public String getType() {
        return type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
    
    public int getUpcredits() {
		return Upcredits;
	}

	public void setUpcredits(int upcredits) {
		Upcredits = upcredits;
	}

	public int getDowncredits() {
		return Downcredits;
	}

	public void setDowncredits(int downcredits) {
		Downcredits = downcredits;
	}

    
}