import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalComponent } from '../components/modals/modal/modal.component';
import { ApiCallsService } from '../services/api-calls.service';
import { DataService } from 'src/app/services/data.service';
import { User } from '../models/user-model';
import { Company } from '../models/company-model';

@Component({
  selector: 'app-user-registry',
  templateUrl: './user-registry.component.html',
  styleUrls: ['./user-registry.component.css'],
})
export class UserRegistryComponent implements OnInit {
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;
  constructor(private router: Router, private apiService: ApiCallsService, private dataService: DataService) { }

  users: Array<User> = [
    {
      id: 1,
      username: 'chrisP',
      password: 'pass',
      firstName: 'Chris',
      lastName: 'Purnell',
      email: 'yocrizzle@gmail.com',
      phoneNumber: '1111111111',
      admin: true,
      active: true,
      status: 'JOINED',
      companies: [],
      teams: [],
    },
    {
      id: 2,
      username: 'willM',
      password: 'pass',
      firstName: 'Will',
      lastName: 'Marttala',
      email: 'wamizzle@gmail.com',
      phoneNumber: '9999999999',
      admin: false,
      active: false,
      status: 'PENDING',
      companies: [],
      teams: [],
    },
  ];
  modalType: string = 'create-user';
  showModal: boolean = false;
  companyId: number = 0;
  addedUser: any = null;

  curUser: User | null = {
    id: 1,
    username: 'chrisP',
    password: 'pass',
    firstName: 'Chris',
    lastName: 'Purnell',
    email: 'yocrizzle@gmail.com',
    phoneNumber: '1111111111',
    admin: true,
    active: true,
    status: 'JOINED',
    companies: [],
    teams: [],
}//this.dataService.activeUser;
  curCompany: Company | null = {
    id: 6,
    name: "Test Company",
    description: "Test"
  }//this.dataService.activeCompany;
  ngOnInit(): void {
    // get the session user
    this.users = [];
    console.log(this.curUser)
    console.log(this.curCompany)
    if (this.curUser) {
      if (this.curUser.admin) {
        this.apiService
          .getCompanyUsers(this.curCompany!.id)
          .then((response) => {
            response.subscribe((users) => {
              this.users = users as User[];
            });
          });
      } else {
        this.router.navigateByUrl("/home")
      }
    } else {
      this.router.navigateByUrl("/login")
    }
  }

  toggleModal() {
    this.modalComponent.toggleModal();
  }

  public checkChildForData(event: any): void {
    console.log(event)
    let addingUser: User = {
      id: 0,
      username: event.profile.username,
      password: '',
      firstName: event.profile.firstName,
      lastName: event.profile.lastName,
      email: event.profile.email,
      active: event.active,
      admin: event.admin,
      status: event.status
    }
    this.users.push(addingUser)
  }
}


