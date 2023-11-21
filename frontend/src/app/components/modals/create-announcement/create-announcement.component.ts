import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-announcement',
  templateUrl: './create-announcement.component.html',
  styleUrls: ['./create-announcement.component.css']
})
export class CreateAnnouncementComponent {
  announcementForm: FormGroup = new FormGroup({
    message: new FormControl<string>('', [Validators.required]),
    // get from state: company: company.id
    // get from session: author: user.id
  })

  constructor() {}

  ngOnInit(): void {}

  onSubmit() {
    // service: post request to create announcement
    // update announcements array
    // toggle modal
  }
}
