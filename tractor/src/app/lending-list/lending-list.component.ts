import { Component, OnInit } from '@angular/core';
import { LendingService } from '../shared/lending.service';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'my-lenging-list',
  templateUrl: './lending-list.component.html',
  styleUrls: ['./lending-list.component.scss'],
  providers: [LendingService]
})
export class LendingListComponent implements OnInit {

  public lendingList: any;
  public filterForm: FormGroup;
  public newLendingForm: FormGroup;
  public showNewLendingForm: boolean;
  public filtred: boolean;

  // CUSTOM PROPERTIES
  public showOldLendingForm: boolean;
  public oldLendingForm: FormGroup;
  public oldLending: any;
  public lending: any;

  constructor(public lendingService: LendingService, private fb: FormBuilder) {
    // this.getLendings();
  }

  ngOnInit() {
    console.log('hello client list');
    this.filterForm = this.fb.group({
      dateFrom: ['2010-10-10'],
      dateTo: ['2018-10-10']
    });


    this.newLendingForm = this.fb.group({
      // lendingName: ['RECLAIMER'],
      dateFrom: ['2012-10-10'],
      dateTo: ['2012-12-12'],
      lattitude: ['3'],
      longitude: ['longitude']
    });

    this.showOldLendingForm = false;
  }

  public getLendings() {
    this.filtred = false;
    this.lendingList = this.lendingService.getLendings()
      .subscribe(
        lendings => this.lendingList = lendings,
        error => console.error('Error: ' + error),
        () => console.log('Completed!')
      );
  }

  public filterLending() {
    let filter: LendingSearchParams = {
      dateFrom: this.filterForm.value.dateFrom,
      dateTo: this.filterForm.value.dateTo
    };

    console.log("asd");

    this.filtred = true;
    this.lendingService.findLendings(filter).subscribe(
      lendings => this.lendingList = lendings,
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }

  public reloadLendings() {
    this.getLendings();
    this.showNewLendingForm = false;
  }

    public reloadLendingOverOldLendingForm() {
    this.getLendings();
    this.showOldLendingForm = false;
  }

  public createNewClient() {
    // NEVIEM AKO SA TO ROBI
    this.addNewLending(this.newLendingForm.value);
  }

  public addNewLending(newLending: any) {
    console.log('Adding new client.');
    this.lendingService.addLending(newLending).subscribe(
      () => this.reloadLendings(),
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }


  // CUSTOM FUNCTION
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

}
