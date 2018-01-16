package anderson.katherine.dec16lab.service;

import anderson.katherine.dec16lab.model.MenuItem;
import anderson.katherine.dec16lab.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public void addMenuItem(MenuItem menuItem) {
        menuItem = menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItemRepository.findAll().forEach(menuItems::add);
        return menuItems;
    }

    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.findOne(id);
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItemRepository.findMenuItemsByCategory(category).forEach(menuItems::add);
        return menuItems;
    }

    public void updateMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.delete(id);
    }
}
