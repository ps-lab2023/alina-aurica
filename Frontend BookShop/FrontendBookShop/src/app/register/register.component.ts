import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../model/user";
import {UserService} from "../services/user.service";
import {first} from "rxjs";
import {Role} from "../model/Role";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ){}
  ngOnInit(): void {

  }

  register(){
    console.log(this.user)
    this.userService.register(this.user.firstName, this.user.lastName, this.user.email, this.user.password, Role.CLIENT).subscribe(
      (userAux: User) => {
        console.log(userAux)
        this.user = userAux
        console.log(this.user)
        alert("Register successfully")
        //this.router.navigateByUrl("/login")
      },
      (_error: Error) => {
        alert("Some fields are null or contain invalid data. Please, rewrite them.")
      }
    )
  }

}
