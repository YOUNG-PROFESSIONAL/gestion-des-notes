import { Parameter } from '../constants';
export declare type Flatten<Type> = Type extends Array<infer Item> ? Item : Type;
export declare type OnlyScalar<T> = T extends string | number | boolean | undefined | null ? T : never;
export declare type OnlySingleObject<T> = T extends {
    [key: string]: any;
} ? T : never;
export declare type OnlyObject<T> = Flatten<T> extends OnlySingleObject<Flatten<T>> ? T | Flatten<T> : never;
export declare type ToOneAndMany<T> = T extends Array<infer Item> ? (Item | Item[]) : (T[] | T);
export declare type KeyWithOptionalPrefix<T, O extends string> = T extends string ? (`${O}${T}` | T) : never;
export declare type ParseOutputElementBase<K extends `${Parameter}`, V extends unknown | undefined = undefined> = (K extends `${Parameter.PAGINATION}` ? Record<string, any> : {
    key: string;
}) & (K extends `${Parameter.RELATIONS}` ? Record<string, any> : {
    alias?: string;
}) & (K extends `${Parameter.FIELDS}` ? {
    value?: V;
} : {
    value: V;
});
export declare type ParseOptionsBase<K extends `${Parameter}`, A = string[]> = (K extends `${Parameter.PAGINATION}` ? Record<string, any> : {
    aliasMapping?: Record<string, string>;
    allowed?: A;
    defaultAlias?: string;
}) & (K extends `${Parameter.PAGINATION}` | `${Parameter.RELATIONS}` ? Record<string, any> : {
    relations?: ParseOutputElementBase<Parameter.RELATIONS, string>[];
});
//# sourceMappingURL=type.d.ts.map