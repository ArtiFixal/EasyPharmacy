import Big from "big.js";

export class MedicineCreateDTO{
    constructor(public name:string,public manufacturerID:number,public categoryID:number,public medicineFormID:number,public price:Big,public prescriptionRequired:boolean,public id?:number,public imageID?:number){}
}