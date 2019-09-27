import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegistryComponent} from './registry/registry.component';
import {IndexComponent} from './index/index.component';

const routes: Routes = [
  {
    path: '', component: IndexComponent, children: [
      {path: 'login', component: LoginComponent},
      {path: 'registry', component: RegistryComponent},
      {path: '', pathMatch: 'full', redirectTo: 'login'},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IndexRoutingModule {
}
