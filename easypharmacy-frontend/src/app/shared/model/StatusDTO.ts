export class StatusDTO{

    constructor(public name:string,public id?:number){
        name=$localize`:Status name|@@${name}:${name}`;
    }
}