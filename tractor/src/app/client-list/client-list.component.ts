import { Component, OnInit } from '@angular/core';
import { ClientService } from '../shared/client.service';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'my-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss'],
  providers: [ClientService]
})
export class ClientListComponent implements OnInit {

  public clientList: any;
  public filterForm: FormGroup;
  public newClientForm: FormGroup;
  public showNewClientForm: boolean;
  public filtred: boolean;

  constructor(public clientService: ClientService, private fb: FormBuilder) {
    this.getClients();
  }

  ngOnInit() {
    console.log('hello client list');
    this.filterForm = this.fb.group({
      acquiredFrom: ['2001-08-21'],
      acquiredTo: ['2015-08-23']
    });


    this.newClientForm = this.fb.group({
      clientName: ['RECLAIMER'],
      clientSurname: ['AHTBB3QD001726541'],
      clientICO: ['1100000'],
      clientEmail: ['asd@asd.com'],
      clientPhone: ['00421905'],
    });

  }

  // public findClient(clientID: number){
  //   console.log('Something is going on ' + clientID + ' number');

  //   this.clientService.findClient(clientID).subscribe(
  //       clients => this.clientList = clients,
  //       error => console.error('Error: ' + error),
  //       () => console.log('Completed!')
  //     );
  // }

  public getClients() {
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

  public reloadClients() {
    this.getClients();
    this.showNewClientForm = false;
  }

  public createNewClient() {
    // NEVIEM AKO SA TO ROBI
    this.addNewClient(this.newClientForm.value);
  }

  public addNewClient(newClient: any) {
    console.log('Adding new client.');
    this.clientService.addClient(newClient).subscribe(
      () => this.reloadClients(),
      error => console.error('Error: ' + error),
      () => console.log('Completed!')
    );
  }
}
