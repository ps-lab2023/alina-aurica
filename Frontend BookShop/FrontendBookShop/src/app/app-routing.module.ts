import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FirstPageComponent} from "./first-page/first-page.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AdminPageComponent} from "./admin-page/admin-page.component";
import {ClientPageComponent} from "./client-page/client-page.component";
import {ViewPageComponent} from "./view-page/view-page.component";

const routes: Routes = [
  {path: "firstPage", component:FirstPageComponent},
  {path: "loginPage", component:LoginComponent},
  {path: "registerPage", component:RegisterComponent},
  {path: "adminPage", component:AdminPageComponent},
  {path: "clientPage", component:ClientPageComponent},
  {path: "viewProfilePage", component:ViewPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
