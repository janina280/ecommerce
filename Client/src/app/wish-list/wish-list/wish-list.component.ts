// wish-list.component.ts
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { WishListItem } from '../wish-list-item';
import { WishListService} from '../wish-list.service';
import { AuthService } from 'src/app/authentication/auth.service';
import { ProductService } from 'src/app/product/product.service';

@Component({
  selector: 'app-wish-list',
  templateUrl: './wish-list.component.html',
  styleUrls: ['./wish-list.component.css']
})
export class WishListComponent implements OnInit {
  formInstance: FormGroup;
  wishListItems: WishListItem[] = [];
  currentUser: any;
  products: any[] = [];

  constructor(
    private wishListService: WishListService,
    private productService: ProductService,
    private authService: AuthService,
  ) {
    this.formInstance = new FormGroup({
      "productId": new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();

    this.fetchWishListAndProducts();
  }

  private fetchWishListAndProducts(): void {
    this.wishListService.getAllWishList().subscribe(
      wishListItems => {
        this.wishListItems = wishListItems;

        this.productService.getProducts().subscribe(
          products => {
            this.products = products;
          },
          error => {
            console.error('Error fetching products', error);
          }
        );
      },
      error => {
        console.error('Error fetching wish list', error);
      }
    );
  }
  delete(wishListItemId: number): void {
    this.wishListService.deleteWishListItem(wishListItemId).subscribe(
      response => {
        this.wishListItems = this.wishListItems.filter(item => item.id !== wishListItemId);
      },
      error => {
        console.error('Error deleting wish list item', error);
      }
    );
  }

  deleteAll(): void {
    this.wishListService.deleteAllWishList().subscribe(
      response => {
        this.wishListItems = [];
      },
      error => {
        console.error('Error deleting wish list', error);
      }
    );
  }

  getProductById(productId: number): any {
    return this.products.find(product => product.id === productId);
  }
}
