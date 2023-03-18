package se.sofiamontgomery.todolistan.listobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListItemRepository extends JpaRepository<ListItemModel, Long> {

    //T.ex. @Query("SELECT s FROM ListItemModel s WHERE s.userModel.userid=5")
    //List<ListItemModel> findByUserid(UserModel user);
}
