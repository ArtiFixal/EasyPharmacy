import { Injectable } from '@angular/core';
import { CartItem } from '../models/CartItem';
import Big from 'big.js';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly CART_KET="cart"
  private cartItems: CartItem[]=[];
  private itemCount=new BehaviorSubject<number>(0);

  constructor() {
    Big.DP=2;
    this.loadCartFromLocalStorage();
  }

  public addToCart(item:CartItem){
    const found=this.cartItems.find(search=>search.id===item.id);
    if(found)
      found.quantity++;
    else
      this.cartItems.push(item);
    this.updateItemCount();
  }

  public removeFromCart(itemID:number){
    this.cartItems=this.cartItems.filter(item=>item.id!==itemID);
    this.updateItemCount();
  }

  public getCartItems(): CartItem[]{
    return this.cartItems;
  }

  protected saveCartToLocalStorage(){
    localStorage.setItem(this.CART_KET,JSON.stringify(this.cartItems));
  }

  protected loadCartFromLocalStorage(){
    const savedCart=localStorage.getItem(this.CART_KET);
    if(savedCart){
      this.cartItems=JSON.parse(savedCart);
      this.updateItemCount();
    }
  }

  public clearCart(){
    this.cartItems=[];
    this.updateItemCount();
  }

  public updateQuantity(itemID:number,quantity:number){
    const found=this.cartItems.find(item=>item.id===itemID);
    if(found&&quantity>0){
      found.quantity=quantity;
      this.updateItemCount();
    }
    else
      this.removeFromCart(itemID);
  }

  private updateItemCount(){
    const totalQuantity=this.cartItems.reduce((total,item)=>total+item.quantity,0);
    this.saveCartToLocalStorage();
    this.itemCount.next(totalQuantity);
  }

  public getCartItemsCount():Observable<number> {
    return this.itemCount.asObservable();
  }

  public calculateTotalPrice():Big {
    let total=new Big(0);
    this.cartItems.forEach(
      item=>{
        const price=new Big(item.price)
        const pricePerItem=price.mul(item.quantity);
        total=total.add(pricePerItem);
      }
    );
    return total;
  }
}
