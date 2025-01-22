package com.test.model;

import com.pat.models.ERole;
import com.pat.models.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RoleTest {

    private Role role;

    @BeforeEach
    void setUp() {
        // Initialize the Role object
        role = new Role(ERole.ROLE_PATIENT);
    }

    @Test
    void testRoleConstructorAndGetters() {
        // Test constructor and getters
        assertThat(role.getName()).isEqualTo(ERole.ROLE_PATIENT);
    }

    @Test
    void testSetters() {
        // Test setters
        role.setName(ERole.ROLE_ADMIN);
        assertThat(role.getName()).isEqualTo(ERole.ROLE_ADMIN);

        role.setId(1);
        assertThat(role.getId()).isEqualTo(1);
    }

    @Test
    void testDefaultConstructor() {
        // Test default constructor
        Role defaultRole = new Role();
        defaultRole.setName(ERole.ROLE_STAFF);
        defaultRole.setId(2);

        assertThat(defaultRole.getName()).isEqualTo(ERole.ROLE_STAFF);
        assertThat(defaultRole.getId()).isEqualTo(2);
    }

    @Test
    void testRoleEnum() {
        // Test that the role can be assigned from the enum correctly
        Role adminRole = new Role(ERole.ROLE_ADMIN);

        assertThat(adminRole.getName()).isEqualTo(ERole.ROLE_ADMIN);
    }

    @Test
    void testRoleId() {
        // Test that the role ID can be set correctly
        Role roleWithId = new Role(ERole.ROLE_PATIENT);
        roleWithId.setId(3);

        assertThat(roleWithId.getId()).isEqualTo(3);
    }
}
