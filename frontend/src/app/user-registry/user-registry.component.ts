import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalComponent } from '../components/modals/modal/modal.component';
import { ApiCallsService } from '../services/api-calls.service';
import { User } from '../models/user-model';


@Component({
  selector: 'app-user-registry',
  templateUrl: './user-registry.component.html',
  styleUrls: ['./user-registry.component.css']
})

export class UserRegistryComponent {
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;
  constructor(private apiService: ApiCallsService) {}

  users: Array<User> = [
    {
      id: 1,
      username: "chrisP",
      firstName: "Chris",
      lastName: "Purnell",
      email: "yocrizzle@gmail.com",
      phoneNumber: "1111111111",
      admin: true,
      active: true,
      status: "JOINED",
      companies: [],
      teams: []
    },
    {
      id: 2,
      username: "willM",
      firstName: "Will",
      lastName: "Marttala",
      email: "wamizzle@gmail.com",
      phoneNumber: "9999999999",
      admin: false,
      active: false,
      status: "PENDING",
      companies: [],
      teams: []
    }
  ]
  modalType: string = 'create-user';
  showModal: boolean = false
  companyId: number = 0

  curUser: User | null = null

  ngOnInit(): void {
    // get the session user
    this.users = []
    let userSessionString = sessionStorage.getItem("user")

    // For debug until session storage is fully implemented
    let DEBUG = true
    if(DEBUG) {
      this.apiService.getCompanyUsers(6).then((response) => {
        response.subscribe((users) => {
          this.users = users as User[]
        })
      })
    }
    if(userSessionString){
      console.log("Found current session's user...")
      this.curUser = JSON.parse(userSessionString)
      // get user company
      if (this.curUser?.companies){
        console.log("Found current session's company...")
        // get company users
        this.apiService.getCompanyUsers(this.curUser?.companies[0].id!).then((response) => {
          response.subscribe(users => {
            this.users = users as User[]
          })
        })
      } else {
        console.log("User has no company")
      }
    } else{
      console.log("No current user found")
    }
  }

  toggleModal() {
    this.modalComponent.toggleModal();
  }
}
