import { Component, OnInit } from '@angular/core';
import { Meet } from '../../models/meet';
import { MeetProvider } from '../../providers/meet-provider/meet-provider';

/**
 * Generated class for the MeetListComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'meet-list',
  templateUrl: 'meet-list.html'
})
export class MeetListComponent implements OnInit {

  meets: Meet[];

  constructor(private meetProvider: MeetProvider) { }

  ngOnInit() {
    this.getMeets();
  }

  private getMeets() {
    this.meetProvider.getMeets().subscribe(meets => this.meets = meets);
  }
}
