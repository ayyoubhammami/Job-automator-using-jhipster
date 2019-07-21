/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { ProfilCandidateDetailComponent } from 'app/entities/profil-candidate/profil-candidate-detail.component';
import { ProfilCandidate } from 'app/shared/model/profil-candidate.model';

describe('Component Tests', () => {
    describe('ProfilCandidate Management Detail Component', () => {
        let comp: ProfilCandidateDetailComponent;
        let fixture: ComponentFixture<ProfilCandidateDetailComponent>;
        const route = ({ data: of({ profilCandidate: new ProfilCandidate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [ProfilCandidateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProfilCandidateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilCandidateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.profilCandidate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
