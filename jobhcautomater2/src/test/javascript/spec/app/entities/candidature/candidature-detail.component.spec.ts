/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { CandidatureDetailComponent } from 'app/entities/candidature/candidature-detail.component';
import { Candidature } from 'app/shared/model/candidature.model';

describe('Component Tests', () => {
    describe('Candidature Management Detail Component', () => {
        let comp: CandidatureDetailComponent;
        let fixture: ComponentFixture<CandidatureDetailComponent>;
        const route = ({ data: of({ candidature: new Candidature(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [CandidatureDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CandidatureDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CandidatureDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.candidature).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
