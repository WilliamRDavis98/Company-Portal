import { Component } from '@angular/core';
import {TeamComponent} from "./team/team.component";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent {

  protected readonly TeamComponent = TeamComponent;

  //   GET companies/{id}/teams
  //   Get Company ID from Session Storage

  teams = [
    {a: 1},
    {a: 1},
    {a: 1},
  ];



}
