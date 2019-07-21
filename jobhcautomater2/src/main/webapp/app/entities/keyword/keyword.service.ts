import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKeyword } from 'app/shared/model/keyword.model';

type EntityResponseType = HttpResponse<IKeyword>;
type EntityArrayResponseType = HttpResponse<IKeyword[]>;

@Injectable({ providedIn: 'root' })
export class KeywordService {
    private resourceUrl = SERVER_API_URL + 'api/keywords';

    constructor(private http: HttpClient) {}

    create(keyword: IKeyword): Observable<EntityResponseType> {
        return this.http.post<IKeyword>(this.resourceUrl, keyword, { observe: 'response' });
    }

    update(keyword: IKeyword): Observable<EntityResponseType> {
        return this.http.put<IKeyword>(this.resourceUrl, keyword, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKeyword>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKeyword[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
