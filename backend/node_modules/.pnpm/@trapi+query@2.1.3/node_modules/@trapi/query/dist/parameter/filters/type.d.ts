import { Parameter } from '../../constants';
import { Flatten, OnlyObject, OnlyScalar, ParseOptionsBase, ParseOutputElementBase } from '../type';
import { FilterOperator, FilterOperatorLabel } from './constants';
export declare type FilterOperatorConfig<V extends string | number | boolean | null | undefined> = {
    operator: `${FilterOperator}` | (`${FilterOperator}`)[];
    value: V | V[];
};
export declare type FilterOperatorLabelType = `${FilterOperatorLabel}`;
declare type FilterValue<V> = V extends string | number | boolean ? (V | V[]) : never;
declare type FilterValueWithOperator<V> = V extends string | number | boolean ? (FilterValue<V> | FilterValueOperator<V> | Array<FilterValueOperator<V>>) : never;
declare type FilterValueOperator<V extends string | number | boolean> = `!${V}` | `!~${V}` | `~${V}` | `<${V}` | `<=${V}` | `>${V}` | `>=${V}`;
export declare type FiltersBuildInput<T> = {
    [K in keyof T]?: T[K] extends OnlyScalar<T[K]> ? T[K] | FilterValueWithOperator<T[K]> | FilterOperatorConfig<T[K]> : T[K] extends OnlyObject<T[K]> ? FiltersBuildInput<Flatten<T[K]>> : never;
};
export declare type FiltersParseOptions = ParseOptionsBase<Parameter.FILTERS>;
export declare type FiltersParseOutputElement = ParseOutputElementBase<Parameter.FILTERS, FilterValue<string | number | boolean | null>> & {
    operator?: {
        [K in FilterOperatorLabel]?: boolean;
    };
};
export declare type FiltersParseOutput = FiltersParseOutputElement[];
export {};
//# sourceMappingURL=type.d.ts.map