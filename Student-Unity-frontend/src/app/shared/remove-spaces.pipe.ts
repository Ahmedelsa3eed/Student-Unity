import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'removeSpaces'
})
export class RemoveSpacesPipe implements PipeTransform {

  transform(value: any): any {
    if (value === undefined)
      return 'undefined';
    return value.replace(/\s/g, "");
  }

}
