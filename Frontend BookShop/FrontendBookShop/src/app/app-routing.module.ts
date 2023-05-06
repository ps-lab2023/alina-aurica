import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FirstPageComponent} from "./first-page/first-page.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AdminPageComponent} from "./admin-page/admin-page.component";
import {ClientPageComponent} from "./client-page/client-page.component";
import {ViewPageComponent} from "./view-page/view-page.component";
import {GiftCardPageComponent} from "./gift-card-page/gift-card-page.component";
import {ViewUsersPageComponent} from "./view-users-page/view-users-page.component";
import {ChatComponent} from "./chat/chat.component";

const routes: Routes = [
  {path: "firstPage", component:FirstPageComponent},
  {path: "loginPage", component:LoginComponent},
  {path: "registerPage", component:RegisterComponent},
  {path: "adminPage", component:AdminPageComponent},
  {path: "clientPage", component:ClientPageComponent},
  {path: "viewProfilePage", component:ViewPageComponent},
  {path: "giftCardPage", component:GiftCardPageComponent},
  {path: "viewUsersPage", component:ViewUsersPageComponent},
  {path: "chatPage", component: ChatComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
