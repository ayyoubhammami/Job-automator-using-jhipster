/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { KeywordDetailComponent } from 'app/entities/keyword/keyword-detail.component';
import { Keyword } from 'app/shared/model/keyword.model';

describe('Component Tests', () => {
    describe('Keyword Management Detail Component', () => {
        let comp: KeywordDetailComponent;
        let fixture: ComponentFixture<KeywordDetailComponent>;
        const route = ({ data: of({ keyword: new Keyword(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [KeywordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KeywordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KeywordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.keyword).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
