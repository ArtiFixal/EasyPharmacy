export class SaleDTO{
    constructor(public clientID:string,public addressID:number,public purchased:Date,public statusID:number,public finalised?:Date,public id?:number){}
}