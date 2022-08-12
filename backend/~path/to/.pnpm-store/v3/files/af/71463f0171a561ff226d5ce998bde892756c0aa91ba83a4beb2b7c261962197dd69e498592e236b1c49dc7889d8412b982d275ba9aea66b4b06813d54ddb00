import { Parameter } from '../../constants';
import { Flatten, KeyWithOptionalPrefix, OnlyObject, ParseOptionsBase, ParseOutputElementBase, ToOneAndMany } from '../type';
export declare const DEFAULT_ALIAS_ID = "__DEFAULT__";
export declare enum FieldOperator {
    INCLUDE = "+",
    EXCLUDE = "-"
}
declare type FieldWithOperator<T extends Record<string, any>> = KeyWithOptionalPrefix<keyof T, FieldOperator> | KeyWithOptionalPrefix<keyof T, FieldOperator>[];
export declare type FieldsBuildInput<T extends Record<string, any>> = {
    [K in keyof T]?: T[K] extends OnlyObject<T[K]> ? (FieldsBuildInput<Flatten<T[K]>> | FieldWithOperator<Flatten<T[K]>>) : never;
} | {
    [key: string]: ToOneAndMany<KeyWithOptionalPrefix<keyof T, FieldOperator>[]>;
} | FieldWithOperator<T>;
export declare type FieldsParseOptions = ParseOptionsBase<Parameter.FIELDS, Record<string, string[]> | string[]>;
export declare type FieldsParseOutputElement = ParseOutputElementBase<Parameter.FIELDS, FieldOperator>;
export declare type FieldsParseOutput = FieldsParseOutputElement[];
export {};
//# sourceMappingURL=type.d.ts.map