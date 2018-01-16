package anderson.katherine.dec16lab.controller;

import anderson.katherine.dec16lab.model.MenuItem;
import anderson.katherine.dec16lab.repository.MenuItemRepository;
import anderson.katherine.dec16lab.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

//    @RequestMapping(value="/menu", method= RequestMethod.GET)
//    public ResponseEntity<Iterable<MenuItem>> getAllMenuItems() {
//        Iterable<MenuItem> allMenuItems = menuItemRepository.findAll();
//        return new ResponseEntity<>(allMenuItems, HttpStatus.OK);
//    }
//
//    @RequestMapping(value="/menu", method=RequestMethod.POST)
//    public ResponseEntity<?> createMenuItem(@Valid @RequestBody MenuItem menuItem) {
//
//        menuItem = menuItemRepository.save(menuItem);
//
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newMenuUri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(menuItem.getId())
//                .toUri();
//        responseHeaders.setLocation(newMenuUri);
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//    }


    @RequestMapping(value="/menu", method=RequestMethod.POST)
    public void addMenuItem(@RequestBody MenuItem menuItem) {
        menuItemService.addMenuItem(menuItem);
    }

    @RequestMapping(value="/menu", method= RequestMethod.GET)
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @RequestMapping(value="/menu/{id}", method= RequestMethod.GET)
    public MenuItem getMenuItem(@PathVariable Long id) {
        return menuItemService.getMenuItem(id);
    }

    @RequestMapping(value="/menu/{id}", method=RequestMethod.PUT)
    public void updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable Long id) {
        menuItemService.updateMenuItem(menuItem);
    }

    @RequestMapping(value="/menu/{id}", method=RequestMethod.DELETE)
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}
