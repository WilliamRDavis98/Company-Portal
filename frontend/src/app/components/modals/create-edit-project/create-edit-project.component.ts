import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
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
  @Output() closeModal: EventEmitter<any> = new EventEmitter<any>();
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
      this.getProjectsById(this.projId); //get project by Id from DB and show its content in modal
    } else {
      console.log('new project.');
      this.resetForm();
    }
  }

  onSubmit() {
    let newName = this.projectForm.get('projectName')?.value;
    let newDescription = this.projectForm.get('projectDescription')?.value;
    if (this.projId) {
      // console.log('Editing project ', this.projId);
      this.editProject(newName,newDescription);
    } else {
      // console.log('Creating new project...');
      this.postNewProject(newName, newDescription); //post new project
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
        // console.log('Received Data: ', this.projects);

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

  postNewProject = async (newName: string, newDescription: string) => {
    this.loading = true;


    // console.log(newName);
    // console.log(newDescription);

    const storedUser = sessionStorage.getItem('user');
    if (storedUser) {
      const { username, password } = JSON.parse(storedUser);
      const teamId = 11
      
      let requestBody: Object = {
        name: newName,
        description: newDescription,
        credentials: {
          username: username,
          password: password
        },
      }
      console.log(requestBody);
      (await this.apiCallsService.createProject(teamId, requestBody)).subscribe(
        (response) => {
          setTimeout(() => {
          // console.log("data stored succesfully", response);
          this.closeModal.emit();
          this.resetForm();
          this.loading = false;
        }, 3000);
      },(error) => {
        console.error('Error storing new projects:', error);
        this.loading = false;
      })
    } else {
      console.error('No user data found in sessionStorage');
      this.loading = false;
    }
  };

  editProject = async (newName: string, newDescription: string) => {
    this.loading = true;

    const storedUser = sessionStorage.getItem('user');
    if (storedUser) {
      const { username, password } = JSON.parse(storedUser);
      const teamId = 11
      
      let requestBody: Object = {
        name: newName,
        description: newDescription,
        credentials: {
          username: username,
          password: password
        },
      }
      console.log(requestBody);
      (await this.apiCallsService.editProject(this.projId, requestBody)).subscribe(
        (response) => {
          setTimeout(() => {
          // console.log("data stored succesfully", response);
          this.closeModal.emit();
          this.loading = false;
        }, 3000);
      },(error) => {
        console.error('Error updating projects:', error);
        this.loading = false;
      })
    } else {
      console.error('No user data found in sessionStorage');
      this.loading = false;
    }
  };
}
