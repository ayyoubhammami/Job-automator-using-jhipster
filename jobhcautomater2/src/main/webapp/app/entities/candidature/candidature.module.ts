import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    CandidatureComponent,
    CandidatureDetailComponent,
    CandidatureUpdateComponent,
    CandidatureDeletePopupComponent,
    CandidatureDeleteDialogComponent,
    candidatureRoute,
    candidaturePopupRoute
} from './';

const ENTITY_STATES = [...candidatureRoute, ...candidaturePopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CandidatureComponent,
        CandidatureDetailComponent,
        CandidatureUpdateComponent,
        CandidatureDeleteDialogComponent,
        CandidatureDeletePopupComponent
    ],
    entryComponents: [CandidatureComponent, CandidatureUpdateComponent, CandidatureDeleteDialogComponent, CandidatureDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2CandidatureModule {}
