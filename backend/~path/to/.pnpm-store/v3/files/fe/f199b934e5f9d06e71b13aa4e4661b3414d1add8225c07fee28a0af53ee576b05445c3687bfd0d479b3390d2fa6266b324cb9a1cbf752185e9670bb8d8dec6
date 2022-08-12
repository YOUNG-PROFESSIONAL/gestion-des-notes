import { FiltersParseOutput } from '@trapi/query';
import { SelectQueryBuilder } from 'typeorm';
import { FiltersApplyOptions, FiltersApplyOutput, FiltersTransformOptions, FiltersTransformOutput } from './type';
export declare function transformParsedFilters(data: FiltersParseOutput, options?: FiltersTransformOptions): FiltersTransformOutput;
/**
 * Apply transformed filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 */
export declare function applyFiltersTransformed<T>(query: SelectQueryBuilder<T>, data: FiltersTransformOutput): FiltersTransformOutput;
/**
 * Apply parsed filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyQueryFiltersParseOutput<T>(query: SelectQueryBuilder<T>, data: FiltersParseOutput, options?: FiltersTransformOptions): FiltersApplyOutput;
/**
 * Apply raw filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyQueryFilters(query: SelectQueryBuilder<any> | undefined, data: unknown, options?: FiltersApplyOptions): FiltersApplyOutput;
/**
 * Apply raw filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
export declare function applyFilters(query: SelectQueryBuilder<any> | undefined, data: unknown, options?: FiltersApplyOptions): FiltersApplyOutput;
