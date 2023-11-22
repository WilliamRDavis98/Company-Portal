import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {
  newUserForm: FormGroup = new FormGroup({
    username: new FormControl<string>('', [Validators.required]),
    first: new FormControl<string>('', [Validators.required]),
    last: new FormControl<string>('', [Validators.required]),
    email: new FormControl<string>('', [Validators.required]),
    phone: new FormControl<string>('', [Validators.required]),
    password: new FormControl<string>('', [Validators.required]),
    confirm: new FormControl<string>('', [Validators.required]),
  })

  constructor() {}

  ngOnInit(): void {

  }

  onSubmit() {
    // validate username
    // post new user
    // toggle modal
  }
}
