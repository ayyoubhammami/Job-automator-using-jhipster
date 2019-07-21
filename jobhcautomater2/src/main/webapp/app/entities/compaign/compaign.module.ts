import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    CompaignComponent,
    CompaignDetailComponent,
    CompaignUpdateComponent,
    CompaignDeletePopupComponent,
    CompaignDeleteDialogComponent,
    compaignRoute,
    compaignPopupRoute
} from './';

const ENTITY_STATES = [...compaignRoute, ...compaignPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CompaignComponent,
        CompaignDetailComponent,
        CompaignUpdateComponent,
        CompaignDeleteDialogComponent,
        CompaignDeletePopupComponent
    ],
    entryComponents: [CompaignComponent, CompaignUpdateComponent, CompaignDeleteDialogComponent, CompaignDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2CompaignModule {}
