import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../../../service/user.service';
import {NzModalService} from 'ng-zorro-antd';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
  @ViewChild('userListIndex', {static: true}) userListIndex: ElementRef;

  userList = [];
  unPassList = [];
  role = 0;
  id = 0;
  recordNumber = 10;

  constructor(private userService: UserService, private modalService: NzModalService) {
  }

  ngOnInit() {
    this.recordNumber = (this.userListIndex.nativeElement.offsetHeight - 70.19 - 10 - 45) / 46 - 1;
    console.log(this.recordNumber)
    this.userService.queryCurrentUser(null).subscribe(result => {
      this.role = result.data.role;
      this.id = result.data.id;
    });
    this.initData();
  }

  initData() {
    this.userService.queryUserList(null).subscribe(result => {
      if (result.status == 100) {
        this.userList = result.data.userList;
        this.unPassList = result.data.unPassList;
      }
    });
  }

  pass(id, status) {
    if (status == 1) {
      let params = {id: id, status: 1};
      this.userService.updateUser(params).subscribe(result => {
        if (result.status == 100) {
          this.initData();
        }
      });
    } else if (status == 0) {
      this.userService.delete(id).subscribe(result => {
        if (result.status == 100) {
          this.initData();
        }
      });
    }
  }

  showPassConfirm(id): void {
    this.modalService.confirm({
      nzTitle: '<p>您确定同意该用户加入工作室吗？</p>',
      nzOnOk: () => this.pass(id, 1),
      nzOkText: '确定',
      nzCancelText: '取消'
    });
  }

  showDeleteConfirm(id): void {
    this.modalService.warning({
      nzTitle: '<p>您确定将该用户移除吗？</p>',
      nzOnOk: () => this.pass(id, 0),
      nzOkText: '确定',
      nzCancelText: '取消'
    });
  }
}
