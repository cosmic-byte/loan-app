package thecrevance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import thecrevance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> getByEmail(String username);

    Optional<User> findByIdAndDeletedIsFalse(Long id);

    User getByEmailAndDeletedFalse(String username);

    Page<User> findByDeletedFalseOrderByFirstnameAsc(Pageable pageable);

    User findByEmail(String email);
}
