

import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule, JsonpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from '@angular/material';

import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { TractorListComponent } from './tractor-list/tractor-list.component';
// INSERT COMPONENTS HERE
// import { ClientListComponent } from './clients/client-list.component';
// import { LendingListComponent } from './lendings/lending-list.component';
// import { RepairListComponent } from './repairs/repair-list.component';
// import { STKListComponent } from './stks/stk-list.component';

import { ApiService } from './shared';
import { routing } from './app.routing';

import { removeNgStyles, createNewHosts } from '@angularclass/hmr';
import { TractorService } from './shared/tractor.service';
// import { RepairService } from './shared/tractor.service';
// import { STKService } from './shared/tractor.service';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    JsonpModule,
    FormsModule,
    MaterialModule
    ReactiveFormsModule,
    routing
  ],
  declarations: [
    AppComponent,
    TractorListComponent,
    AboutComponent,
    // INSERT COMPONENTS HERE
    // ClieantListComponent,
    // LendingListComponent,
    // RepairsListComponent,
    // STKListComponent
  ],
  providers: [
    ApiService,
    TractorService,
    // ClieantService,
    // LendingService,
    // RepairService,
    // STKService
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
