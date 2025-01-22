package com.test.model;


import com.doc.models.ERole;
import com.doc.models.Role;
import com.doc.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

class UserTest {

    private User user;
    private Role role1;
    private Role role2;

    @BeforeEach
    void setUp() {
        // Initialize a User object for testing
        user = new User("testUser", "testPassword");

        // Initialize Roles (assuming Role is a simple POJO)
        role1 = new Role(ERole.ROLE_PATIENT);
        role2 = new Role(ERole.ROLE_ADMIN);

        // Add roles to the user
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);
    }

    @Test
    void testUserConstructorAndGetters() {
        // Test the user constructor and getters
        assertThat(user.getUsername()).isEqualTo("testUser");
        assertThat(user.getPassword()).isEqualTo("testPassword");
        assertThat(user.getRoles()).hasSize(2); // Verify roles are set
    }

    @Test
    void testSetters() {
        // Test the setters
        user.setUsername("newUsername");
        user.setPassword("newPassword");

        assertThat(user.getUsername()).isEqualTo("newUsername");
        assertThat(user.getPassword()).isEqualTo("newPassword");
    }

    @Test
    void testAddRoles() {
        // Test adding roles to the user
        Role role3 = new Role(ERole.ROLE_STAFF);
        user.getRoles().add(role3);

        assertThat(user.getRoles()).hasSize(3); // Ensure the new role is added
        assertThat(user.getRoles()).contains(role3); // Ensure the new role exists in the set
    }

    @Test
    void testGetId() {
        // Test setting and getting the user_id
        user.setId(1);

        assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    void testEmptyUser() {
        // Test the default constructor and empty user object
        User emptyUser = new User();
        emptyUser.setUsername("emptyUser");
        emptyUser.setPassword("emptyPassword");

        assertThat(emptyUser.getUsername()).isEqualTo("emptyUser");
        assertThat(emptyUser.getPassword()).isEqualTo("emptyPassword");
    }
}
