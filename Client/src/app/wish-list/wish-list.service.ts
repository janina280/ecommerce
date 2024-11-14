import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { WishListItem} from './wish-list-item';

@Injectable({
  providedIn: 'root'
})
export class WishListService {

  private wishlistUrl: string;

  constructor(private http: HttpClient) {
    this.wishlistUrl = "http://localhost:8080/api/wish-list";
  }

  public getAllWishList(): Observable<WishListItem[]> {
    return this.http.get<WishListItem[]>(this.wishlistUrl);
  }

  public createWishListItem(newWishlistItem: any): Observable<WishListItem> {
    return this.http.post<WishListItem>(this.wishlistUrl, newWishlistItem);
  }

  public deleteWishListItem(wishlistItemId: number): Observable<string> {
    const url = `${this.wishlistUrl}/${wishlistItemId}`;
    return this.http.delete<string>(url);
  }

  public deleteAllWishList(): Observable<string> {
    const url = `${this.wishlistUrl}/all`;
    return this.http.delete<string>(url);
  }
}
