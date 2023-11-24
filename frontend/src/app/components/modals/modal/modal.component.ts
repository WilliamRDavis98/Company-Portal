import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Announcement } from 'src/app/models/announcement-model';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  @Input() modalType: string = '';
  @Input() announcements: Announcement[] = []


  projectId: any; 
  
  toggleModal() {
    const overlay = document.getElementById('overlay');
    const modal = document.getElementById('modal');

    if (overlay && modal) {
      if (overlay.style.display === 'block') {
        overlay.style.display = 'none';
        modal.style.display = 'none';
      } else {
        overlay.style.display = 'block';
        modal.style.display = 'block'; 
      }
    }
  }

  setProjectId(projectId: any) {
    this.projectId = projectId;
  }
}
