package anderson.katherine.dec16lab.repository;

import anderson.katherine.dec16lab.model.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    public Iterable<MenuItem> findMenuItemsByCategory(String category);
}
