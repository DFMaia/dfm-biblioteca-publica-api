package com.dfm.biblioteca.domain.repository;

import com.dfm.biblioteca.domain.entity.User;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Introspected
public interface UserRepository extends CrudRepository<User, Long> {

    //Busca usuário pelo CPF
    Optional<User> findByCpf(String cpf);

    //Busca usuários pelo status
    List<User> findByStatus(User.UserStatus status);

    //Busca usuários pelo status
    long countByStatus(User.UserStatus status);

    //Verifica se o usuário existe pelo CPF
    boolean existsByCpf(String cpf);

    //Remove todos os usuários por status
    long deleteAllByStatus(User.UserStatus status);

    //Remove um usuário por status
    long deleteByIdAndStatus(Long id, User.UserStatus status);
}
