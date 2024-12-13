package com.ecommerce.topit.repos;

import com.ecommerce.topit.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
}
