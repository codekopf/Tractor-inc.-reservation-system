import { RouterModule, Routes } from '@angular/router';

import { TractorListComponent } from './tractor-list/tractor-list.component';
import { AboutComponent } from './about/about.component';

const routes: Routes = [
  { path: '', component: TractorListComponent },
  { path: 'about', component: AboutComponent}
];

export const routing = RouterModule.forRoot(routes);
