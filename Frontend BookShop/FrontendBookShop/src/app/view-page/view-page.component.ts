import {Component, OnInit} from '@angular/core';
// @ts-ignore
import {User} from "../model/User";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../services/user.service";

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
    const userAux: any = localStorage.getItem("user")
    this.userService.viewProfile(userAux).subscribe(
      (userProfile: User) => {
        console.log(userProfile)
        this.user = userProfile
      },
      (_error: Error) => {

      }
    )
  }

  logOut(){
    const user: any = localStorage.getItem("user")
    this.userService.logout(user).subscribe(
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
    console.log(this.user)
    const user1: any = localStorage.getItem("user")
    this.userService.changePassword(user1, this.user.password).subscribe(
      (userAux: User) => {
        console.log(userAux)
        this.user = userAux
        alert("Change password successfully")
      },
      (_error: Error) => {
        alert("Change password failed")
      }
    )
  }
}
