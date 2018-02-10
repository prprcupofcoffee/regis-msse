import { Component } from '@angular/core';

import { MeetListPage } from '../meet-list/meet-list';
import { ContactPage } from '../contact/contact';
import { HomePage } from '../home/home';  

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  tab2Root = MeetListPage;
  tab3Root = ContactPage;

  constructor() {

  }
}
