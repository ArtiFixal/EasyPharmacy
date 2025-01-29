import { Component, Directive, Input } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { take } from "rxjs";
import { provideRouter, withComponentInputBinding } from "@angular/router";
import { employeeRoutes } from "../employee.module";

@Directive({
  providers:[
    [provideRouter(employeeRoutes,withComponentInputBinding())]
  ]
})
export abstract class EntityDetailsPage<T,S extends BaseService<T>> {

  data?: T

  constructor(protected service: S) {}

  @Input()
  set id(entityID: number) {
    this.service.getEntity(entityID).pipe(take(1)).subscribe({
      next: response => this.data = response
    });
  }
}