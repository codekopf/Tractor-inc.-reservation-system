import { RouterModule, Routes } from '@angular/router';

// Records
import { TractorListComponent } from './tractor-list/tractor-list.component';
import { ClientListComponent } from './client-list/client-list.component';
// import { LendingListComponent } from './lendings/lending-list.component';
// import { STKListComponent } from './stk/stks-list.component';
// import { RepairListComponent } from './repairs/repairs-list.component';
// Static pages
import { AboutComponent } from './about/about.component';

const routes: Routes = [
  { path: '', component: TractorListComponent },
  { path: 'clients', component: ClientListComponent },
  // { path: '/lendings', component: LendingListComponent },
  // { path: '/stks', component: STKListComponent },
  // { path: '/repairs', component: RepairListComponent },
  { path: 'about', component: AboutComponent}
];

export const routing = RouterModule.forRoot(routes);
