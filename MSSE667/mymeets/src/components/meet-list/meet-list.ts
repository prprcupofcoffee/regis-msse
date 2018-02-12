import { Component, OnInit } from '@angular/core';

import { AlertController } from 'ionic-angular';

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

  constructor(
    private meetProvider: MeetProvider,
    private alertCtrl: AlertController) { }

  ngOnInit() {
    this.getMeets();
  }

  itemClicked(selectedMeet: Meet): void {
    let alertTitle: string = selectedMeet.name;
    let alertSubTitle: string = selectedMeet.location;
    let alert = this.alertCtrl.create({
      title: alertTitle,
      subTitle: alertSubTitle,
      buttons: ['Close']
    });
    alert.present();
  }

  private getMeets() {
    this.meetProvider.getMeets().subscribe(meets => this.meets = meets);
  }
}
