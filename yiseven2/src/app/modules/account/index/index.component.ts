import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../service/user.service';
import {NzModalService} from 'ng-zorro-antd';
import {environment} from '../../../../environments/environment';
import {IndexService} from '../../../service/index.service';
import {DOCUMENT} from '@angular/common';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {
  isCollapsed = false;

  username: string = '';

  navStatus = 0;

  constructor(private router: Router, private userService: UserService,
              private indexService: IndexService, private modalService: NzModalService,
              private activatedRoute: ActivatedRoute, @Inject(DOCUMENT) document: any) {
  }

  ngOnInit() {
    this.activatedRoute.url.subscribe(() => this.setNavType(document.location.href));
    this.userService.queryCurrentUser(null).subscribe(result => {
      this.username = result.data.username;
    });
  }

  setNavType(url) {
    if (url.indexOf('index/runningAccount') != -1) {
      this.navStatus = 0;
    } else if (url.indexOf('index/userList') != -1) {
      this.navStatus = 1;
    }
  }

  navUserList() {
    this.router.navigateByUrl('/account/index/userList');
  }

  navRecordList() {
    this.router.navigateByUrl('/account/index');
  }

  showLogoutConfirm(): void {
    this.modalService.confirm({
      nzTitle: '<p>您确定退出登录吗？</p>',
      nzOnOk: () => this.logout(),
      nzOkText: '确定',
      nzCancelText: '取消'
    });
  }

  logout() {
    this.indexService.logout().subscribe(result => {
      if (result.status == 100) {
        localStorage.removeItem(environment.current_user);
        this.router.navigateByUrl('/index');
      }
    });
  }
}
