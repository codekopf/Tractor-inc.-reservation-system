import { Component, OnInit } from '@angular/core';
import { TractorService } from '../shared/tractor.service';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'my-tractor-list',
  templateUrl: './tractor-list.component.html',
  styleUrls: ['./tractor-list.component.scss'],
  providers: [TractorService]
})
export class TractorListComponent implements OnInit {

  public tractorList: any;
  public filterForm: FormGroup;
  public newCarForm: FormGroup;
  public showNewCarForm: boolean;
  public filtred: boolean;

  // Custom
  public specificTractorID: number;

  constructor(public tractorService: TractorService, private fb: FormBuilder) {
    this.getCars();
  }

  ngOnInit() {
    console.log('hello tractor list');
    this.filterForm = this.fb.group({
      acquiredFrom: ['2007-08-21'],
      acquiredTo: ['2007-08-23']
    });


    this.newCarForm = this.fb.group({
      type: ['RECLAIMER'],
      vin: ['AHTBB3QD001726541'],
      testDatum: [1487812893],
      price: [1100000]
    });

  }

  public getCars() {
    this.filtred = false;
    this.tractorList = this.tractorService.getCars()
      .subscribe(
        tractors => this.tractorList = tractors,
        error => console.error('Error: ' + error),
        () => console.log('Completed!')
      );
  }

  public filterTractor() {
    let filter: SearchParams = {
      acquiredFrom: this.filterForm.value.acquiredFrom,
      acquiredTo: this.filterForm.value.acquiredTo
    };

    this.filtred = true;
    this.tractorService.findCars(filter).subscribe(
      tractors => this.tractorList = tractors,
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }

  public reloadCars() {
    this.getCars();
    this.showNewCarForm = false;
  }

  public createNewCar() {
    this.addNewCar(this.newCarForm.value);
  }

  public addNewCar(newCar: any) {
    this.tractorService.addCar(newCar).subscribe(
      () => this.reloadCars(),
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }



}
