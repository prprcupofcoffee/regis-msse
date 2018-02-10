import { NgModule } from '@angular/core';
import { MeetsComponent } from './meets/meets';
import { MeetDetailComponent } from './meet-detail/meet-detail';
@NgModule({
	declarations: [MeetsComponent,
    MeetDetailComponent],
	imports: [],
	exports: [MeetsComponent,
    MeetDetailComponent]
})
export class ComponentsModule {}
