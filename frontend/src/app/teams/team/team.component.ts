import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  @Input() teamData: any;

  members: string[] = [];

  ngOnInit(): void {
    if (this.teamData && this.teamData.description) { // This is ensuring whitespace and empty names are omitted.
      const separators = [',', '&'];
      const regex = new RegExp(separators.join('|'), 'g');
      this.members = this.teamData.description.split(regex)
        .map((s: string) => s.trim())
        .filter((name: string) => name !== "");
    }
  }
}
