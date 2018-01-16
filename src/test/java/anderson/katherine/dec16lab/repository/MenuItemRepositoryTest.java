package anderson.katherine.dec16lab.repository;

import anderson.katherine.dec16lab.model.MenuItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MenuItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void getEmptyMenuTest() {
        Iterable<MenuItem> emptyMenu = menuItemRepository.findAll();

        assertThat(emptyMenu.iterator().hasNext()).isFalse();
    }

    @Test
    public void getMenuItemsTest() {
//        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", BigDecimal.valueOf(19.99), "Pizza");
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");
        entityManager.persist(hawaiianPizza);
        entityManager.flush();

        Iterable<MenuItem> testMenu = menuItemRepository.findAll();

        assertThat(testMenu.iterator().next()).isEqualToComparingFieldByField(hawaiianPizza);
    }

    @Test
    public void getMenuItemByIdTest() {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");
        entityManager.persist(hawaiianPizza);
        entityManager.flush();

        MenuItem menuItem = menuItemRepository.findOne((long) 1);

        assertThat(menuItem.getName()).isEqualTo("Hawaiian Pizza");
    }

    @Test
    public void getMenuItemByIncorrectIdTest() {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");
        entityManager.persist(hawaiianPizza);
        entityManager.flush();

        MenuItem menuItem = menuItemRepository.findOne((long) 2);

        assertThat(menuItem).isEqualTo(null);
    }

    @Test
    public void getMenuItemsByCategoryTest() {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");
        MenuItem pepperoniPizza = new MenuItem("Pepperoni Pizza", 16.99, "Pizza");
        MenuItem cheesePizza = new MenuItem("Cheese Pizza", 14.99, "Pizza");
        MenuItem turkeyHoagie = new MenuItem("Turkey Hoagie", 7.40, "Hoagies");
        entityManager.persist(hawaiianPizza);
        entityManager.persist(pepperoniPizza);
        entityManager.persist(cheesePizza);
        entityManager.persist(turkeyHoagie);
        entityManager.flush();

        List<MenuItem> menuItems = new ArrayList<>();
        menuItemRepository.findMenuItemsByCategory("Pizza").forEach(menuItems::add);
        assertThat(menuItems).contains(hawaiianPizza, pepperoniPizza, cheesePizza);
    }

    @Test
    public void addMenuItemTest() {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");
        menuItemRepository.save(hawaiianPizza);
        MenuItem menuItem = menuItemRepository.findOne((long) 1);

        assertThat(menuItem.getName()).isEqualTo("Hawaiian Pizza");
    }

    @Test
    public void deleteMenuItemTest() {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");
        entityManager.persist(hawaiianPizza);
        entityManager.flush();

        menuItemRepository.delete((long) 1);

        MenuItem menuItem = menuItemRepository.findOne((long) 1);

        assertThat(menuItem).isEqualTo(null);
    }
}
