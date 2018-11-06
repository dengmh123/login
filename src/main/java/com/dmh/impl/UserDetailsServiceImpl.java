package com.dmh.impl;

import com.dmh.model.SysRole;
import com.dmh.model.SysUser;
import com.dmh.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private SysUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
        SysUser user=userRepository.findByUsername(s);
        List<SimpleGrantedAuthority> auths=new ArrayList<>();
        for (SysRole role:user.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getUsername(),user.getPassword(),auths);
    }
}
