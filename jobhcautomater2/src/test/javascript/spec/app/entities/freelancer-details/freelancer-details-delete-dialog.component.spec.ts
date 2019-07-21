/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { FreelancerDetailsDeleteDialogComponent } from 'app/entities/freelancer-details/freelancer-details-delete-dialog.component';
import { FreelancerDetailsService } from 'app/entities/freelancer-details/freelancer-details.service';

describe('Component Tests', () => {
    describe('FreelancerDetails Management Delete Component', () => {
        let comp: FreelancerDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<FreelancerDetailsDeleteDialogComponent>;
        let service: FreelancerDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [FreelancerDetailsDeleteDialogComponent]
            })
                .overrideTemplate(FreelancerDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FreelancerDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FreelancerDetailsService);
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
