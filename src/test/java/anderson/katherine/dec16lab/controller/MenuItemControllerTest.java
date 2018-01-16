package anderson.katherine.dec16lab.controller;

import anderson.katherine.dec16lab.model.MenuItem;
import anderson.katherine.dec16lab.repository.MenuItemRepository;
import anderson.katherine.dec16lab.service.MenuItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuItemController.class)
public class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void givenMenuItem_whenGetMenuItems_thenReturnJsonArray() throws Exception {

//        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", BigDecimal.valueOf(19.99), "Pizza");
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");

        List<MenuItem> allMenuItems = Arrays.asList(hawaiianPizza);

        given(menuItemService.getAllMenuItems()).willReturn(allMenuItems);

        mockMvc.perform(get("/menu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(hawaiianPizza.getName()));
    }

    @Test
    public void getMenuItemByIdTest() throws Exception {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");

        given(menuItemService.getMenuItem((long) 1)).willReturn(hawaiianPizza);

        mockMvc.perform(get("/menu/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(hawaiianPizza.getName()));
    }

    @Test
    public void addMenuItem() throws Exception {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");

        String menuItemJson = "{\"name\":\"Hawaiian Pizza\",\"price\":\"19.99\",\"category\":\"Pizza\"}";

        mockMvc.perform(post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMenuItem() throws Exception {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");

        String menuItemJson = "{\"name\":\"Hawaiian Pizza\",\"price\":\"19.99\",\"category\":\"Pizza\"}";

        mockMvc.perform(put("/menu/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(menuItemJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMenuItem() throws Exception {
        MenuItem hawaiianPizza = new MenuItem("Hawaiian Pizza", 19.99, "Pizza");

        String menuItemJson = "{\"name\":\"Hawaiian Pizza\",\"price\":\"19.99\",\"category\":\"Pizza\"}";

        mockMvc.perform(delete("/menu/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
