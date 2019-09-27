import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';


const routes: Routes = [
  {path: 'index', loadChildren: () => import('./modules/index/index.module').then(m => m.IndexModule)},
  {path: 'account', loadChildren: () => import('./modules/account/account.module').then(m => m.AccountModule)},
  {path: '', pathMatch: 'full', redirectTo: 'index'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
