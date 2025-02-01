import { Pipe, PipeTransform } from '@angular/core';
import { enviroment } from '../enviroment';

@Pipe({
  name: 'defaultImage'
})
export class DefaultImagePipe implements PipeTransform {

  transform(value?: string): string {
    if(!value)
      return `${enviroment.frontendUrl}/default.jpg`;
    return value;
  }

}
