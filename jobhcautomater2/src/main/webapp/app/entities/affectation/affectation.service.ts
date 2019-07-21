import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAffectation } from 'app/shared/model/affectation.model';

type EntityResponseType = HttpResponse<IAffectation>;
type EntityArrayResponseType = HttpResponse<IAffectation[]>;

@Injectable({ providedIn: 'root' })
export class AffectationService {
    private resourceUrl = SERVER_API_URL + 'api/affectations';

    constructor(private http: HttpClient) {}

    create(affectation: IAffectation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(affectation);
        return this.http
            .post<IAffectation>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(affectation: IAffectation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(affectation);
        return this.http
            .put<IAffectation>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAffectation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAffectation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(affectation: IAffectation): IAffectation {
        const copy: IAffectation = Object.assign({}, affectation, {
            date: affectation.date != null && affectation.date.isValid() ? affectation.date.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((affectation: IAffectation) => {
            affectation.date = affectation.date != null ? moment(affectation.date) : null;
        });
        return res;
    }
}
