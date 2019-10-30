package com.pramati.kart.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.pramati.kart.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int deleteStatus;
    private String name;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String createdBy;
    private String lastModifiedBy;
    private Date createdTime;
    private Date lastModifiedTime;
    @Enumerated(EnumType.STRING)
    private Role roles;
    private Long billingAddressId;
    private Long shippingAddressId;
    private int active;

    @JsonSerialize(using = DateSerializer.class)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}