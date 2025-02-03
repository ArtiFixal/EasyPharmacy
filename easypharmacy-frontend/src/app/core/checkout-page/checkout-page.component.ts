import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CartService } from '../services/cart.service';
import Big from 'big.js';
import { AddressDTO } from '../../shared/model/AddressDTO';
import { CartItemDTO } from '../models/CartItemDTO';
import { AuthService } from '../../auth/services/auth.service';
import { take } from 'rxjs';
import { PersonDTO } from '../models/PersonDTO';
import { SaleService } from '../../shared/services/sale.service';
import { SaleCreationDTO } from '../../shared/model/SaleCreationDTO';

@Component({
  selector: 'app-checkout-page',
  imports: [ReactiveFormsModule],
  templateUrl: './checkout-page.component.html',
  styleUrl: './checkout-page.component.css'
})
export class CheckoutPageComponent implements OnInit{

  addressForm=new FormGroup({
    street:new FormControl('',Validators.required),
    postalCode:new FormControl('',Validators.required),
    city:new FormControl('',Validators.required),
  })
  cartItemCount=0;
  totalAmount=new Big(0)
  currentUser?:PersonDTO

  constructor(private cartService:CartService,private authService:AuthService,private saleService:SaleService){}

  ngOnInit(): void {
    this.totalAmount=this.cartService.calculateTotalPrice();
    this.authService.whoAmI("clients").pipe(take(1)).subscribe({
      next:response=>this.currentUser=response
    })
    this.cartService.getCartItemsCount().pipe(take(1)).subscribe({
      next:value=>this.cartItemCount=value
    })
  }

  placeOrder(){
    if(this.addressForm.valid&&this.addressForm.value.postalCode?.length!>=5){
      let addressDto=new AddressDTO(this.addressForm.value.street!,this.addressForm.value.postalCode!.replace('-',''),this.addressForm.value.city!);
      let cartItems=this.cartService.getCartItems();
      let cart:CartItemDTO[]=cartItems.map((item)=>{
        return new CartItemDTO(item.id,item.quantity)
      });
      console.log(cart);
      
      let sale=new SaleCreationDTO(this.currentUser?.id!,addressDto,cart);
      this.saleService.addSale(sale).pipe(take(1)).subscribe({
        next:response=>{
          console.log(response);
          
        }
      })
    }
  }
}
