import { Component, Input } from '@angular/core';
import { Announcement } from 'src/app/models/announcement-model';
import { User } from 'src/app/models/user-model';

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent {
  @Input() announcement!: Announcement;
  
  months: string[] = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ]

  user!: User;
  date!: Date;

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem("user") as string);
    this.date = new Date(this.announcement.date)
  }
  // get user.first from service?
}