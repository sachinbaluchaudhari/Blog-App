package com.blog.app;

import com.blog.app.entities.Role;
import com.blog.app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
@Autowired
private RoleRepository roleRepository;
	@Value("${normal.user.id}")
	private String normalId;
	@Value("${admin.user.id}")
	private String adminId;
	@Override
	public void run(String... args) throws Exception {
		Role normal = Role.builder().roleId(normalId).roleName("ROLE_NORMAL").build();
		Role admin = Role.builder().roleId(adminId).roleName("ROLE_ADMIN").build();
		roleRepository.save(normal);
		roleRepository.save(admin);
	}
}
