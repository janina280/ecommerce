package com.ecommerce.topit.services;

import com.ecommerce.topit.dtos.WishListItemDto;
import com.ecommerce.topit.models.Product;
import com.ecommerce.topit.models.WishListItem;
import com.ecommerce.topit.repos.ProductRepository;
import com.ecommerce.topit.repos.WishListItemRepository;
import com.ecommerce.topit.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListItemService {

    private final WishListItemRepository wishListRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public WishListItemService(
            WishListItemRepository wishListRepository,
            ProductRepository productRepository,
            UserRepository userRepository
    ) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<WishListItemDto> getAllWishLists() {
        return wishListRepository.findAll().stream()
                .map(WishListItemDto::new)
                .collect(Collectors.toList());
    }

    public WishListItemDto createWishListItem(WishListItemDto wishListItemDto) {
        WishListItem wishListItem = new WishListItem();

        Product product = productRepository.findById(wishListItemDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        wishListItem.setProduct(product);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        wishListItem.setUser(this.userRepository.findByUsername(username).orElseThrow());

        return new WishListItemDto(wishListRepository.save(wishListItem));
    }


    public void deleteWishListItem(Long id) {
        wishListRepository.deleteById(id);
    }

    public void deleteAllWishListItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<WishListItem> wishListItems = wishListRepository.findAll();
        
        for (var wishListItem : wishListItems) {
            wishListRepository.deleteById(wishListItem.getId());
        }
    }
}
