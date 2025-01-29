import { Directive, OnDestroy, OnInit } from '@angular/core';
import { BaseService } from '../services/base.service';
import { BehaviorSubject, interval, Subscription, switchMap, take } from 'rxjs';

@Directive()
export abstract class EntityListComponent<T, S extends BaseService<T>> implements OnInit,OnDestroy {

  currentPage: number = 0;
  maxPage: number = 1;
  displayInfoPopup: boolean = false;
  popupText = '';
  pageSubject=new BehaviorSubject<number>(this.currentPage);

  protected pageSubscription!:Subscription;
  protected listData: T[] = []

  constructor(protected service: S) { }

  protected abstract localizeAddSuccess():string;
  protected abstract localizeAddFail():string;
  protected abstract localizeEditSuccess():string;
  protected abstract localizeEditFail():string;
  

  ngOnInit(): void {
    const lastActionState = window.history.state;
    // Display localized info popup
    if (lastActionState.action) {
      if (lastActionState.action == "add") {
        if (lastActionState.success)
          this.popupText = this.localizeAddSuccess();
        else
          this.popupText = this.localizeAddFail();
      }
      else {
        if (lastActionState.success)
          this.popupText = this.localizeEditSuccess();
        else
          this.popupText = this.localizeEditFail();
      }
      // Hide popup after 2s
      let task = interval(2000).subscribe({
        next: value => {
          this.displayInfoPopup = false;
          this.popupText = '';
          task.unsubscribe();
        },
      });
    }
    this.service.getEntityMaxPage().pipe(take(1)).subscribe({
      next: response=>this.maxPage=response,
      error: err=>console.log(err)
    })
    this.pageSubscription=this.pageSubject.pipe(
      switchMap((page)=>this.service.getEntityPage(page))
    ).subscribe({
      next: data=>this.listData=data,
      error: err=>console.log(err)
    })
  }

  ngOnDestroy(): void {
    if(!this.pageSubscription.closed)
      this.pageSubscription.unsubscribe();
  }

  protected nextPage() {
    if (this.currentPage < this.maxPage) {
      this.currentPage++;
      this.pageSubject.next(this.currentPage);
    }
  }

  protected previousPage(){
    if (this.currentPage > 0 ){
      this.currentPage--;
      this.pageSubject.next(this.currentPage);
    }
  }
}
