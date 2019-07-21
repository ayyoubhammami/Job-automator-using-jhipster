/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { CompaignDeleteDialogComponent } from 'app/entities/compaign/compaign-delete-dialog.component';
import { CompaignService } from 'app/entities/compaign/compaign.service';

describe('Component Tests', () => {
    describe('Compaign Management Delete Component', () => {
        let comp: CompaignDeleteDialogComponent;
        let fixture: ComponentFixture<CompaignDeleteDialogComponent>;
        let service: CompaignService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [CompaignDeleteDialogComponent]
            })
                .overrideTemplate(CompaignDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CompaignDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CompaignService);
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
