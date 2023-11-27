import { Component, Input, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Announcement } from 'src/app/models/announcement-model';
import { User } from 'src/app/models/user-model';
import { ApiCallsService } from 'src/app/services/api-calls.service';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-create-announcement',
  templateUrl: './create-announcement.component.html',
  styleUrls: ['./create-announcement.component.css']
})
export class CreateAnnouncementComponent {
  @Input() announcements: Announcement[] = []

  announcementForm: FormGroup = new FormGroup({
    message: new FormControl<string>('', [Validators.required]),
  })
  // get from state/session: company
  // get from session: author

  constructor(private apiCallsService: ApiCallsService, private router: Router, private modalComponent: ModalComponent) {}
  
  ngOnInit(): void {}

  async onSubmit() {
    let user: User = JSON.parse(sessionStorage.getItem("user") as string);
    let companyId: number = parseInt(sessionStorage.getItem("userCompany") as string);
    if (!user) {
      this.router.navigateByUrl("");
    } else if (!companyId) {
      this.router.navigateByUrl("select-company");
    }
    // service: post request to create announcement
    let requestBody: Object = {
      title: "",
      message: this.announcementForm.controls['message'].value,
      credentials: {
        username: user.username,
        password: user.password,
      }
    }
    ;(await this.apiCallsService.createAnnouncement(companyId, requestBody)).subscribe((response) => {
      // update announcements array
      this.announcements.unshift(response);
    })
    this.modalComponent.toggleModal();
  }
}