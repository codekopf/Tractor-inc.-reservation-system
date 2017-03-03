import { Component, OnInit } from '@angular/core';
import { RepairService } from '../shared/repair.service';
import { FormGroup, FormBuilder } from '@angular/forms';
@Component({
  selector: 'my-repair-list',
  templateUrl: './repair-list.component.html',
  styleUrls: ['./repair-list.component.scss'],
  providers: [RepairService]
})
export class RepairListComponent implements OnInit {

  public clientList: any;

}
