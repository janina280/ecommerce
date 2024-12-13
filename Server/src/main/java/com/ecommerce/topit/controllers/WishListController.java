package com.ecommerce.topit.controllers;

import com.ecommerce.topit.dtos.WishListItemDto;
import com.ecommerce.topit.services.WishListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wish-list")
@CrossOrigin(origins = "http://localhost:4200/")
@PreAuthorize("hasRole('USER')")
public class WishListController {

    private final WishListItemService wishListItemService;

    @Autowired
    public WishListController(WishListItemService wishListItemService) {
        this.wishListItemService = wishListItemService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<WishListItemDto> getAllWishListItems() {
        return wishListItemService.getAllWishLists();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:create')")
    public WishListItemDto createWishListItem(@RequestBody WishListItemDto wishListItemDto) {
        return wishListItemService.createWishListItem(wishListItemDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public Map<String, String> deleteWishListItem(@PathVariable Long id) {
        wishListItemService.deleteWishListItem(id);
        return Collections.singletonMap("response", "WishListItem " + id + " has been deleted.");
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasAuthority('user:delete')")
    public Map<String, String> deleteAllWishListItems() {
        wishListItemService.deleteAllWishListItems();
        return Collections.singletonMap("response", "All WishListItems have been deleted.");
    }
}
