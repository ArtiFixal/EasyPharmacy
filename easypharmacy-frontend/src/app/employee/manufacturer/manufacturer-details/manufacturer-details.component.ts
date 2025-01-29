import { Component } from '@angular/core';
import { ManufacturerService } from '../services/manufacturer.service';
import { ManufacturerDTO } from '../models/ManufacturerDTO';
import { EntityDetailsPage } from '../../shared/EntityDetailsPage';

@Component({
  standalone: false,
  selector: 'app-manufacturer-details',
  templateUrl: './manufacturer-details.component.html',
  styleUrl: './manufacturer-details.component.css',
})
export class ManufacturerDetailsComponent extends EntityDetailsPage<ManufacturerDTO, ManufacturerService> {

  constructor(manufacturerService: ManufacturerService) {
    super(manufacturerService)
  }
}
