import { Component, OnInit } from '@angular/core';
import { Meet } from '../../models/meet';
import { MeetProvider } from '../../providers/meet-provider/meet-provider';

/**
 * Generated class for the MeetsComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'meets',
  templateUrl: 'meets.html'
})
export class MeetsComponent implements OnInit {

  meets: Meet[];

  constructor(private meetProvider: MeetProvider) { }

  ngOnInit() {
    this.getMeets();
  }

  private getMeets() {
    this.meetProvider.getMeets().subscribe(meets => this.meets = meets);
  }
}
