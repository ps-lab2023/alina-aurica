import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FirstPageComponent } from './first-page/first-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { RegisterComponent } from './register/register.component';
import { ClientPageComponent } from './client-page/client-page.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { ViewPageComponent } from './view-page/view-page.component';
import { GiftCardPageComponent } from './gift-card-page/gift-card-page.component';
import { ViewUsersPageComponent } from './view-users-page/view-users-page.component';
import { ChatComponent } from './chat/chat.component';
import { ReviewPageComponent } from './review-page/review-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FirstPageComponent,
    RegisterComponent,
    ClientPageComponent,
    AdminPageComponent,
    ViewPageComponent,
    GiftCardPageComponent,
    ViewUsersPageComponent,
    ChatComponent,
    ReviewPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
