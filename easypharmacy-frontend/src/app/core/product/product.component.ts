import { Component, input } from '@angular/core';
import { MedicineDTO } from '../../employee/medicine/models/MedicineDTO';
import { Router, RouterModule } from '@angular/router';
import { DefaultImagePipe } from "../../shared/default-image.pipe";
import { CartService } from '../services/cart.service';
import { CartItem } from '../models/CartItem';

@Component({
  selector: 'app-product',
  imports: [RouterModule,DefaultImagePipe],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  product=input.required<MedicineDTO>()

  constructor(private router:Router,private cartService:CartService){}

  public openProductPage(){
    this.router.navigateByUrl(`/product/${this.product().id}`)
  }

  public addToCart(){
    const item=new CartItem(this.product().id!,this.product().name,this.product().price,1,this.product().image);
    this.cartService.addToCart(item);
  }
}
