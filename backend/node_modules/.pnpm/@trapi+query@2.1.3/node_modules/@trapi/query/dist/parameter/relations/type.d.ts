import { Parameter } from '../../constants';
import { Flatten, OnlyObject, ParseOptionsBase, ParseOutputElementBase } from '../type';
export declare type RelationsBuildInput<T extends Record<string, any>> = {
    [K in keyof T]?: T[K] extends OnlyObject<T[K]> ? RelationsBuildInput<Flatten<T[K]>> | boolean : never;
};
export declare type RelationsParseOptions = ParseOptionsBase<Parameter.RELATIONS> & {
    includeParents?: boolean | string[] | string;
};
export declare type RelationsParseOutputElement = ParseOutputElementBase<Parameter.RELATIONS, string>;
export declare type RelationsParseOutput = RelationsParseOutputElement[];
//# sourceMappingURL=type.d.ts.map