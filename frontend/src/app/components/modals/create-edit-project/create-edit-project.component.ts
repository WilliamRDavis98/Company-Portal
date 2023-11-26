import { Component, Input, SimpleChanges } from '@angular/core';
import {
  FormControl,
  FormControlName,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ApiCallsService } from 'src/app/services/api-calls.service';
import { Project } from 'src/app/models/project-model';

@Component({
  selector: 'app-create-edit-project',
  templateUrl: './create-edit-project.component.html',
  styleUrls: ['./create-edit-project.component.css'],
})
export class CreateEditProjectComponent {
  @Input() projId: any;
  projects: Project[] = [];

  loading: boolean = false;

  projectForm: FormGroup = new FormGroup({
    projectName: new FormControl<string>('', [Validators.required]),
    projectDescription: new FormControl<string>('', [Validators.required]),
  });

  constructor(private apiCallsService: ApiCallsService) {}

  ngOnInit(): void {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['projId'] && changes['projId'].currentValue) {
      console.log('Editing project with projId:', this.projId);
      this.getProjectsById(this.projId);
    } else {
      console.log('new project.');
      this.resetForm();
    }
  }

  onSubmit() {
    if (this.projId) {
      console.log('Editing project ', this.projId);
      //post project id
      console.log('id: ', this.projId);
      console.log('name: ', this.projectForm.get('projectName')?.value);
      console.log('desc: ', this.projectForm.get('projectDescription')?.value);
    } else {
      console.log('Creating new project...');
      console.log('id: ', this.projId);
      console.log('name: ', this.projectForm.get('projectName')?.value);
      console.log('desc: ', this.projectForm.get('projectDescription')?.value);
      //post new project
    }
  }

  private resetForm() {
    this.projectForm.reset({
      projectName: '',
      projectDescription: '',
    });
  }

  getProjectsById = async (id: number) => {
    this.loading = true;

    (await this.apiCallsService.getProjectById(id)).subscribe(
      (response) => {
        this.projects = [response as unknown as Project];
        console.log('Received Data: ', this.projects);

        setTimeout(() => {
          if (this.projects.length > 0) {
            this.projectForm.patchValue({
              projectName: this.projects[0].name,
              projectDescription: this.projects[0].description,
            });
          } else {
            console.error('No projects found');
            this.resetForm();
          }
          this.loading = false;
        }, 1000);
      },
      (error) => {
        console.error('Error fetching projects:', error);
        this.loading = false;
      }
    );
  };
}
