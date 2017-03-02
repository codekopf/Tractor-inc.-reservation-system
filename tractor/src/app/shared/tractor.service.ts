/**
 * Created by rostapetr on 27.02.17.
 */
import { Injectable } from '@angular/core';
import {Http, URLSearchParams, Headers, RequestOptions} from '@angular/http';

@Injectable()
export class TractorService {
  constructor(private http: Http) {

  }


  getCars() {
    return this.http.get('http://localhost:8081/car-evidence-js/cars').map(res => res.json());
  }

  findCars(params: SearchParams) {
    let parameters = new URLSearchParams();
    parameters.set('acquiredFrom', params.acquiredFrom);
    parameters.set('acquiredTo', params.acquiredTo);

    return this.http.get('http://localhost:8081/car-evidence-js/cars/find', {search: parameters}).map(res => res.json());
  }


  addCar(car: any) {
    let headers = new Headers({ 'Content-Type': 'application/json' , 'Accept': 'application/json',});
    let options = new RequestOptions({ headers: headers });

    return this.http.post(
      'http://localhost:8081/car-evidence-js/cars/new',
      JSON.stringify(car),
      options);
  }

  // Methods using the http object
}
