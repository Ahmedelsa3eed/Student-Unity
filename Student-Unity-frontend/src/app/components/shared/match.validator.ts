import { FormControl, FormGroup, FormBuilder, Validators, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';


export function ConfirmedValidator(controlName: string, matchingControlName: string) : ValidatorFn {
  return (control: AbstractControl) : ValidationErrors | null => {
    const FormGroup = control as FormGroup;
    const controlValue  = FormGroup.get(controlName)?.value;
    const matchingControlValue  = FormGroup.get(matchingControlName)?.value;

    if (control.get('password')?.value === control.get('rPassword')?.value) {
      return null;
    } else {
      return { ValudesNotMatch: true };
    }
  }
}
