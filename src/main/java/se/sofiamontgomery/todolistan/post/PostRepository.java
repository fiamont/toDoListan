package se.sofiamontgomery.todolistan.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sofiamontgomery.todolistan.user.UserModel;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {

    //List<PostModel> findAllByUserModel(UserModel userModel);
}
