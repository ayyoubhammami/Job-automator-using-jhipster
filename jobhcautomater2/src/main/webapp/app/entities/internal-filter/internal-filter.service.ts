import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInternalFilter } from 'app/shared/model/internal-filter.model';

type EntityResponseType = HttpResponse<IInternalFilter>;
type EntityArrayResponseType = HttpResponse<IInternalFilter[]>;

@Injectable({ providedIn: 'root' })
export class InternalFilterService {
    private resourceUrl = SERVER_API_URL + 'api/internal-filters';

    constructor(private http: HttpClient) {}

    create(internalFilter: IInternalFilter): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(internalFilter);
        return this.http
            .post<IInternalFilter>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(internalFilter: IInternalFilter): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(internalFilter);
        return this.http
            .put<IInternalFilter>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInternalFilter>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInternalFilter[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(internalFilter: IInternalFilter): IInternalFilter {
        const copy: IInternalFilter = Object.assign({}, internalFilter, {
            datePosted:
                internalFilter.datePosted != null && internalFilter.datePosted.isValid()
                    ? internalFilter.datePosted.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.datePosted = res.body.datePosted != null ? moment(res.body.datePosted) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((internalFilter: IInternalFilter) => {
            internalFilter.datePosted = internalFilter.datePosted != null ? moment(internalFilter.datePosted) : null;
        });
        return res;
    }
}
