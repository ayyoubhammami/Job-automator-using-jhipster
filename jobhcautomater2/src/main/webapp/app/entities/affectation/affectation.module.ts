import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';
import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    AffectationComponent,
    AffectationDetailComponent,
    AffectationUpdateComponent,
    AffectationDeletePopupComponent,
    AffectationDeleteDialogComponent,
    affectationRoute,
    affectationPopupRoute
} from './';

const ENTITY_STATES = [...affectationRoute, ...affectationPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES),NgSelectModule,FormsModule],

    declarations: [
        AffectationComponent,
        AffectationDetailComponent,
        AffectationUpdateComponent,
        AffectationDeleteDialogComponent,
        AffectationDeletePopupComponent
    ],
    entryComponents: [AffectationComponent, AffectationUpdateComponent, AffectationDeleteDialogComponent, AffectationDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2AffectationModule {}
