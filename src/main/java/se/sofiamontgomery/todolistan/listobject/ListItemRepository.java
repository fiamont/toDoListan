package se.sofiamontgomery.todolistan.listobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListItemRepository extends JpaRepository<ListItemModel, Long> {
    ListItemModel findByListItemName(String listItemName);
}
