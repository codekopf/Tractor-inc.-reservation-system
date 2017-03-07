/**
 * Created by rostapetr on 27.02.17.
 * @edit - Enhanced by Andrej Buday
 */
import { Injectable } from '@angular/core';
import { Http, URLSearchParams, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class LendingService {
  constructor(private http: Http) {}

  //
  // Returns all info of single lending
  //
  getLending(id: number) {
    return this.http.get(`http://localhost:8095/car-evidence-js/lending/${id}`).map(res => res.json());
  }

  //
  // Returns all info of all lendings
  //
  getLendings() {
    return this.http.get(`http://localhost:8095/car-evidence-js/lendings`).map(res => res.json());
  }

  // Returns all lendings
  findLendings(params: LendingSearchParams) {
    let parameters = new URLSearchParams();
    parameters.set('dateFrom', params.dateFrom);
    parameters.set('dateTo', params.dateTo);

    return this.http.get('http://localhost:8095/car-evidence-js/lendings/find', {search: parameters}).map(res => res.json());
  }

  // Create new lending
  addLending(lending: any) {
    let headers = new Headers({ 'Content-Type': 'application/json' , 'Accept': 'application/json', });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(
      'http://localhost:8095/car-evidence-js/lendings/new',
      JSON.stringify(lending),
      options);
  }


    // Edit old client
  editLending(lending: any) {
    let headers = new Headers({ 'Content-Type': 'application/json' , 'Accept': 'application/json', });
    let options = new RequestOptions({ headers: headers });

    // TODO OOOOO
    console.log("ZOBRAZI SA TOTO?");
    console.log(lending);
    return this.http.post(
      'http://localhost:8095/car-evidence-js/clients/update',
      JSON.stringify(lending),
      options);
  }

 findLending(filter: any) {
   let headers = new Headers({ 'Content-Type': 'application/json' , 'Accept': 'application/json', });
    let options = new RequestOptions({ headers: headers });
    console.log(filter);
     return this.http.post(
      'http://localhost:8095/car-evidence-js/clients/update',
      JSON.stringify(filter),
      options);
 }



  // Updated selected lending
  // TODO
  // updateCar(lending: any) {
  // }

  // Delete selected lending
  // TODO
  // deleteCar(id: number) {
  // }


}
