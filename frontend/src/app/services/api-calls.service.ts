import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { User } from '../models/user-model';
import { DataService } from './data.service';
import { Announcement } from '../models/announcement-model';
import { Company } from '../models/company-model';

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
    companies: [],
    teams: [],
  };

  /**
   * Log-In API Call
   * Returns promise while it waits on the confirmation from the BE
   */
  async login(username: string, password: string) {
    /** make backend api call for the user */
    let requestUrl: string = this.apiUrl + '/users/login';
    let requestBody: Object = { username, password };

    return this.http.post<any>(requestUrl, requestBody).pipe(
      map((response) => {
        //Create user Object based on response data
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
          companies: response.companies,
          teams: response.teams,
        };

        // Make an Array of User Team Id's
        let userTeams: String[] = response.teams.map((team: any) => {
          return team.id;
        });

        // Use Session Storage for User object, team id's, and company id
        sessionStorage.setItem('user', JSON.stringify(authenticatingUser));
        sessionStorage.setItem('userTeams', JSON.stringify(userTeams));
        sessionStorage.setItem('userCompany', response.companies[0].id);

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

  async getCompanyUsers(companyId: number) {
    let requestUrl: string = this.apiUrl + '/companies/' + companyId + '/users';
    return this.http.get<any>(requestUrl).pipe(
      map((response) => {
        console.log('Response:', response);
        let responseUsers: Array<any> = response;
        let users: Array<User> = [];
        for (let i = 0; i < responseUsers.length; i++) {
          let newUser: User = {
            id: responseUsers[i].id,
            username: responseUsers[i].profile.username,
            password: responseUsers[i].profile.password,
            firstName: responseUsers[i].profile.firstName,
            lastName: responseUsers[i].profile.lastName,
            email: responseUsers[i].profile.email,
            phoneNumber: responseUsers[i].profile.phone,
            active: responseUsers[i].active,
            admin: responseUsers[i].admin,
            status: responseUsers[i].status,
            companies: responseUsers[i].companies,
            teams: responseUsers[i].teams,
          };
          users.push(newUser);
        }
        return users;
      }),
      catchError((error) => {
        if (error.status === 404) {
          // Handle the "user not found" error here, e.g., show a message to the user
          console.error('Users not found:', error);
        }
        // Rethrow the error or return a default value as needed
        return throwError(error);
      })
    );
  }

  async getAnnouncements(id: string) {
    let requestUrl: string = this.apiUrl + `/companies/${id}/announcements`;
    return this.http.get<Announcement[]>(requestUrl);
  }

  async createAnnouncement(id: number, requestBody: Object) {
    let requestUrl: string = this.apiUrl + `/companies/${id}/announcements`;
    return this.http.post<Announcement>(requestUrl, requestBody);
  }
}
