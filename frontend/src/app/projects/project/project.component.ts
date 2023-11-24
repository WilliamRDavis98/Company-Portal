import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent {

  @Input() project: any;

  // getTimeDifference(timestamp: number): string { //calculate time difference to show last updated time
  //   const currentTimestamp = Date.now();
  //   const difference = currentTimestamp - timestamp;
  
  //   const seconds = Math.floor(difference / 1000);
  //   const minutes = Math.floor(seconds / 60);
  //   const hours = Math.floor(minutes / 60);
  //   const days = Math.floor(hours / 24);
  
  //   if (days > 0) {
  //     return `last updated ${days} ${days === 1 ? 'day' : 'days'} ago`;
  //   } else if (hours > 0) {
  //     return `last updated ${hours} ${hours === 1 ? 'hour' : 'hours'} ago`;
  //   } else if (minutes > 0) {
  //     return `last updated ${minutes} ${minutes === 1 ? 'minute' : 'minutes'} ago`;
  //   } else {
  //     return `last updated ${seconds} ${seconds === 1 ? 'second' : 'seconds'} ago`;
  //   }
  // }  
  checkStatus(status: boolean): string  {
    return status ? 'active' : 'inactive';
  }
}