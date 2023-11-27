import { Injectable } from '@angular/core';
import { User } from '../models/user-model';
import { Company } from '../models/company-model';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  activeUser: User | null = null;
  activeCompanyId: number | null = null;
  teamId: number | null = null;
  teamName: string | null = null;
}
