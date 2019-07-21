/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { AffectationDeleteDialogComponent } from 'app/entities/affectation/affectation-delete-dialog.component';
import { AffectationService } from 'app/entities/affectation/affectation.service';

describe('Component Tests', () => {
    describe('Affectation Management Delete Component', () => {
        let comp: AffectationDeleteDialogComponent;
        let fixture: ComponentFixture<AffectationDeleteDialogComponent>;
        let service: AffectationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [AffectationDeleteDialogComponent]
            })
                .overrideTemplate(AffectationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AffectationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AffectationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
