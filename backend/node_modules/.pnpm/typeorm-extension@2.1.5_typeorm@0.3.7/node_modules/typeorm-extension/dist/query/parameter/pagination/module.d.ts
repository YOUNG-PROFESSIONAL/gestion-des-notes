import { SelectQueryBuilder } from 'typeorm';
import { PaginationApplyOptions, PaginationApplyOutput } from './type';
/**
 * Apply parsed page/pagination parameter data on the db query.
 *
 * @param query
 * @param data
 */
export declare function applyQueryPaginationParseOutput<T>(query: SelectQueryBuilder<T>, data: PaginationApplyOutput): import("@trapi/query").PaginationParseOutput;
/**
 * Apply raw page/pagination parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyQueryPagination<T>(query: SelectQueryBuilder<T>, data: unknown, options?: PaginationApplyOptions): PaginationApplyOutput;
/**
 * Apply raw page/pagination parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyPagination<T>(query: SelectQueryBuilder<T>, data: unknown, options?: PaginationApplyOptions): PaginationApplyOutput;
