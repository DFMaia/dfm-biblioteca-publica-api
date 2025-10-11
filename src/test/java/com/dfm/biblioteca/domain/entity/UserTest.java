package com.dfm.biblioteca.domain.entity;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class UserTest {

    private User validUser;

    @BeforeEach
    void setUp() {
        // Cria um usuário válido para ser usado nos testes
        validUser = new User();
        validUser.setFirstName("João");
        validUser.setLastName("Silva");
        validUser.setBirthDate(LocalDate.of(1990, 5, 15));
        validUser.setCpf("12345678901");
        validUser.setNationality("Brasileira");
        validUser.setStreet("Rua das Flores");
        validUser.setNumber("123");
        validUser.setComplement("Apto 45");
        validUser.setNeighborhood("Centro");
        validUser.setZipCode("30130100");
        validUser.setCity("Belo Horizonte");
        validUser.setState("MG");
        validUser.setCountry("Brasil");
        validUser.setStatus(User.UserStatus.REGULAR);
    }

    // ===== Testes de Criação e Atribuição =====

    @Test
    void shouldCreateUserWithAllFieldsSet() {
        assertNotNull(validUser);
        assertEquals("João", validUser.getFirstName());
        assertEquals("Silva", validUser.getLastName());
        assertEquals(LocalDate.of(1990, 5, 15), validUser.getBirthDate());
        assertEquals("12345678901", validUser.getCpf());
        assertEquals("Brasileira", validUser.getNationality());
        assertEquals("Rua das Flores", validUser.getStreet());
        assertEquals("123", validUser.getNumber());
        assertEquals("Apto 45", validUser.getComplement());
        assertEquals("Centro", validUser.getNeighborhood());
        assertEquals("30130100", validUser.getZipCode());
        assertEquals("Belo Horizonte", validUser.getCity());
        assertEquals("MG", validUser.getState());
        assertEquals("Brasil", validUser.getCountry());
        assertEquals(User.UserStatus.REGULAR, validUser.getStatus());
    }

    @Test
    void shouldCreateUserWithMinimalRequiredFields() {
        User minimalUser = new User();
        minimalUser.setFirstName("A");
        minimalUser.setLastName("B");
        minimalUser.setBirthDate(LocalDate.of(1990, 1, 1));
        minimalUser.setCpf("12345678901");
        minimalUser.setNationality("Brasil");
        minimalUser.setStreet("Rua");
        minimalUser.setNumber("1");
        minimalUser.setNeighborhood("Bairro");
        minimalUser.setZipCode("12345678");
        minimalUser.setCity("Cidade");
        minimalUser.setState("MG");
        minimalUser.setCountry("Brasil");

        assertNotNull(minimalUser);
        assertEquals("A", minimalUser.getFirstName());
        assertEquals("B", minimalUser.getLastName());
    }

    @Test
    void shouldCreateUserWithNullComplement() {
        User userWithoutComplement = new User();
        userWithoutComplement.setFirstName("João");
        userWithoutComplement.setLastName("Silva");
        userWithoutComplement.setBirthDate(LocalDate.of(1990, 5, 15));
        userWithoutComplement.setCpf("12345678901");
        userWithoutComplement.setNationality("Brasileira");
        userWithoutComplement.setStreet("Rua das Flores");
        userWithoutComplement.setNumber("123");
        // complement é opcional, deixa null
        userWithoutComplement.setNeighborhood("Centro");
        userWithoutComplement.setZipCode("30130100");
        userWithoutComplement.setCity("Belo Horizonte");
        userWithoutComplement.setState("MG");
        userWithoutComplement.setCountry("Brasil");

        assertNull(userWithoutComplement.getComplement());
    }

    @Test
    void shouldUpdateFirstName() {
        validUser.setFirstName("Maria");
        assertEquals("Maria", validUser.getFirstName());
    }

    @Test
    void shouldUpdateLastName() {
        validUser.setLastName("Santos");
        assertEquals("Santos", validUser.getLastName());
    }

    @Test
    void shouldUpdateBirthDate() {
        LocalDate newDate = LocalDate.of(1985, 3, 20);
        validUser.setBirthDate(newDate);
        assertEquals(newDate, validUser.getBirthDate());
    }

    @Test
    void shouldUpdateCpf() {
        validUser.setCpf("98765432100");
        assertEquals("98765432100", validUser.getCpf());
    }

    @Test
    void shouldUpdateAddress() {
        validUser.setStreet("Avenida Paulista");
        validUser.setNumber("1000");
        validUser.setNeighborhood("Bela Vista");
        validUser.setCity("São Paulo");
        validUser.setState("SP");

        assertEquals("Avenida Paulista", validUser.getStreet());
        assertEquals("1000", validUser.getNumber());
        assertEquals("Bela Vista", validUser.getNeighborhood());
        assertEquals("São Paulo", validUser.getCity());
        assertEquals("SP", validUser.getState());
    }

    @Test
    void shouldHaveDefaultStatusAsRegular() {
        User newUser = new User();
        assertEquals(User.UserStatus.REGULAR, newUser.getStatus());
    }

    @Test
    void shouldChangeStatusToBlocked() {
        validUser.setStatus(User.UserStatus.BLOCKED);
        assertEquals(User.UserStatus.BLOCKED, validUser.getStatus());
    }

    @Test
    void shouldChangeStatusToPersonaNonGrata() {
        validUser.setStatus(User.UserStatus.PERSONA_NON_GRATA);
        assertEquals(User.UserStatus.PERSONA_NON_GRATA, validUser.getStatus());
    }

    @Test
    void shouldAllowStatusTransitions() {
        validUser.setStatus(User.UserStatus.BLOCKED);
        assertEquals(User.UserStatus.BLOCKED, validUser.getStatus());

        validUser.setStatus(User.UserStatus.REGULAR);
        assertEquals(User.UserStatus.REGULAR, validUser.getStatus());

        validUser.setStatus(User.UserStatus.PERSONA_NON_GRATA);
        assertEquals(User.UserStatus.PERSONA_NON_GRATA, validUser.getStatus());
    }

    @Test
    void shouldCreateUserWithNoArgsConstructor() {
        User user = new User();
        assertNotNull(user);
        assertEquals(User.UserStatus.REGULAR, user.getStatus());
    }

    @Test
    void shouldCreateUserWithAllArgsConstructor() {
        User user = new User(
                1L,
                "João",
                "Silva",
                LocalDate.of(1990, 5, 15),
                "12345678901",
                "Brasileira",
                "Rua das Flores",
                "123",
                "Apto 45",
                "Centro",
                "30130100",
                "Belo Horizonte",
                "MG",
                "Brasil",
                User.UserStatus.REGULAR,
                null,
                null,
                null
        );

        assertEquals(1L, user.getId());
        assertEquals("João", user.getFirstName());
        assertEquals("Silva", user.getLastName());
        assertEquals(User.UserStatus.REGULAR, user.getStatus());
    }

    @Test
    void shouldHaveSameValuesForEqualUsers() {
        User user1 = new User();
        user1.setFirstName("João");
        user1.setLastName("Silva");

        User user2 = new User();
        user2.setFirstName("João");
        user2.setLastName("Silva");
        assertEquals(user1, user2);
    }

    @Test
    void shouldGenerateToString() {
        String userString = validUser.toString();
        assertNotNull(userString);
        assertTrue(userString.contains("User"));
        assertTrue(userString.contains("João"));
    }

}