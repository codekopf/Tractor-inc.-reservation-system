import { Component, OnInit } from '@angular/core';
import { ClientService } from '../shared/client.service';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'my-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss'],
  providers: [ClientService]
})
export class ClientListComponent implements OnInit {

  // Custom
  public clientInfo: any;
  public specificClientID: number;

  constructor(public clientService: ClientService, private fb: FormBuilder) {
    this.getClient();
  }

  ngOnInit() {
    console.log('hello client list');
    this.filterForm = this.fb.group({
      acquiredFrom: ['2001-08-21'],
      acquiredTo: ['2015-08-23']
    });


    this.clientInfo = this.fb.group({
      type: ['RECLAIMER'],
      vin: ['AHTBB3QD001726541'],
      testDatum: [1487812893],
      price: [1100000]
    });

  }

  public getClient() {
    this.filtred = false;
    this.clientList = this.clientService.getClients()
      .subscribe(
        clients => this.clientList = clients,
        error => console.error('Error: ' + error),
        () => console.log('Completed!')
      );
  }

  public filterClient() {
    let filter: SearchParams = {
      acquiredFrom: this.filterForm.value.acquiredFrom,
      acquiredTo: this.filterForm.value.acquiredTo
    };

    this.filtred = true;
    this.clientService.findClients(filter).subscribe(
      clients => this.clientList = clients,
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }





}
