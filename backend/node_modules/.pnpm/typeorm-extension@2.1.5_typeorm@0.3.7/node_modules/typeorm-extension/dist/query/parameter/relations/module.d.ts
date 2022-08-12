import { RelationsParseOutput } from '@trapi/query';
import { SelectQueryBuilder } from 'typeorm';
import { RelationsApplyOptions, RelationsApplyOutput } from './type';
/**
 * Apply parsed include/relation parameter data on the db query.
 *
 * @param query
 * @param data
 */
export declare function applyQueryRelationsParseOutput<T>(query: SelectQueryBuilder<T>, data: RelationsParseOutput): RelationsApplyOutput;
/**
 * Apply raw include/relations parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyQueryRelations<T>(query: SelectQueryBuilder<T>, data: unknown, options?: RelationsApplyOptions): RelationsApplyOutput;
/**
 * Apply raw include/relations parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyRelations<T>(query: SelectQueryBuilder<T>, data: unknown, options?: RelationsApplyOptions): RelationsApplyOutput;
