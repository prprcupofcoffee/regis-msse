import { Component, Input } from '@angular/core';
import { Meet } from '../../models/meet';

/**
 * Generated class for the MeetDetailComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'meet-detail',
  templateUrl: 'meet-detail.html'
})
export class MeetDetailComponent {

  @Input() meet: Meet;
  text: string;

  constructor() {
    console.log('Hello MeetDetailComponent Component');
    this.text = 'Hello World';
  }

}
