/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { SocialMediaComponent } from 'app/entities/social-media/social-media.component';
import { SocialMediaService } from 'app/entities/social-media/social-media.service';
import { SocialMedia } from 'app/shared/model/social-media.model';

describe('Component Tests', () => {
    describe('SocialMedia Management Component', () => {
        let comp: SocialMediaComponent;
        let fixture: ComponentFixture<SocialMediaComponent>;
        let service: SocialMediaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [SocialMediaComponent],
                providers: []
            })
                .overrideTemplate(SocialMediaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SocialMediaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SocialMediaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SocialMedia(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.socialMedias[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
