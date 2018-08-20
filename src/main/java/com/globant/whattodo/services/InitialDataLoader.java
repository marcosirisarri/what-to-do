package com.globant.whattodo.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.globant.whattodo.entities.Privilege;
import com.globant.whattodo.entities.Role;
import com.globant.whattodo.entities.User;
import com.globant.whattodo.repository.PrivilegesRepository;
import com.globant.whattodo.repository.RolesRepository;
import com.globant.whattodo.repository.UsersRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PrivilegesRepository privilegesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

		Role adminRole = rolesRepository.findByName("ROLE_ADMIN").get();
		User user = new User("test@test.com", passwordEncoder.encode("test"));
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setRoles(Arrays.asList(adminRole));
		usersRepository.save(user);

		alreadySetup = true;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Optional<Privilege> privilegeOptional = privilegesRepository.findByName(name);
		Privilege privilege;
		if (privilegeOptional.isPresent()) {
			privilege = privilegeOptional.get();
		} else {
			privilege = new Privilege(name);
			privilegesRepository.save(privilege);
		}

		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Optional<Role> roleOptional = rolesRepository.findByName(name);
		Role role;
		if (roleOptional.isPresent()) {
			role = roleOptional.get();
		} else {
			role = new Role(name);
			role.setPrivileges(privileges);
			rolesRepository.save(role);
		}
		return role;
	}
}