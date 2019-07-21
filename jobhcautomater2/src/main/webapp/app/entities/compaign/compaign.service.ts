import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICompaign } from 'app/shared/model/compaign.model';

type EntityResponseType = HttpResponse<ICompaign>;
type EntityArrayResponseType = HttpResponse<ICompaign[]>;

@Injectable({ providedIn: 'root' })
export class CompaignService {
    private resourceUrl = SERVER_API_URL + 'api/compaigns';

    constructor(private http: HttpClient) {}

    create(compaign: ICompaign): Observable<EntityResponseType> {
        return this.http.post<ICompaign>(this.resourceUrl, compaign, { observe: 'response' });
    }

    update(compaign: ICompaign): Observable<EntityResponseType> {
        return this.http.put<ICompaign>(this.resourceUrl, compaign, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICompaign>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICompaign[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    publish(compaign: ICompaign):  Observable<EntityResponseType> {
        return this.http.post<ICompaign>(this.resourceUrl+'/publish', compaign, { observe: 'response' });
    }

  

}
