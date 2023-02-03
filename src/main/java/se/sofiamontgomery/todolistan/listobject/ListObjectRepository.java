package se.sofiamontgomery.todolistan.listobject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListObjectRepository extends JpaRepository<ListObjectModel, Long> {
}
