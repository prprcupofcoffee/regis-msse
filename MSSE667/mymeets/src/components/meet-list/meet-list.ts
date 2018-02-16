import { Component, OnInit } from '@angular/core';

import { AlertController, NavController } from 'ionic-angular';

import { Meet } from '../../models/meet';
import { MeetDetailsPage } from '../../pages/meet-details/meet-details';
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
    private alertCtrl: AlertController,
    private navCtrl: NavController) { }

  ngOnInit() {
    this.getMeets();
  }

  itemClicked(selectedMeet: Meet): void {
    this.navCtrl.push(MeetDetailsPage, { 'meet': selectedMeet });
    // let alertTitle: string = selectedMeet.name;
    // let alertSubTitle: string = selectedMeet.location;
    // let alert = this.alertCtrl.create({
    //   title: alertTitle,
    //   subTitle: alertSubTitle,
    //   buttons: ['Close']
    // });
    // alert.present();
  }

  private getMeets() {
    this.meetProvider.getAll().subscribe(meets => this.meets = meets);
  }
}
