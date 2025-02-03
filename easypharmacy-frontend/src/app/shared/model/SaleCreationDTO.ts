import { CartItemDTO } from "../../core/models/CartItemDTO";
import { AddressDTO } from "./AddressDTO";

export class SaleCreationDTO{
    constructor(public clienID:number,public address:AddressDTO,public cart:CartItemDTO[]){}
}