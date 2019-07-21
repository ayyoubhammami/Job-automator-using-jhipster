import { IJob } from 'app/shared/model//job.model';
import { IInternalFilter } from 'app/shared/model//internal-filter.model';

export interface IJobMatchingFilters {
    id?: number;
    visited?: boolean;
    job?: IJob;
    internalFilter?: IInternalFilter;
}

export class JobMatchingFilters implements IJobMatchingFilters {
    constructor(public id?: number, public visited?: boolean, public job?: IJob, public internalFilter?: IInternalFilter) {
        this.visited = false;
    }
}
