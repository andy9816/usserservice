package com.getarray.usserservice.service;

import com.getarray.usserservice.domain.AppRole;
import com.getarray.usserservice.domain.AppUser;
import com.getarray.usserservice.repository.RoleRepository;
import com.getarray.usserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements  AppUserService, UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user= userRepository.findByUsername(username);
        if(user==null){
            log.error("User not found in db");
            throw  new UsernameNotFoundException("NOt FOUND IN DB");
        }
        else{
            log.info("User Found in DB {}",username);
        }
        Collection<SimpleGrantedAuthority> authoroties=new ArrayList<>();
        user.getRoles().forEach(role-> authoroties.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getUsername(),user.getPassword(),authoroties);
    }


    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("SAVED NEW USER {} TO DB", appUser.getName());
        return userRepository.save(appUser);
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        log.info("SAVED NEW ROLE {} TO DB", appRole.getName());

        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("ADD NEW ROLE {} TO USER {}", roleName, username);

        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("FETCH ALL USERS");
        return userRepository.findAll();
    }
}