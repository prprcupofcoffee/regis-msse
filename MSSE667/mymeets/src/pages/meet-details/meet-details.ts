import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'page-meet-details',
  templateUrl: 'meet-details.html'
})
export class MeetDetailsPage {

  public id: number;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams) {
      this.id = this.navParams.get('meet').id;
  }
}
