package com.example.demo.modal;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Table(name = "users_user")
@Entity
@Data
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7429094341445912443L;

	@Id
	private int id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name ="password")
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    @OrderBy
    @JsonIgnore
    private Set<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
}
