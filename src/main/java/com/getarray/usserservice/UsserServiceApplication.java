package com.getarray.usserservice;

import com.getarray.usserservice.domain.AppRole;
import com.getarray.usserservice.domain.AppUser;

import com.getarray.usserservice.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class UsserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsserServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner run(AppUserService appUserService){
		return args -> {
			appUserService.saveRole(new AppRole(null, "ROLL_USER"));
			appUserService.saveRole(new AppRole(null, "ROLE_MANAGER"));
			appUserService.saveRole(new AppRole(null, "ROLE_ADMIN"));
			appUserService.saveRole(new AppRole(null, "ROLE_SUPER_ADMIN"));

			appUserService.saveUser(new AppUser(null, "Ted Mosbey", "cowboy", "123", new ArrayList<>()));
			appUserService.saveUser(new AppUser(null, "Marshal Erikshan", "marshalthemachine", "123", new ArrayList<>()));
			appUserService.saveUser(new AppUser(null, "Barney Stinson", "daddyishome", "123", new ArrayList<>()));
			appUserService.saveUser(new AppUser(null, "Lily Aldrin", "lil", "123", new ArrayList<>()));
			appUserService.saveUser(new AppUser(null, "Robin Sabatskey", "gunnut", "ted", new ArrayList<>()));

			appUserService.addRoleToUser("cowboy", "ROLL_USER");
			appUserService.addRoleToUser("marshalthemachine", "ROLE_MANAGER");
			appUserService.addRoleToUser("daddyishome", "ROLE_ADMIN");
			appUserService.addRoleToUser("lil", "ROLE_SUPER_ADMIN");
			appUserService.addRoleToUser("gunnut", "ROLL_USER");
			appUserService.addRoleToUser("marshalthemachine", "ROLE_MANAGER");
			appUserService.addRoleToUser("marshalthemachine", "ROLE_USER");
		};
	}

}
