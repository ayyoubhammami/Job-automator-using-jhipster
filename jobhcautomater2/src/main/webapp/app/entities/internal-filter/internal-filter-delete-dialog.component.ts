import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInternalFilter } from 'app/shared/model/internal-filter.model';
import { InternalFilterService } from './internal-filter.service';

@Component({
    selector: 'jhi-internal-filter-delete-dialog',
    templateUrl: './internal-filter-delete-dialog.component.html'
})
export class InternalFilterDeleteDialogComponent {
    internalFilter: IInternalFilter;

    constructor(
        private internalFilterService: InternalFilterService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.internalFilterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'internalFilterListModification',
                content: 'Deleted an internalFilter'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-internal-filter-delete-popup',
    template: ''
})
export class InternalFilterDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ internalFilter }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InternalFilterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.internalFilter = internalFilter;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
