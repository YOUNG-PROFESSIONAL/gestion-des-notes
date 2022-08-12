import { SortParseOutput } from '@trapi/query';
import { SelectQueryBuilder } from 'typeorm';
import { SortApplyOptions, SortApplyOutput } from './type';
/**
 * Apply parsed sort parameter data on the db query.
 *
 * @param query
 * @param data
 */
export declare function applyQuerySortParseOutput<T>(query: SelectQueryBuilder<T>, data: SortParseOutput): SortApplyOutput;
/**
 * Apply raw sort parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyQuerySort<T>(query: SelectQueryBuilder<T>, data: unknown, options?: SortApplyOptions): SortParseOutput;
/**
 * Apply raw sort parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applySort<T>(query: SelectQueryBuilder<T>, data: unknown, options?: SortApplyOptions): SortParseOutput;
