import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiCallsService } from '../services/api-calls.service';
import { User } from '../models/user-model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private router: Router, private apiService: ApiCallsService) {}

  username!: string
  password!: string
  error: string = ""

  onSubmit() {
    console.log("Username: ", this.username,", Password:", this.password)

    this.apiService.login(this.username, this.password).then((response) => {

      response.subscribe((user) => {
        if(user) {
          this.error=""
          //Logic to check the user is valid and if they are an admin or not
          if(user.admin) {
            console.log("User is an Admin")
            this.router.navigateByUrl("/select-company")
          } else {
            console.log("User is not an Admin")
            this.router.navigateByUrl("/announcements")
          }
        }
      }, (error) => {
        console.log("bonus error:",error.error.message)
        this.error = error.error.message
      })
    })
  }
}
