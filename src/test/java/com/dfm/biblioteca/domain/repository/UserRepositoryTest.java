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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
                //"datasources.default.dialect", "H2",
                //"jpa.default.properties.hibernate.dialect", "org.hibernate.dialect.H2Dialect",
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

    @Test
    void shouldFindUserByCpf(){
        userRepository.save(testUser);
        Optional<User> foundUser = userRepository.findByCpf(testUser.getCpf());
        assertTrue(foundUser.isPresent());
        assertEquals("João", foundUser.get().getFirstName());
        assertEquals("12345678901", foundUser.get().getCpf());
    }

    @Test
    void shouldReturnEmptyWhenCpfNotFound(){
        Optional<User> foundUser = userRepository.findByCpf("99999999999");
        assertTrue(foundUser.isPresent());
    }

    @Test
    void shouldFindUsersByStatus(){
        userRepository.save(testUser);

        User blockedUser = new User();
        blockedUser.setFirstName("Maria");
        blockedUser.setLastName("Santos");
        blockedUser.setBirthDate(LocalDate.of(1985,3,20));
        blockedUser.setCpf("98765432100");
        blockedUser.setNationality("Brasileira");
        blockedUser.setStreet("Avenida Paulista");
        blockedUser.setNumber("1000");
        blockedUser.setNeighborhood("Bela Vista");
        blockedUser.setZipCode("01310100");
        blockedUser.setCity("São Paulo");
        blockedUser.setState("SP");
        blockedUser.setCountry("Brasil");
        blockedUser.setStatus(User.UserStatus.BLOCKED);
        userRepository.save(blockedUser);

        List<User> regularUsers = userRepository.findByStatus(User.UserStatus.REGULAR);
        List<User> blockedUsers = userRepository.findByStatus(User.UserStatus.BLOCKED);

        assertEquals(1, regularUsers.size());
        assertEquals("João", regularUsers.get(0).getFirstName());

        assertEquals(1, blockedUsers.size());
        assertEquals("Maria", blockedUsers.get(0).getFirstName());
    }

    @Test
    void shouldCountUsersByStatus(){
        userRepository.save(testUser);

        User blockedUser = new User();
        blockedUser.setFirstName("Maria");
        blockedUser.setLastName("Santos");
        blockedUser.setBirthDate(LocalDate.of(1985,3,20));
        blockedUser.setCpf("98765432100");
        blockedUser.setNationality("Brasileira");
        blockedUser.setStreet("Avenida Paulista");
        blockedUser.setNumber("1000");
        blockedUser.setNeighborhood("Bela Vista");
        blockedUser.setZipCode("01310100");
        blockedUser.setCity("São Paulo");
        blockedUser.setState("SP");
        blockedUser.setCountry("Brasil");
        blockedUser.setStatus(User.UserStatus.BLOCKED);
        userRepository.save(blockedUser);

        long regularCount = userRepository.countByStatus(User.UserStatus.REGULAR);
        long blockedCount = userRepository.countByStatus(User.UserStatus.BLOCKED);

        assertEquals(1, regularCount);
        assertEquals(1, blockedCount);
    }

    @Test
    void shouldReturnTrueWhenCpfExists(){
        userRepository.save(testUser);
        assertTrue(userRepository.existsByCpf("12345678901"));
    }

    @Test
    void shouldReturnFalseWhenCpfDoesNotExists(){
        assertFalse(userRepository.existsByCpf("99999999999"));
    }

    @Test
    void shouldDeleteUserById(){
        User savedUser = userRepository.save(testUser);
        userRepository.deleteById(savedUser.getId());
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertFalse(foundUser.isPresent());
    }

    @Test
    void shouldDeleteAllUsersByStatus(){
        testUser.setStatus(User.UserStatus.BLOCKED);
        userRepository.save(testUser);

        User anotherBlockedUser = new User();
        anotherBlockedUser.setFirstName("Maria");
        anotherBlockedUser.setLastName("Santos");
        anotherBlockedUser.setBirthDate(LocalDate.of(1985,3,20));
        anotherBlockedUser.setCpf("98765432100");
        anotherBlockedUser.setNationality("Brasileira");
        anotherBlockedUser.setStreet("Avenida Paulista");
        anotherBlockedUser.setNumber("1000");
        anotherBlockedUser.setNeighborhood("Bela Vista");
        anotherBlockedUser.setZipCode("01310100");
        anotherBlockedUser.setCity("São Paulo");
        anotherBlockedUser.setState("SP");
        anotherBlockedUser.setCountry("Brasil");
        anotherBlockedUser.setStatus(User.UserStatus.BLOCKED);
        userRepository.save(anotherBlockedUser);

        long deletedCount = userRepository.deleteAllByStatus(User.UserStatus.BLOCKED);

        assertEquals(2, deletedCount);
        assertEquals(0, userRepository.countByStatus(User.UserStatus.BLOCKED));
    }

    @Test
    void shouldDeleteUserByIdAndStatus(){
        testUser.setStatus(User.UserStatus.BLOCKED);
        User savedUser = userRepository.save(testUser);

        long deletedCount = userRepository.deleteByIdAndStatus(savedUser.getId(), User.UserStatus.BLOCKED);

        assertEquals(1, deletedCount);
        assertFalse(userRepository.findById(savedUser.getId()).isPresent());
    }

    @Test
    void shouldNotDeleteUserWhenStatusDoesNotMatch(){
        User savedUser = userRepository.save(testUser); // Status REGULAR

        long deletedCount = userRepository.deleteByIdAndStatus(savedUser.getId(), User.UserStatus.BLOCKED);

        assertEquals(0, deletedCount);
        assertTrue(userRepository.findById(savedUser.getId()).isPresent());
    }
}