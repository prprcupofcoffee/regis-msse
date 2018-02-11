import { NgModule } from '@angular/core';
import { MeetListComponent } from './meet-list/meet-list';
import { MeetListItemComponent } from './meet-list-item/meet-list-item';
@NgModule({
	declarations: [MeetListComponent,
    MeetListItemComponent],
	imports: [],
	exports: [MeetListComponent,
    MeetListItemComponent]
})
export class ComponentsModule {}
