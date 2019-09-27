import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IndexComponent} from './index/index.component';
import {RunningAccountComponent} from './index/running-account/running-account.component';
import {UserListComponent} from './index/user-list/user-list.component';


const routes: Routes = [
  {
    path: 'index', component: IndexComponent, children: [
      {path: 'runningAccount', component: RunningAccountComponent},

      {
        path: 'userList', component: UserListComponent, children: [
        ]
      },
      {path: '', pathMatch: 'full', redirectTo: 'runningAccount'},
    ]
  },
  {path: '', pathMatch: 'full', redirectTo: 'index'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule {
}
