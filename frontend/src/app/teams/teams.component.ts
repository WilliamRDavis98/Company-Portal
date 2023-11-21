import { Component } from '@angular/core';
import {TeamComponent} from "./team/team.component";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent {

  protected readonly TeamComponent = TeamComponent;

  teams = [
    {a: 1},
    {a: 1},
    {a: 1},
    {a: 1},
    {a: 1},
    {a: 1},
  ];
}
