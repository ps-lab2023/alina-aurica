import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../model/user";
import {UserService} from "../services/user.service";
import {first} from "rxjs";
import {Role} from "../model/Role";

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
      (userAux: User) => {
        console.log(userAux);
        this.user = userAux;
        console.log(this.user);
        if (typeof userAux.email === "string") {
          localStorage.setItem("user", userAux.email);
        }
        alert("Login successfully");
        console.log(userAux.role);
        // @ts-ignore
        if (userAux.role === "ADMIN") {
          console.log("admin");
          this.router.navigateByUrl("/adminPage");
        } else { // @ts-ignore
          if (userAux.role === "CLIENT") {
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
