import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {InputComponent} from "./input/input.component";
import {RoutingPath} from "./common/routing-path";
import {TextComponent} from "./text/text.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: RoutingPath.HOME,
    pathMatch: 'full',
  },
  {
    path: RoutingPath.HOME,
    component: HomeComponent
  },
  {
    path: RoutingPath.INPUT,
    component: InputComponent
  },
  {
    path: RoutingPath.TEXT,
    component: TextComponent
  },
  {
    path: '**',
    redirectTo: RoutingPath.HOME
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
