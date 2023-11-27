import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators, ValidatorFn, AbstractControl, ValidationErrors, FormBuilder } from '@angular/forms';
import { ApiCallsService } from 'src/app/services/api-calls.service';
import { User } from 'src/app/models/user-model';
import { DataService } from 'src/app/services/data.service';
import { ModalComponent } from '../modal/modal.component';
import { Company } from 'src/app/models/company-model';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {
  @ViewChild(ModalComponent) modalComponant!: ModalComponent;
  newUserForm: FormGroup = new FormGroup({
    username: new FormControl<string>('', [Validators.required]),
    first: new FormControl<string>('', [Validators.required]),
    last: new FormControl<string>('', [Validators.required]),
    email: new FormControl<string>('', [Validators.required]),
    phone: new FormControl<string>('', [Validators.required]),
    password: new FormControl<string>('', [Validators.required]),
    confirm: new FormControl<string>('', [Validators.required]),
    admin: new FormControl<boolean>(false)
  })

  curCompany: Company | null = { 
    id: 6,
    name: "Test",
    description: "Test"}//this.dataService.activeCompany
  passMatch: boolean = true
  validUser: boolean = true
  @Output() addedUser = new EventEmitter<any>();

  checkPasswords: ValidatorFn = (group: AbstractControl):  ValidationErrors | null => { 
    if(group.get('password')){
      if(group.get('password')){
        let pass = this.newUserForm.controls['password'].value
        let confirmPass = this.newUserForm.controls['confirm'].value
        return pass === confirmPass ? null : { notSame: true }
      }
    }
    return { notSame: true }
  }
  curUser: User | null = this.dataService.activeUser

  constructor(private apiService: ApiCallsService, private dataService: DataService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.newUserForm.addValidators(this.checkPasswords)
    // this.newUserForm = this.fb.group({
    //   password: ['', [Validators.required]],
    //   confirmPassword: ['']
    // }, { validators: this.checkPasswords })
  }

  async onSubmit() {
    if(this.newUserForm.controls['password'].value !== this.newUserForm.controls['confirm'].value){
      this.passMatch = false
      return
    }
    this.passMatch = true
    // validate username
    if(!this.apiService.validateUsernameAvailable(this.newUserForm.controls['username'].value)) {
      this.validUser = false
      return
    }
    if(!this.curCompany){
      return
    }
    // toggle modal
    this.toggleModal()
    // post new user
    let newUser: User = {
      id: 1, // not submitted, but added to fit user model
      username: this.newUserForm.controls['username'].value,
      password: this.newUserForm.controls['password'].value,
      firstName: this.newUserForm.controls['first'].value,
      lastName: this.newUserForm.controls['last'].value,
      email: this.newUserForm.controls['email'].value,
      phoneNumber: this.newUserForm.controls['phone'].value,
      active: false,
      admin: false,
      status: ""
    };
    (await this.apiService.createUser(newUser, this.curCompany?.id)).subscribe((response) => {
      this.addedUser.emit(response)
    })

  }
  toggleModal() {
    const overlay = document.getElementById('overlay');
    const modal = document.getElementById('modal');

    if (overlay && modal) {
      if (overlay.style.display === 'block') {
        overlay.style.display = 'none';
        modal.style.display = 'none';
      } else {
        overlay.style.display = 'block';
        modal.style.display = 'block';
      }
    }
  }

}
