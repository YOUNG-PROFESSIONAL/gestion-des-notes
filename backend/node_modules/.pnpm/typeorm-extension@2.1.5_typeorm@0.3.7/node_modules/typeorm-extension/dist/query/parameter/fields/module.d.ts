import { SelectQueryBuilder } from 'typeorm';
import { FieldsApplyOptions, FieldsApplyOutput } from './type';
/**
 * Apply parsed fields parameter data on the db query.
 *
 * @param query
 * @param data
 */
export declare function applyQueryFieldsParseOutput<T>(query: SelectQueryBuilder<T>, data: FieldsApplyOutput): import("@trapi/query").FieldsParseOutput;
/**
 * Apply raw fields parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyQueryFields<T>(query: SelectQueryBuilder<T>, data: unknown, options?: FieldsApplyOptions): FieldsApplyOutput;
/**
 * Apply raw fields parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyFields<T>(query: SelectQueryBuilder<T>, data: unknown, options?: FieldsApplyOptions): FieldsApplyOutput;
