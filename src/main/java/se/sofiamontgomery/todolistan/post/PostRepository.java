package se.sofiamontgomery.todolistan.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {

    //T.ex. @Query("SELECT s FROM PostModel s WHERE s.userModel.userid=5")
    //List<PostModel> findByUserid(UserModel user);
}
