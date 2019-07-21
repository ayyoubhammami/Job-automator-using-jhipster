import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    ProfilCondidateComponent,
    ProfilCondidateDetailComponent,
    ProfilCondidateUpdateComponent,
    ProfilCondidateDeletePopupComponent,
    ProfilCondidateDeleteDialogComponent,
    profilCondidateRoute,
    profilCondidatePopupRoute
} from './';

const ENTITY_STATES = [...profilCondidateRoute, ...profilCondidatePopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfilCondidateComponent,
        ProfilCondidateDetailComponent,
        ProfilCondidateUpdateComponent,
        ProfilCondidateDeleteDialogComponent,
        ProfilCondidateDeletePopupComponent
    ],
    entryComponents: [
        ProfilCondidateComponent,
        ProfilCondidateUpdateComponent,
        ProfilCondidateDeleteDialogComponent,
        ProfilCondidateDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2ProfilCondidateModule {}
