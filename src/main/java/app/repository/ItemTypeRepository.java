package app.repository;

import app.model.ItemType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ItemTypeRepository extends CrudRepository<ItemType, Long> {

    @Query("select it.id from ItemType it where name = ?1")
    Long findIdByName(String name);

}
