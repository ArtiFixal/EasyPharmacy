import Big from "big.js";

export class CartItem{
    constructor(public id:number,public name:string,public price:Big,public quantity:number,public imagePath:string){}
}