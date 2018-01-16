package anderson.katherine.dec16lab.service;

import anderson.katherine.dec16lab.model.MenuItem;
import anderson.katherine.dec16lab.repository.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuItemService.class)
public class MenuItemServiceTest {

    @Autowired
    private MenuItemService menuItemService;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp() {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");
        MenuItem pepperoniPizza = new MenuItem("Pepperoni Pizza", 16.99, "Pizza");
        MenuItem cheesePizza = new MenuItem("Cheese Pizza", 14.99, "Pizza");
        MenuItem turkeyHoagie = new MenuItem("Turkey Hoagie", 7.40, "Hoagies");

        Mockito.when(menuItemRepository.findOne((long) 1))
                .thenReturn(hawaiianPizza);

        Mockito.when(menuItemRepository.findOne((long) 5))
                .thenReturn(null);

        Mockito.when(menuItemRepository.findAll())
                .thenReturn(Arrays.asList(hawaiianPizza, pepperoniPizza, cheesePizza, turkeyHoagie));

        Mockito.when(menuItemRepository.findMenuItemsByCategory("Pizza"))
                .thenReturn(Arrays.asList(hawaiianPizza, pepperoniPizza, cheesePizza));
    }

    @Test
    public void getAllMenuItemsTest() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        assertThat(menuItems.get(0).getName()).isEqualTo("Hawaiian Pizza");
    }

    @Test
    public void getMenuItemByCorrectIdTest() {
        MenuItem menuItem = menuItemService.getMenuItem((long) 1);
        assertThat(menuItem.getName()).isEqualTo("Hawaiian Pizza");
    }

    @Test
    public void getMenuItemByIncorrectIdTest() {
        MenuItem menuItem = menuItemService.getMenuItem((long) 5);
        assertThat(menuItem).isEqualTo(null);
    }

    @Test
    public void getMenuItemsByCategory() {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByCategory("Pizza");
        assertThat(menuItems).hasSize(3);
    }

//    @Test
//    public void updateMenuItem() {
//        menuItemService.updateMenuItem();
//    }
}
