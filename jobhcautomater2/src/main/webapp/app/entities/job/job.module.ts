import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import { Ng5SliderModule } from 'ng5-slider';

import {
    JobComponent,
    JobDetailComponent,
    JobUpdateComponent,
    JobDeletePopupComponent,
    JobDeleteDialogComponent,
    jobRoute,
    jobPopupRoute
} from './';

const ENTITY_STATES = [...jobRoute, ...jobPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES), Ng5SliderModule],
    declarations: [JobComponent, JobDetailComponent, JobUpdateComponent, JobDeleteDialogComponent, JobDeletePopupComponent],
    entryComponents: [JobComponent, JobUpdateComponent, JobDeleteDialogComponent, JobDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2JobModule {}
