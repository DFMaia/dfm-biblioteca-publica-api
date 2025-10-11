package com.dfm.biblioteca.domain.repository;

import com.dfm.biblioteca.domain.entity.User;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest implements TestPropertyProvider {

    @Inject
    private UserRepository userRepository;

    private User testUser;

    @Override
    public Map<String, String> getProperties() {
        return Map.of(
                "datasources.default.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
                "datasources.default.driver-class-name", "org.h2.Driver",
                "datasources.default.username", "sa",
                "datasources.default.password", "",
                "datasources.default.dialect", "H2",
                "jpa.default.properties.hibernate.dialect", "org.hibernate.dialect.H2Dialect",
                "jpa.default.properties.hibernate.hbm2ddl.auto", "create-drop"
        );
    }

    @BeforeEach
    void setUp(){
        testUser = new User();
        testUser.setFirstName("João");
        testUser.setLastName("Silva");
        testUser.setBirthDate(LocalDate.of(1990,5,15));
        testUser.setCpf("12345678901");
        testUser.setNationality("Brasileira");
        testUser.setStreet("Rua das Flores");
        testUser.setNumber("123");
        testUser.setComplement("Apto 45");
        testUser.setNeighborhood("Centro");
        testUser.setZipCode("30130100");
        testUser.setCity("Belo Horizonte");
        testUser.setState("MG");
        testUser.setCountry("Brasil");
        testUser.setStatus(User.UserStatus.REGULAR);
    }

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void shouldSaveUser(){
        User savedUser = userRepository.save(testUser);
        assertNotNull(savedUser.getId());
        assertEquals("João", savedUser.getFirstName());
        assertEquals("12345678901", savedUser.getCpf());
    }

    @Test
    void shouldFindUserById(){
        User savedUser = userRepository.save(testUser);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("João", foundUser.get().getFirstName());
        assertEquals("12345678901", foundUser.get().getCpf());
    }
}