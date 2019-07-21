/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { ProfilCondidateDetailComponent } from 'app/entities/profil-condidate/profil-condidate-detail.component';
import { ProfilCondidate } from 'app/shared/model/profil-condidate.model';

describe('Component Tests', () => {
    describe('ProfilCondidate Management Detail Component', () => {
        let comp: ProfilCondidateDetailComponent;
        let fixture: ComponentFixture<ProfilCondidateDetailComponent>;
        const route = ({ data: of({ profilCondidate: new ProfilCondidate(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [ProfilCondidateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProfilCondidateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilCondidateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.profilCondidate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
