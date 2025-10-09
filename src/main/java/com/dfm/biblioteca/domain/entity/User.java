package com.dfm.biblioteca.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_cpf", columnList = "cpf"),
        @Index(name = "idx_status", columnList = "status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotNull(message = "Birth date is required")
    @PastOrPresent(message = "Birth date cannot be in the future")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "CPF is required")
    @Size(min = 11, max = 11, message = "CPF must have 11 characters")
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank(message = "Nationality is required")
    @Size(min = 1, max = 100, message = "Nationality must be between 1 and 100 characters")
    @Column(name = "nationality", nullable = false, length = 100)
    private String nationality;

    @NotBlank(message = "Street is required")
    @Size(min = 1, max = 150, message = "Street must be between 1 and 150 characters")
    @Column(name = "street", nullable = false, length = 150)
    private String street;

    @NotBlank(message = "Number is required")
    @Size(min = 1, max = 10, message = "Number must be between 1 and 10 characters")
    @Column(name = "number", nullable = false, length = 10)
    private String number;

    @Size(max = 100, message = "Complement must be at most 100 characters")
    @Column(name = "complement", length = 100)
    private String complement;

    @NotBlank(message = "Neighborhood is required")
    @Size(min = 1, max = 100, message = "Neighborhood must be between 1 and 100 characters")
    @Column(name = "neighborhood", nullable = false, length = 100)
    private String neighborhood;

    @NotBlank(message = "Zip code is required")
    @Size(min = 1, max = 10, message = "Zip code must be between 1 and 10 characters")
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @NotBlank(message = "City is required")
    @Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State must have 2 characters")
    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @NotBlank(message = "Country is required")
    @Size(min = 1, max = 100, message = "Country must be between 1 and 100 characters")
    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('REGULAR', 'BLOCKED', 'PERSONA_NON_GRATA') DEFAULT 'REGULAR'")
    private UserStatus status = UserStatus.REGULAR;

    @Column(name = "persona_non_grata_until_date")
    private LocalDateTime personaNonGrataUntilDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * Enum que representa os possíveis status de um usuário.
     *
     * REGULAR: Usuário sem pendências, pode pegar livros emprestados.
     * BLOCKED: Usuário tem pendências (multa, livro atrasado ou desaparecido).
     * PERSONA_NON_GRATA: Usuário bloqueado por devolver livro raro com atraso > 10 dias.
     */
    public enum UserStatus {
        REGULAR,
        BLOCKED,
        PERSONA_NON_GRATA
    }
}