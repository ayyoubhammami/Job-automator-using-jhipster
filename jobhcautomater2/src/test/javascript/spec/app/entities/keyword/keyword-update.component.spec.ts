/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { KeywordUpdateComponent } from 'app/entities/keyword/keyword-update.component';
import { KeywordService } from 'app/entities/keyword/keyword.service';
import { Keyword } from 'app/shared/model/keyword.model';

describe('Component Tests', () => {
    describe('Keyword Management Update Component', () => {
        let comp: KeywordUpdateComponent;
        let fixture: ComponentFixture<KeywordUpdateComponent>;
        let service: KeywordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [KeywordUpdateComponent]
            })
                .overrideTemplate(KeywordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KeywordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KeywordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Keyword(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.keyword = entity;
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
                    const entity = new Keyword();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.keyword = entity;
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
