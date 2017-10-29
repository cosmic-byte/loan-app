package thecrevance.repository;

import thecrevance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> getByEmail(String username);

    Optional<User> findByIdAndDeletedIsFalse(Long id);

    User getByEmailAndDeletedFalse(String username);

    List<User> findByDeletedFalseOrderByFirstnameAsc();
}
