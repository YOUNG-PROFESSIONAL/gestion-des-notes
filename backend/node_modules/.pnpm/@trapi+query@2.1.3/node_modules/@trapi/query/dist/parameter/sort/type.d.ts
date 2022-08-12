import { Flatten, KeyWithOptionalPrefix, OnlyObject, OnlyScalar, ParseOptionsBase, ParseOutputElementBase } from '../type';
import { Parameter } from '../../constants';
export declare enum SortDirection {
    ASC = "ASC",
    DESC = "DESC"
}
declare type SortWithOperator<T extends Record<string, any>> = KeyWithOptionalPrefix<keyof T, '-'> | KeyWithOptionalPrefix<keyof T, '-'>[];
export declare type SortBuildInput<T> = {
    [K in keyof T]?: T[K] extends OnlyScalar<T[K]> ? `${SortDirection}` : T[K] extends Date ? `${SortDirection}` : T[K] extends OnlyObject<T[K]> ? SortBuildInput<Flatten<T[K]>> | SortWithOperator<Flatten<T[K]>> : never;
} | SortWithOperator<T>;
export declare type SortParseOptions = ParseOptionsBase<Parameter.SORT, string[] | string[][]>;
export declare type SortParseOutputElement = ParseOutputElementBase<Parameter.SORT, SortDirection>;
export declare type SortParseOutput = SortParseOutputElement[];
export {};
//# sourceMappingURL=type.d.ts.map