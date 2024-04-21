package project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByAccountLogin(String accountLogin);
    boolean existsByAccountId(Long accountId);

    boolean existsById(Long userId);
}
