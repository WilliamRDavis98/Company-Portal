import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiCallsService } from '../services/api-calls.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {
  teams: any[] = [];
  CompanyID: any | null;
  openNewTeamModal(): void {}

  constructor(private router: Router, private apiCallsService: ApiCallsService, private dataService: DataService) {}

  ngOnInit() {
    let curUser = this.dataService.activeUser
    if (!curUser || !curUser.admin) {
      this.router.navigateByUrl("/login")
    }
    this.CompanyID = this.dataService.activeCompanyId
    if (this.CompanyID) {
      this.apiCallsService.getTeamsByCompanyId(this.CompanyID).subscribe(
        (teamsData) => {
          this.teams = teamsData;
          this.teams.forEach((team) => {
            this.apiCallsService.getProjectsByTeamId(this.CompanyID, team.id).subscribe(
              (projects) => {
                team.projectsCount = projects.length;
              },
              (error) => {
                console.error('Error fetching projects for team', team.id, error);
              }
            );
          });
        },
      );
    }
  }
}
