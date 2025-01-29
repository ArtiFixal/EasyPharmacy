import { Component } from '@angular/core';
import { ManufacturerDTO } from '../models/ManufacturerDTO';
import { ManufacturerService } from '../services/manufacturer.service';
import { EntityListComponent } from '../../../shared/entity-list/entity-list.component';

@Component({
  standalone:false,
  selector: 'app-manufacturer',
  templateUrl: './manufacturer-list.component.html',
  styleUrl: './manufacturer-list.component.css'
})
export class ManufacturerComponent extends EntityListComponent<ManufacturerDTO,ManufacturerService>{

  constructor(manufacturereService:ManufacturerService){
    super(manufacturereService)
  }

  protected override localizeAddSuccess(): string {
    return $localize`:|Text describing successful add of manufacturer@@manufacturer.add.success:New manufacturer added successfully`;
  }
  protected override localizeAddFail(): string {
    return $localize`:|Text describing fail of adding new manufacturer@@manufacturer.add.fail:Failed to add manufacturer`;
  }
  protected override localizeEditSuccess(): string {
    return $localize`:|Text describing success of manufacturer edit@@manufactuer.edit.success:Manufacturer updated successfully`;
  }
  protected override localizeEditFail(): string {
    return $localize`:|Text describing fail manufacturer edit@@manufactuer.edit.fail:Failed to update manufacturer`;
  }
}
