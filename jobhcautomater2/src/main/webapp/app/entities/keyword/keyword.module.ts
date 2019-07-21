import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    KeywordComponent,
    KeywordDetailComponent,
    KeywordUpdateComponent,
    KeywordDeletePopupComponent,
    KeywordDeleteDialogComponent,
    keywordRoute,
    keywordPopupRoute
} from './';

const ENTITY_STATES = [...keywordRoute, ...keywordPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KeywordComponent,
        KeywordDetailComponent,
        KeywordUpdateComponent,
        KeywordDeleteDialogComponent,
        KeywordDeletePopupComponent
    ],
    entryComponents: [KeywordComponent, KeywordUpdateComponent, KeywordDeleteDialogComponent, KeywordDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2KeywordModule {}
