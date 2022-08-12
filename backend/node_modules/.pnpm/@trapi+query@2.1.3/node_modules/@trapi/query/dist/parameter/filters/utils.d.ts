import { FilterOperatorConfig, FilterOperatorLabelType } from './type';
export declare function determineFilterOperatorLabelsByValue(input: string): {
    operators: FilterOperatorLabelType[];
    value: string | string[];
};
export declare function isFilterOperatorConfig(data: unknown): data is FilterOperatorConfig<any>;
//# sourceMappingURL=utils.d.ts.map