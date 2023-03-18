package se.sofiamontgomery.todolistan.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);

    @Query("SELECT s FROM UserModel s ORDER BY s.username asc")
    List<UserModel> orderByUsername ();
}
