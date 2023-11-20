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
  
  constructor() { }

  ngOnInit(): void {

  }

  toggleModal() {
    this.modalComponent.toggleModal();
  }
}
