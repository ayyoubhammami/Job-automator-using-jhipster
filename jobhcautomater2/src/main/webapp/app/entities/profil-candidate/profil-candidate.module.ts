import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    ProfilCandidateComponent,
    ProfilCandidateDetailComponent,
    ProfilCandidateUpdateComponent,
    ProfilCandidateDeletePopupComponent,
    ProfilCandidateDeleteDialogComponent,
    profilCandidateRoute,
    profilCandidatePopupRoute
} from './';

const ENTITY_STATES = [...profilCandidateRoute, ...profilCandidatePopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfilCandidateComponent,
        ProfilCandidateDetailComponent,
        ProfilCandidateUpdateComponent,
        ProfilCandidateDeleteDialogComponent,
        ProfilCandidateDeletePopupComponent
    ],
    entryComponents: [
        ProfilCandidateComponent,
        ProfilCandidateUpdateComponent,
        ProfilCandidateDeleteDialogComponent,
        ProfilCandidateDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2ProfilCandidateModule {}
