package com.pramati.kart.userManagement.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.pramati.kart.userManagement.utility.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private String username;
    private String password;
    private int active;
    private String email;
    private String phone;
    private String createdBy;
    private String lastModifiedBy;
    private Date createdTime;
    private Date lastModifiedTime;
    @Enumerated(EnumType.STRING)
    private Role roles;
    private Long billingAddressId;
    private Long shippingAddressId;

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
}