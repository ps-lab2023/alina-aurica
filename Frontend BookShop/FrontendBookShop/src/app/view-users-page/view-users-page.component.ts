import {Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
// @ts-ignore
import {User} from "../model/User";

@Component({
  selector: 'app-view-users-page',
  templateUrl: './view-users-page.component.html',
  styleUrls: ['./view-users-page.component.css']
})
export class ViewUsersPageComponent implements OnInit{

  users: User[] = [];
  id: any
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ){}


  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      (result: User[]) => {
        console.log(result);
        this.users = result;
      },
      (_error: Error) => {
        console.log("error");
      }
    );
  }

  saveUserToXML(): void {
    console.log(this.id)
    this.userService.saveUsersToXML(this.id).subscribe(() => alert("Saved!"));
  }

}
