import { FieldDetails } from './type';
export declare function isFieldAllowedByRelations<T extends {
    value: string;
}>(field: string | FieldDetails, includes?: T[], options?: {
    defaultAlias?: string;
}): boolean;
export declare function buildFieldWithAlias(field: string | FieldDetails, defaultAlias?: string): string;
//# sourceMappingURL=relation.d.ts.map