import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import { JobHcAutomater2AdminModule } from 'app/admin/admin.module';
import {
    FreelancerDetailsComponent,
    FreelancerDetailsDetailComponent,
    FreelancerDetailsUpdateComponent,
    FreelancerDetailsDeletePopupComponent,
    FreelancerDetailsDeleteDialogComponent,
    freelancerDetailsRoute,
    freelancerDetailsPopupRoute
} from './';

const ENTITY_STATES = [...freelancerDetailsRoute, ...freelancerDetailsPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, JobHcAutomater2AdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FreelancerDetailsComponent,
        FreelancerDetailsDetailComponent,
        FreelancerDetailsUpdateComponent,
        FreelancerDetailsDeleteDialogComponent,
        FreelancerDetailsDeletePopupComponent
    ],
    entryComponents: [
        FreelancerDetailsComponent,
        FreelancerDetailsUpdateComponent,
        FreelancerDetailsDeleteDialogComponent,
        FreelancerDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2FreelancerDetailsModule {}
