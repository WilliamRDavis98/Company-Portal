import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiCallsService } from '../services/api-calls.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private router: Router, private apiService: ApiCallsService) {}

  username!: String
  password!: String

  onSubmit() {
    console.log("Username, password:", this.username, this.password)

    this.apiService.login(/**pass in username/password */).then((response) => {

      //Logic to check the user is valid and if they are an admin or not

      //if(user.isAdmin) -> then log in to "Select Company" page
      //elseif(!user.isAdmin) -> log in to the "Teams" page

      this.router.navigateByUrl("/select-company")
    })
  }
}
