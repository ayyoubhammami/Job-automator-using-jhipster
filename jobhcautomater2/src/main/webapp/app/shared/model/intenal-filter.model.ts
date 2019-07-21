export interface IIntenalFilter {
    id?: number;
}

export class IntenalFilter implements IIntenalFilter {
    constructor(public id?: number) {}
}
