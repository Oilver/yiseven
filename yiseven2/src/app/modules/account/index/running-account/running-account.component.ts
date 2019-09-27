import {Component, OnInit} from '@angular/core';
import {RecordService} from '../../../../service/record.service';
import {NzMessageService} from 'ng-zorro-antd';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-running-account',
  templateUrl: './running-account.component.html',
  styleUrls: ['./running-account.component.scss']
})
export class RunningAccountComponent implements OnInit {
  balance = '';
  recordEntityList = [];

  isVisible = false;
  isOkLoading = false;

  userRole = 0;

  recordForm: FormGroup;

  constructor(private fb: FormBuilder, private recordService: RecordService, private nzMessageService: NzMessageService) {
    this.recordForm = this.fb.group({
      title: [null, [Validators.required]],
      type: ['1', [Validators.required]],
      value: [0, [Validators.required]],
      description: ['']
    });
  }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.recordService.queryList(null).subscribe(result => {
      if (result.status == 100) {
        this.balance = result.data.balance;
        this.recordEntityList = result.data.recordEntityList;
        this.userRole = result.data.userRole;
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
}
