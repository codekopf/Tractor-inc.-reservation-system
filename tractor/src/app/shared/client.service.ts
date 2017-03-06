/**
 * Created by Andrej Buday on 05. March 2017
 */
import { Injectable } from '@angular/core';
import { Http, URLSearchParams, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class ClientService {
  constructor(private http: Http) {}

  // Returns info about selected client
  // TODO
  // findClient(clientID: number) {
  //   console.log('asd');
  //   let id = clientID.toString();

  //   let parameters = new URLSearchParams();
  //   parameters.set('id', id);

  //   return this.http.get('http://localhost:8095/car-evidence-js/client/find', {search: parameters}).map(res => res.json());

  // }

  // Returns info about selected client
  getClients() {
    return this.http.get(`http://localhost:8095/car-evidence-js/clients`).map(res => res.json());
  }

  // Returns all clients
  findClients(params: SearchParams) {
    let parameters = new URLSearchParams();
    parameters.set('acquiredFrom', params.acquiredFrom);
    parameters.set('acquiredTo', params.acquiredTo);

    return this.http.get('http://localhost:8095/car-evidence-js/clients/find', {search: parameters}).map(res => res.json());
  }

  // Create new client
  addClient(client: any) {
    let headers = new Headers({ 'Content-Type': 'application/json' , 'Accept': 'application/json', });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(
      'http://localhost:8095/car-evidence-js/clients/new',
      JSON.stringify(client),
      options);
  }

  // Updated selected client
  // TODO
  // updateClient(client: any) {
  // }

  // Delete selected client
  // TODO
  // deleteClient(id: number) {
  // }
}
