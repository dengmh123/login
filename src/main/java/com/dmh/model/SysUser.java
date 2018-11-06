package com.dmh.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class SysUser {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    //FetchType.EAGER：急加载。在加载一个实体的时候，其中定义是急加载的的属性(property)和字段(field)会立即从数据库中加载
    //CascadeType:级联更新
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<SysRole> roles;
    public SysUser(){}
    public SysUser(String username,String password){
        this.username=username;
        this.password=password;
    }
    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public List<SysRole> getRoles() {
        return roles;
    }
}