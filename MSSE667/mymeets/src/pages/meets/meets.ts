import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-meets',
  templateUrl: 'meets.html'
})
export class MeetsPage {
  icons: string[];
  items: Array<{ name: string, note: string, icon: string }>;

  constructor(public navCtrl: NavController) {
    this.icons = ['flask', 'wifi', 'beer', 'football', 'basketball', 'paper-plane',
      'american-football', 'boat', 'bluetooth', 'build'];

    this.items = [];
    for (let i = 1; i < 11; i++) {
      this.items.push({
        name: 'Meet ' + i,
        note: 'This is meet #' + i,
        icon: this.icons[Math.floor(Math.random() * this.icons.length)]
      });
    }
  }
}
