export class MedicineDTO{
    constructor(public name:string,public image:string,public manufacturerID:number,public categoryID:number,public medicineFormID:number,public price:Big,public prescriptionRequired:boolean,public id?:number){}
}