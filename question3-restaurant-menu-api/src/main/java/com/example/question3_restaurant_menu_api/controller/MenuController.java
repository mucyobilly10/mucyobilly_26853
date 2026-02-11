package com.example.question3_restaurant_menu_api.controller;

import com.example.question3_restaurant_menu_api.model.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menuItems = new ArrayList<>();

    // Sample data (8 items as required)
    public MenuController() {
        menuItems.add(new MenuItem(1L, "Spring Rolls", "Crispy rolls", 5.0, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Chicken Wings", "Spicy wings", 7.5, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Beef Burger", "Grilled beef burger", 10.0, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Pasta", "Creamy pasta", 9.0, "Main Course", false));
        menuItems.add(new MenuItem(5L, "Ice Cream", "Vanilla ice cream", 4.0, "Dessert", true));
        menuItems.add(new MenuItem(6L, "Cake", "Chocolate cake", 6.0, "Dessert", false));
        menuItems.add(new MenuItem(7L, "Coffee", "Hot coffee", 3.0, "Beverage", true));
        menuItems.add(new MenuItem(8L, "Juice", "Fresh juice", 3.5, "Beverage", true));
    }

    // GET all menu items
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    // GET menu item by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET menu items by category
   @GetMapping("/category/{category}")
public List<MenuItem> getByCategory(@PathVariable String category) {

    List<MenuItem> result = new ArrayList<>();

    for (MenuItem item : menuItems) {
        if (item.getCategory().trim().toLowerCase()
                .equals(category.trim().toLowerCase())) {
            result.add(item);
        }
    }

    return result;
}


    // GET available items (?available=true)
    @GetMapping("/available")
    public List<MenuItem> getAvailableItems(@RequestParam boolean available) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        return result;
    }

    // SEARCH menu items by name
   @GetMapping("/search")
public List<MenuItem> searchByName(@RequestParam String name) {

    List<MenuItem> result = new ArrayList<>();

    for (MenuItem item : menuItems) {
        if (item.getName().toLowerCase()
                .contains(name.trim().toLowerCase())) {
            result.add(item);
        }
    }

    return result;
}


    // ADD new menu item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItems.add(menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
    }

    // TOGGLE availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                menuItems.remove(item);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
