import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalComponent } from '../components/modals/modal/modal.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;

  modalType: string = 'create-announcement';
  announcements: any[] = [];
  companyId: number | undefined;
  
  constructor() { }

  ngOnInit(): void {
    // authorize
    // set company id from state
    // get announcements
    if (this.companyId) {
      this.getAnnouncements(this.companyId);
    }
  }

  getAnnouncements = async (id: number) => {

  }

  toggleModal() {
    this.modalComponent.toggleModal();
  }
}
