import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'yesNo'
})
export class YesNoPipe implements PipeTransform {

  transform(value?: boolean): string {
    if(value)
      return $localize`:|Boolean yes text@@bool.yes:Yes`
    return $localize`:|Boolean no text@@bool.no:No`
  }

}
