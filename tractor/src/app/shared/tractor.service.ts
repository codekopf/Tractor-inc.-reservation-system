/**
 * Created by rostapetr on 27.02.17.
 * @edit - Enhanced by Andrej Buday
 */
import { Injectable } from '@angular/core';
import { Http, URLSearchParams, Headers, RequestOptions } from '@angular/http';

@Injectable()
export class TractorService {
  constructor(private http: Http) {}

  // Returns info about selected car
  // TODO
  getCar(id: number) {
    return this.http.get(`http://localhost:8095/car-evidence-js/car/${id}`).map(res => res.json());
  }

  // Returns info about selected car
  getCars() {
    return this.http.get(`http://localhost:8095/car-evidence-js/cars`).map(res => res.json());
  }

  // Returns all cars
  findCars(params: SearchParams) {
    let parameters = new URLSearchParams();
    parameters.set('acquiredFrom', params.acquiredFrom);
    parameters.set('acquiredTo', params.acquiredTo);

    return this.http.get('http://localhost:8095/car-evidence-js/cars/find', {search: parameters}).map(res => res.json());
  }

  // Create new car
  addCar(car: any) {
    let headers = new Headers({ 'Content-Type': 'application/json' , 'Accept': 'application/json', });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(
      'http://localhost:8095/car-evidence-js/cars/new',
      JSON.stringify(car),
      options);
  }

  // Updated selected car
  // TODO
  // updateCar(car: any) {
  // }

  // Delete selected car
  // TODO
  // deleteCar(id: number) {
  // }


}
