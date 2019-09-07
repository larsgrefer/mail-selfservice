package de.greferhosting.selfservice.repository;

import de.greferhosting.selfservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsernameAndDomain(String username, String domain);
}
