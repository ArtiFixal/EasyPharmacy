import { Component, Input } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { SelectOption } from './SelectOption';

@Component({
  selector: 'select-form',
  imports: [ReactiveFormsModule],
  templateUrl: './select-form.component.html',
  styleUrl: './select-form.component.css'
})
export class SelectFormComponent {

  @Input({required:true}) control!:FormControl;
  @Input({required:true}) options!:SelectOption[];

}
