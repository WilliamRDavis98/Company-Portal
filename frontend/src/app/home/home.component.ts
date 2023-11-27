import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalComponent } from '../components/modals/modal/modal.component';
import { User } from '../models/user-model';
import { Router } from '@angular/router';
import { ApiCallsService } from '../services/api-calls.service';
import { DataService } from 'src/app/services/data.service';
import { Announcement } from '../models/announcement-model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;

  user: User | null = this.dataService.activeUser;
  companyId: number | null = this.dataService.activeCompanyId;

  modalType: string = 'create-announcement';
  announcements: Announcement[] = [];
  
  constructor(private apiCallsService: ApiCallsService, private router: Router, private dataService: DataService) { }

  ngOnInit(): void {
    // authorize
    if (!this.user) {
      this.router.navigateByUrl("login");
    }
    this.getAnnouncements(this.companyId!);
  }

  getAnnouncements = async (id: number) => {
    (await this.apiCallsService.getAnnouncements(id)).subscribe((response) => {
      this.announcements = response;
    });
  }

  toggleModal() {
    this.modalComponent.toggleModal();
  }
}
