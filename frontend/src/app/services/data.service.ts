import { Injectable } from '@angular/core';
import { User } from '../models/user-model';
import { Company } from '../models/company-model';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  activeUser: User | null = null;
  activeCompany: Company | null = null;
}
