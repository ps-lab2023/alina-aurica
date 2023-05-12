import {Component, OnInit} from '@angular/core';
// @ts-ignore
import {User} from "../model/User";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../services/user.service";
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-view-page',
  templateUrl: './view-page.component.html',
  styleUrls: ['./view-page.component.css']
})
export class ViewPageComponent implements OnInit{

  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ){}

  ngOnInit(){
    this.viewProfile()
  }

  viewProfile(){
    const user: any = localStorage.getItem("token")
    console.log(user)
    var tokenPayload: any;
    tokenPayload = jwt_decode(user)
    this.userService.viewProfile(tokenPayload.sub).subscribe(
      (userProfile: User) => {
        console.log(userProfile)
        this.user = userProfile
      },
      (_error: Error) => {

      }
    )
  }

  logOut(){
    const user: any = localStorage.getItem("token")
    console.log(user)
    var tokenPayload: any;
    tokenPayload = jwt_decode(user)
    this.userService.logout(tokenPayload.sub).subscribe(
      (userAux: User) => {
        console.log(userAux)
        this.user = userAux
        localStorage.clear()
        alert("LogOut successfull")
        this.router.navigateByUrl("/firstPage");
      },
      (_error: Error) => {
        alert("LogOut failed")
      }
    )
  }

  changePassword(){
    const user1: any = localStorage.getItem("token")
    console.log(user1)
    var tokenPayload: any;
    tokenPayload = jwt_decode(user1)
    this.userService.changePassword(tokenPayload.sub, this.user.password).subscribe(
      (userAux: any) => {
        console.log(userAux.token)
        localStorage.setItem("token", userAux.token);

        //this.user = userAux
        alert("Change password successfully")
      },
      (_error: Error) => {
        alert("Change password failed")
      }
    )
  }
}
