import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'recordType'
})
export class RecordTypePipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (value == 1) {
      return '支出';
    } else if (value == 0) {
      return '收入';
    }
    return value;
  }

}
