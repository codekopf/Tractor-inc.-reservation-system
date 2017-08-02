import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule, JsonpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from '@angular/material';

import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { TractorListComponent } from './tractor-list/tractor-list.component';
// Custom components
import { ClientListComponent } from './client-list/client-list.component';
import { LendingListComponent } from './lending-list/lending-list.component';
import { NewLendingListComponent } from './new-lending/new-lending.component';

import { ApiService } from './shared';
import { routing } from './app.routing';

import { removeNgStyles, createNewHosts } from '@angularclass/hmr';
import { TractorService } from './shared/tractor.service';
import { ClientService } from './shared/client.service';
import { LendingService } from './shared/lending.service';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    JsonpModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    routing
  ],
  declarations: [
    AppComponent,
    TractorListComponent,
    AboutComponent,
    // INSERT CUSTOM COMPONENTS HERE
    ClientListComponent,
    LendingListComponent,
    NewLendingListComponent
  ],
  providers: [
    ApiService,
    TractorService,
    ClientService,
    LendingService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(public appRef: ApplicationRef) {}
  hmrOnInit(store) {
    console.log('HMR store', store);
  }
  hmrOnDestroy(store) {
    let cmpLocation = this.appRef.components.map(cmp => cmp.location.nativeElement);
    // recreate elements
    store.disposeOldHosts = createNewHosts(cmpLocation);
    // remove styles
    removeNgStyles();
  }
  hmrAfterDestroy(store) {
    // display new elements
    store.disposeOldHosts();
    delete store.disposeOldHosts;
  }
}
