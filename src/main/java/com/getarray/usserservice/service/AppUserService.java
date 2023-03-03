package com.getarray.usserservice.service;

import com.getarray.usserservice.domain.AppRole;
import com.getarray.usserservice.domain.AppUser;


import java.util.List;
public interface AppUserService {
    AppUser saveUser(AppUser appUser);
    AppRole saveRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
