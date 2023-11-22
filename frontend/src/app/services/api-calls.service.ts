import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { User } from '../models/user-model';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root',
})
export class ApiCallsService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private dataService: DataService) {}

  testUser1: User = {
    id: 1,
    username: 'TestUser',
    password: 'pass',
    firstName: 'Test',
    lastName: 'User',
    email: 'test@user.com',
    active: true,
    admin: true, //Change this value to change login page for User
    status: 'Joined',
  };

  /**
   * Log-In API Call
   * Returns promise while it waits on the confirmation from the BE
   */
  async login(username: String, password: String) {
    /** make backend api call for the user */
    let requestUrl: string = this.apiUrl + '/users/login';
    let requestBody: Object = { username, password };

    return this.http.post<any>(requestUrl, requestBody).pipe(
      map((response) => {
        const authenticatingUser: User = {
          id: response.id,
          username: username,
          password: password,
          firstName: response.profile.firstName,
          lastName: response.profile.lastName,
          email: response.profile.email,
          active: response.active,
          admin: response.admin,
          status: response.status,
        };

        console.log('User Object:', authenticatingUser);

        this.dataService.activeUser = authenticatingUser;

        return authenticatingUser;
      }),
      catchError((error) => {
        if (error.status === 404) {
          // Handle the "user not found" error here, e.g., show a message to the user
          console.error('User not found:', error);
        } else {
          // Handle other errors, such as network issues or server errors
          console.error('Login error:', error);
        }

        // Rethrow the error or return a default value as needed
        return throwError(error);
      })
    );
  }
}
