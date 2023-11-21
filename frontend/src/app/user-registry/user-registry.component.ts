import { Component } from '@angular/core';

@Component({
  selector: 'app-user-registry',
  templateUrl: './user-registry.component.html',
  styleUrls: ['./user-registry.component.css']
})

export class UserRegistryComponent {

  users: Array<any> = [
    {
      id: 1,
      profile: {
        firstname: "Chris",
        lastname: "Pumell",
        email: "yocrizzle@gmail.com",
        phone: "1111111111"
      },
      isAdmin: true,
      active: true,
      status: "JOINED"
    },
    {
      id: 2,
      profile: {
        firstname: "Will",
        lastname: "Marttala",
        email: "wamizzle@gmail.com",
        phone: "9999999999"
      },
      isAdmin: false,
      active: false,
      status: "PENDING"
    }
  ]

  showModal: boolean = false


  addUser(): void {
    this.showModal = !this.showModal
  }
}
