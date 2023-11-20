import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  @Input() modalType: string = '';

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
}
