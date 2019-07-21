import { IInternalFilter } from 'app/shared/model//internal-filter.model';

export const enum ListOfCriterea {
    Title = 'Title',
    Description = 'Description',
    DatePosted = 'DatePosted',
    Country = 'Country',
    RatingClient = 'RatingClient',
    PaiementMethod = 'PaiementMethod',
    JobType = 'JobType',
    ExperienceLevel = 'ExperienceLevel',
    ClientHiresNumber = 'ClientHiresNumber',
    ClientHistoryInfoIsPrevious = 'ClientHistoryInfoIsPrevious',
    NumberOfWantedHiring = 'NumberOfWantedHiring',
    NumberOfProposal = 'NumberOfProposal',
    CategoryProject = 'CategoryProject',
    Source = 'Source',
    LinkForDetails = 'LinkForDetails',
    StatutOfOffer = 'StatutOfOffer',
    MinBudget = 'MinBudget',
    MaxBudget = 'MaxBudget',
    MinHoursPerWeek = 'MinHoursPerWeek',
    MaxHoursPerWeek = 'MaxHoursPerWeek',
    MinProjectLenghtWithMonthUnit = 'MinProjectLenghtWithMonthUnit',
    MaxProjectLenghtWithMonthUnit = 'MaxProjectLenghtWithMonthUnit'
}

export interface IFilterCriterea {
    id?: number;
    name?: ListOfCriterea;
    value?: string;
    internalFilter?: IInternalFilter;
}

export class FilterCriterea implements IFilterCriterea {
    constructor(public id?: number, public name?: ListOfCriterea, public value?: string, public internalFilter?: IInternalFilter) {}
}
