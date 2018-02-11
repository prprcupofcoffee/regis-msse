import { Component, Input } from '@angular/core';
import { Meet } from '../../models/meet';

/**
 * Generated class for the MeetListItemComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'meet-list-item',
  templateUrl: 'meet-list-item.html'
})
export class MeetListItemComponent {

  @Input() meet: Meet;

  constructor() {

  }
}
