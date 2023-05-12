import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../model/user";
import {UserService} from "../services/user.service";
import {first} from "rxjs";
import {Role} from "../model/Role";
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ){}

  ngOnInit(){

  }

  login() {
    console.log(this.user);
    this.userService.login(this.user.email, this.user.password).subscribe(
      (userAux: any) => {
        console.log(userAux.token);

        // if (typeof userAux.email === "string") {
        //   localStorage.setItem("user", userAux.email);
        // }
        localStorage.setItem("token", userAux.token);

        alert("Login successfully");

        var tokenPayload: any;
        tokenPayload = jwt_decode(userAux.token)
        if (tokenPayload.role === "ADMIN") {
          console.log("admin");
          this.router.navigateByUrl("/adminPage");
        } else {
          if (tokenPayload.role === "CLIENT") {
            console.log("client");
            this.router.navigateByUrl("/clientPage");
          }
        }
      },
      (_error: Error) => {
        alert("Email and password are incorrect. Please, rewrite them.");
      }
    );
  }

  // register(){
  //   this.router.navigateByUrl("/registerPage");
  // }

}
