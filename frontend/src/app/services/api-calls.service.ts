import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiCallsService {
  private apiUrl = 'http://localhost:4200/'

  constructor(private http: HttpClient) {}

  /**
   * Log-In API Call
   * Returns promise while it waits on the confirmation from the BE
   */
  async login(/**some kind of params */) { /** make backend api call for the user */}

}
