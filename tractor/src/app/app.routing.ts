import { RouterModule, Routes } from '@angular/router';

// Records
import { TractorListComponent } from './tractor-list/tractor-list.component';
import { ClientListComponent } from './client-list/client-list.component';
import { LendingListComponent } from './lending-list/lending-list.component';
import { NewLendingListComponent } from './new-lending/new-lending.component';
// Static pages
import { AboutComponent } from './about/about.component';

const routes: Routes = [
  { path: '', component: TractorListComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'lendings', component: LendingListComponent },
  { path: 'newlendings', component: NewLendingListComponent },
  // Static pages
  { path: 'about', component: AboutComponent}
];

export const routing = RouterModule.forRoot(routes);
