/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { CompaignUpdateComponent } from 'app/entities/compaign/compaign-update.component';
import { CompaignService } from 'app/entities/compaign/compaign.service';
import { Compaign } from 'app/shared/model/compaign.model';

describe('Component Tests', () => {
    describe('Compaign Management Update Component', () => {
        let comp: CompaignUpdateComponent;
        let fixture: ComponentFixture<CompaignUpdateComponent>;
        let service: CompaignService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [CompaignUpdateComponent]
            })
                .overrideTemplate(CompaignUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CompaignUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CompaignService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Compaign(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.compaign = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Compaign();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.compaign = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
