import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { CartItem } from '../models/CartItem';
import { DefaultImagePipe } from "../../shared/default-image.pipe";
import Big from 'big.js';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cart-page',
  imports: [DefaultImagePipe,RouterModule],
  templateUrl: './cart-page.component.html',
  styleUrl: './cart-page.component.css'
})
export class CartPageComponent implements OnInit{

  cartItems:CartItem[]=[]
  totalAmount=Big(0)

  constructor(private cartService:CartService){}

  ngOnInit(): void {
    this.cartItems=this.cartService.getCartItems();
    this.totalAmount=this.cartService.calculateTotalPrice();
  }

  incProductQuantity(productID:number){
    const product=this.cartItems.find(item=>item.id===productID);
    if(product)
      this.cartService.updateQuantity(productID,product.quantity+1)
  }

  decProductQuantity(productID:number){
    const product=this.cartItems.find(item=>item.id===productID);
    if(product)
      this.cartService.updateQuantity(productID,product.quantity-1)
  }

}
