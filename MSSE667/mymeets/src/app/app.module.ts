import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientInMemoryWebApiModule, InMemoryWebApiModule } from 'angular-in-memory-web-api';

import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { MeetsPage } from '../pages/meets/meets';

import { MyApp } from './app.component';
import { AboutPage } from '../pages/about/about';
import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { MeetDetailsPage } from '../pages/meet-details/meet-details';
import { TabsPage } from '../pages/tabs/tabs';
import { MeetProvider } from '../providers/meet-provider/meet-provider';
import { MeetListComponent } from '../components/meet-list/meet-list';
import { MeetListItemComponent } from '../components/meet-list-item/meet-list-item';
import { MeetsDataService } from '../in-memory-data-services/meets-data-service';

@NgModule({
  declarations: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    MeetDetailsPage,
    MeetListComponent,
    MeetListItemComponent,
    MeetsPage,
    TabsPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpClientModule,

    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    HttpClientInMemoryWebApiModule.forRoot(
      MeetsDataService, { dataEncapsulation: false }
    )
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    MeetDetailsPage,
    MeetsPage,
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    MeetProvider
  ]
})
export class AppModule {}
