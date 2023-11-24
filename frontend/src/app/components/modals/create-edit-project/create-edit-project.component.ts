import { Component, Input, SimpleChanges } from '@angular/core';
import { FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-edit-project',
  templateUrl: './create-edit-project.component.html',
  styleUrls: ['./create-edit-project.component.css']
})
export class CreateEditProjectComponent {

  @Input() projId:any;

  projectForm: FormGroup = new FormGroup({
    projectName: new FormControl<string>('', [Validators.required]),
    projectDescription: new FormControl<string>('', [Validators.required]),
  })

  constructor() {}

  ngOnInit(): void {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['projId'] && changes['projId'].currentValue) {
      console.log('Editing project with projId:', this.projId);
      //get project with id
      this.projectForm.patchValue({
        projectName: this.projId+" name",
        projectDescription: this.projId+" Description"
      });
    }else{
      console.log('new project.');
      this.resetForm();
    }
  }

  onSubmit() {
    if (this.projId) {
      console.log("Editing project ",this.projId);
      //post project id
      console.log("id: ",this.projId);
      console.log("name: ",this.projectForm.get('projectName')?.value);
      console.log("desc: ",this.projectForm.get('projectDescription')?.value);
      
    }else {
      console.log('Creating new project...');
      console.log("id: ",this.projId);
      console.log("name: ",this.projectForm.get('projectName')?.value);
      console.log("desc: ",this.projectForm.get('projectDescription')?.value);
      //post new project
    }
  }

  private resetForm() {
    this.projectForm.reset({
      projectName: '',
      projectDescription: ''
    });
  }

}
