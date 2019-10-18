import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {RecordService} from '../../../../service/record.service';
import {NzMessageService} from 'ng-zorro-antd';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-running-account',
  templateUrl: './running-account.component.html',
  styleUrls: ['./running-account.component.scss']
})
export class RunningAccountComponent implements OnInit {
  @ViewChild('runningAccountIndex', {static: true}) runningAccountIndex: ElementRef;

  balance = '';
  recordEntityList = [];

  isVisible = false;
  isOkLoading = false;

  userRole = 0;

  recordForm: FormGroup;
  recordNumber = 10;

  constructor(private fb: FormBuilder, private recordService: RecordService, private nzMessageService: NzMessageService,) {
    this.recordForm = this.fb.group({
      title: [null, [Validators.required]],
      type: ['1', [Validators.required]],
      value: [0, [Validators.required]],
      createDate: [null, [Validators.required]],
      description: ['']
    });
  }

  ngOnInit() {
    this.recordNumber = (this.runningAccountIndex.nativeElement.offsetHeight - 70.19 - 10 - 45) / 46 - 1;
    this.initData();
  }

  initData() {
    this.recordService.queryList(null).subscribe(result => {
      if (result.status == 100) {
        this.balance = result.data.balance;
        this.recordEntityList = result.data.recordEntityList;
        this.userRole = result.data.userRole;

        this.listOfDisplayData = this.recordEntityList;
      }
    });
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isOkLoading = true;
    for (const key in this.recordForm.controls) {
      this.recordForm.controls[key].markAsDirty();
      this.recordForm.controls[key].updateValueAndValidity();
    }

    this.recordService.add(this.recordForm.value).subscribe(result => {
      this.handleCancel();
      this.isOkLoading = false;
      if (result.status == 100) {
        this.nzMessageService.success('添加成功');
        this.initData();
      }
    });
  }

  handleCancel(): void {
    this.resetForm();
    this.isVisible = false;
  }

  resetForm(): void {
    this.recordForm.reset();
    this.recordForm.get('title').setValue(null);
    this.recordForm.get('value').setValue(0);
    this.recordForm.get('type').setValue('1');
    this.recordForm.get('description').setValue('');
  }

  sortName: string | null = null;
  sortValue: string | null = null;

  listOfSearchTitles: string[] = [];
  listOfSearchType: string[] = [];
  listOfDisplayData: any;

  sort(sort: { key: string; value: string }): void {
    this.sortName = sort.key;
    this.sortValue = sort.value;
    this.search();
  }

  search(): void {
    /** filter data **/
    const filterFunc = (item: any) =>
      (this.listOfSearchTitles.length != 0 ? this.listOfSearchTitles.includes(item.title) : true) &&
      (this.listOfSearchType.length != 0 ? this.listOfSearchType.includes(item.type) : true);

    const data = this.recordEntityList.filter(item => filterFunc(item));
    /** sort data **/
    if (this.sortName && this.sortValue) {
      this.listOfDisplayData = data.sort((a, b) =>
        this.sortValue === 'ascend'
          ? a[this.sortName!] > b[this.sortName!]
          ? 1
          : -1
          : b[this.sortName!] > a[this.sortName!]
          ? 1
          : -1
      );
    } else {
      this.listOfDisplayData = data;
    }
  }

  filter(listOfSearchTitles: string[], listOfSearchType: string[]): void {
    this.listOfSearchType = listOfSearchType;
    this.listOfSearchTitles = listOfSearchTitles;
    this.search();
  }

  titles = [
    {text: '雇人刷单', value: '雇人刷单'},
    {text: '拿货开销', value: '拿货开销'},
    {text: '店铺开销', value: '店铺开销'},
    {text: '员工工资', value: '员工工资'},
    {text: '其他', value: '其他'}
  ];

  types = [{text: '支出', value: 1}, {text: '收入', value: 0}];
}
