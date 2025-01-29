import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";
import { enviroment } from "../../enviroment";
import { Directive } from "@angular/core";
import { of, switchMap, take } from "rxjs";

@Directive()
export abstract class BaseService<T>{
    
    readonly pageSize=20;

    protected resourceUrl:string;

    constructor(protected http:HttpClient,resourceName:string) {
        this.resourceUrl=`${enviroment.apiUrl}/${enviroment.currentApiVer}/${resourceName}`
    }

    public getEntityMaxPage():Observable<number>{
        return this.http.get<number>(`${this.resourceUrl}/count`).pipe(
            take(1),
            switchMap(count=>of(Math.floor(count/this.pageSize)))
        );
    }

    public getEntityPage(page:number):Observable<T[]>{
        return this.http.get<T[]>(`${this.resourceUrl}/page?page=${page}&pageSize=${this.pageSize}`)
    }
    
    public getEntity(entityID:number):Observable<T>{
        return this.http.get<T>(`${this.resourceUrl}/${entityID}`)
    }
    
    public addEntity(dtoData:T){
        return this.http.post<T>(`${this.resourceUrl}/add`,dtoData)
    }
    
    public editEntity(dtoData:T){
        return this.http.patch<T>(`${this.resourceUrl}/edit`,dtoData)
    }
}