import { Component, OnInit } from '@angular/core';
import { LendingService } from '../shared/lending.service';
import { TractorService } from '../shared/tractor.service';
import { ClientService } from '../shared/client.service';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'my-lending-list',
  templateUrl: './lending-list.component.html',
  styleUrls: ['./lending-list.component.scss'],
  providers: [LendingService, TractorService, ClientService]
})
export class LendingListComponent implements OnInit {

  // Default
  public lendingList: any;
  public filterForm: FormGroup;

  // Filtered list
  public filtred: boolean;

  // Create new entry
  public newLendingForm: FormGroup;
  public showNewLendingForm: boolean;
  public vehicleList: any;
  public clientList: any;

  // Editing existing entry
  public showOldLendingForm: boolean;
  public oldLendingForm: FormGroup;
  public oldLending: any;
  public lending: any;

  // Searching for available vehicles
  public availableVehicleList: any;

  constructor(public lendingService: LendingService, public tractorService: TractorService, public clientService: ClientService, private fb: FormBuilder) {
    // Uncomment below if you want to load all lendings on page load
    // this.getLendings();

    // Establishing lists with clients and vehicles for creating new entry
    this.getVehicles();
    this.getClients();
  }

  ngOnInit() {
    console.log('OnInit - Setting lending list');

    this.filterForm = this.fb.group({
      dateFrom: ['2015-10-10'],
      dateTo: ['2018-10-10']
    });

    this.newLendingForm = this.fb.group({
      car: ['RECLAIMER'],
      client: [''],
      dateFrom: ['2012-10-10'],
      dateTo: ['2012-12-12'],
      lattitude: ['3'],
      longitude: ['2'],
      price: ['0']
    });

    this.showOldLendingForm = false;
  }

  // Get all lendings entries
  public getLendings() {
    this.filtred = false;
    this.lendingList = this.lendingService.getLendings()
      .subscribe(
        lendings => this.lendingList = lendings,
        error => console.error('Error: ' + error),
        () => console.log('Completed!')
      );
  }

  // Get all vehicles entries
  public getVehicles() {
    this.tractorService.getCars()
      .subscribe(
        vehicle => this.vehicleList = vehicle,
        error => console.error('Error: ' + error),
        () => console.log('getVehicles() completed!')
      );
  }

  // Get all vehicles entries
  public getClients() {
    this.clientService.getClients()
      .subscribe(
        client => this.clientList = client,
        error => console.error('Error: ' + error),
        () => console.log('getClients() completed!')
      );
  }

  // Return filter lendings
  public filterLending() {
    let filter: LendingSearchParams = {
      dateFrom: this.filterForm.value.dateFrom,
      dateTo: this.filterForm.value.dateTo,
      type: ''
    };
    this.filtred = true;
    this.lendingService.findLendings(filter).subscribe(
      lendings => this.lendingList = lendings,
      error => console.error('Error: ' + error),
      () => console.log( this.lendingList + 'filterLending() completed!')
    );
  }








  // searchAvailableVehicle
  public searchAvailableVehicle() {
    console.log('ASDDDDDD');
    let filter: LendingSearchParams = {
      type: this.filterForm.value.type,
      dateFrom: this.filterForm.value.dateFrom,
      dateTo: this.filterForm.value.dateTo
    };
    console.log('ASD');
    this.filtred = true;
    this.lendingService. filterAvailableVehicles(filter).subscribe(
      vehicles => this.availableVehicleList = vehicles,
      error => console.error('Error: ' + error),
      () => console.log( this.lendingList + 'filterLending() completed!')
    );
  }
















  //
  public reloadLendings() {
    this.getLendings();
    this.showNewLendingForm = false;
  }

  //
  public reloadLendingOverOldLendingForm() {
    this.getLendings();
    this.showOldLendingForm = false;
  }

  //
  public createNewLending() {
    this.addNewLending(this.newLendingForm.value);
  }

  //
  public addNewLending(newLending: any) {
    console.log('Adding new lending.');
    this.lendingService.addLending(newLending).subscribe(
      () => this.reloadLendings(),
      error => console.error('Error: ' + error),
      () => console.log('addNewLending() completed!')
    );
  }

  //
  public findLending(id: string) {
    let filter: ClientSearchParams = {
      id: id
    };

    this.lendingService.findLending(filter).subscribe(
      lending => {
        this.lending = lending;
        this.oldLendingForm = this.fb.group({
          id: lending[0].id,
          lendingName: lending[0].lendingName,
          lendingSurname: lending[0].lendingSurname,
          lendingICO: lending[0].lendingICO,
          lendingEmail: lending[0].lendingEmail,
          lendingPhone: lending[0].lendingPhone,
          lendingDateOfRegistraion: lending[0].lendingDateOfRegistraion
        });
        this.showOldLendingForm = true;
      },
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }

  //
  public editOldLending() {
    console.log('Editing old lending.');
    this.oldLending = this.oldLendingForm.value;
     console.log(this.oldLending);
    this.lendingService.editLending(this.oldLending).subscribe(
      () => this.reloadLendingsOverOldLendingForm(),
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }

  //
  public reloadLendingsOverOldLendingForm() {}
}
