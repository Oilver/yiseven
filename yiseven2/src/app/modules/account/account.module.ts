import {NgModule} from '@angular/core';
import {CommonModule, HashLocationStrategy, LocationStrategy} from '@angular/common';

import {AccountRoutingModule} from './account-routing.module';
import {NgZorroAntdModule, NzToolTipModule} from 'ng-zorro-antd';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {IndexComponent} from './index/index.component';
import {RunningAccountComponent} from './index/running-account/running-account.component';
import {RecordTypePipe} from '../../pipe/record-type.pipe';
import {UserListComponent} from './index/user-list/user-list.component';


@NgModule({
  declarations: [IndexComponent, RunningAccountComponent, RecordTypePipe, UserListComponent],
  imports: [
    CommonModule,
    AccountRoutingModule,
    NgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class AccountModule {
}
