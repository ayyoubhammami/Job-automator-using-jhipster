/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { CompaignDetailComponent } from 'app/entities/compaign/compaign-detail.component';
import { Compaign } from 'app/shared/model/compaign.model';

describe('Component Tests', () => {
    describe('Compaign Management Detail Component', () => {
        let comp: CompaignDetailComponent;
        let fixture: ComponentFixture<CompaignDetailComponent>;
        const route = ({ data: of({ compaign: new Compaign(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [CompaignDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CompaignDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CompaignDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.compaign).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
