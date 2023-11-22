import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalComponent } from '../components/modals/modal/modal.component';
import { User } from '../models/user-model';
import { Router } from '@angular/router';
import { ApiCallsService } from '../services/api-calls.service';
import { Announcement } from '../models/announcement-model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;

  user?: User;
  companyId?: string;

  modalType: string = 'create-announcement';
  announcements: Announcement[] = [];
  
  constructor(private apiCallsService: ApiCallsService, private router: Router) { }

  ngOnInit(): void {
    // authorize
    this.user = JSON.parse(sessionStorage.getItem("user") as string);
    if (!this.user) {
      this.router.navigateByUrl("");
    }
    // get current company id from state/storage
    this.companyId = sessionStorage.getItem("userCompany") as string;
    // get announcements
    if (this.companyId) {
      this.getAnnouncements(this.companyId);
    } else {
      this.router.navigateByUrl("select-company")
    }
  }

  getAnnouncements = async (id: string) => {
    (await this.apiCallsService.getAnnouncements(id)).subscribe((response) => {
      this.announcements = response;
    });
  }

  toggleModal() {
    this.modalComponent.toggleModal();
  }
}
