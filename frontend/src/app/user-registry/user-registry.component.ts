import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalComponent } from '../components/modals/modal/modal.component';
import { ApiCallsService } from '../services/api-calls.service';
import { DataService } from 'src/app/services/data.service';
import { User } from '../models/user-model';

@Component({
  selector: 'app-user-registry',
  templateUrl: './user-registry.component.html',
  styleUrls: ['./user-registry.component.css'],
})
export class UserRegistryComponent implements OnInit {
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;
  constructor(private router: Router, private apiService: ApiCallsService, private dataService: DataService) { }

  users: Array<User> = [];
  modalType: string = 'create-user';
  showModal: boolean = false;
  companyId: number = 0;
  addedUser: any = null;

  curUser: User | null = this.dataService.activeUser;
  curCompanyId: number | null = this.dataService.activeCompanyId;
  ngOnInit(): void {
    // get the session user
    this.users = [];
    if (this.curUser) {
      if (this.curUser.admin) {
        this.apiService
          .getCompanyUsers(this.curCompanyId!)
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


