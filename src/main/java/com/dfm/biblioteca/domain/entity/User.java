package com.dfm.biblioteca.domain.entity;

import io.micronaut.core.annotation.Introspected;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_cpf", columnList = "cpf"),
        @Index(name = "idx_status", columnList = "status")
})
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

    // Getters e Setters explícitos (necessário para Micronaut Data)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getPersonaNonGrataUntilDate() {
        return personaNonGrataUntilDate;
    }

    public void setPersonaNonGrataUntilDate(LocalDateTime personaNonGrataUntilDate) {
        this.personaNonGrataUntilDate = personaNonGrataUntilDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(cpf, user.cpf) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(nationality, user.nationality) &&
                Objects.equals(street, user.street) &&
                Objects.equals(number, user.number) &&
                Objects.equals(complement, user.complement) &&
                Objects.equals(neighborhood, user.neighborhood) &&
                Objects.equals(zipCode, user.zipCode) &&
                Objects.equals(city, user.city) &&
                Objects.equals(state, user.state) &&
                Objects.equals(country, user.country) &&
                status == user.status &&
                Objects.equals(personaNonGrataUntilDate, user.personaNonGrataUntilDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, cpf, nationality,
                street, number, complement, neighborhood, zipCode,
                city, state, country, status, personaNonGrataUntilDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", cpf='" + cpf + '\'' +
                ", status=" + status +
                '}';
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