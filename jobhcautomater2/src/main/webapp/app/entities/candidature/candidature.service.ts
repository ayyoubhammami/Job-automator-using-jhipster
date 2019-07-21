import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICandidature } from 'app/shared/model/candidature.model';

type EntityResponseType = HttpResponse<ICandidature>;
type EntityArrayResponseType = HttpResponse<ICandidature[]>;

@Injectable({ providedIn: 'root' })
export class CandidatureService {
    private resourceUrl = SERVER_API_URL + 'api/candidatures';

    constructor(private http: HttpClient) {}

    create(candidature: ICandidature): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(candidature);
        return this.http
            .post<ICandidature>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(candidature: ICandidature): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(candidature);
        return this.http
            .put<ICandidature>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICandidature>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICandidature[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(candidature: ICandidature): ICandidature {
        const copy: ICandidature = Object.assign({}, candidature, {
            date: candidature.date != null && candidature.date.isValid() ? candidature.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((candidature: ICandidature) => {
            candidature.date = candidature.date != null ? moment(candidature.date) : null;
        });
        return res;
    }
}
