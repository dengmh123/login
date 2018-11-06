package com.dmh.repository;

import com.dmh.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 根据用户名查用户
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
}
