import {Component, OnInit, ViewChild} from '@angular/core';
import { ApiCallsService } from '../services/api-calls.service';
import {ModalComponent} from "../components/modals/modal/modal.component";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {
  teams: any[] = [];
  CompanyID: any | null;
  modalType: string = 'create-team';
  @ViewChild(ModalComponent) modalComponent!: ModalComponent;


  constructor(private apiCallsService: ApiCallsService) {}

  ngOnInit() {
    // this.CompanyID = sessionStorage.getItem('userCompany') || null;
    // temp for testing
    this.CompanyID = 6
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
  toggleModal(){
    this.modalComponent.toggleModal()
  }
}
