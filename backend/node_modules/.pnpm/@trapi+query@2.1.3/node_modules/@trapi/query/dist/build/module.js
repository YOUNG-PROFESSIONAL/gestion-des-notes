"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildQuery = void 0;
const parameter_1 = require("../parameter");
const constants_1 = require("../constants");
const utils_1 = require("../utils");
function buildQuery(input, options) {
    if (typeof input === 'undefined' ||
        input === null) {
        return '';
    }
    const query = {};
    if (typeof input[constants_1.Parameter.FIELDS] !== 'undefined' ||
        typeof input[constants_1.URLParameter.FIELDS] !== 'undefined') {
        query[constants_1.URLParameter.FIELDS] = (0, parameter_1.buildQueryFieldsForMany)([
            ...(input[constants_1.Parameter.FIELDS] ? [input[constants_1.Parameter.FIELDS]] : []),
            ...(input[constants_1.URLParameter.FIELDS] ? [input[constants_1.URLParameter.FIELDS]] : []),
        ]);
    }
    if (typeof input[constants_1.Parameter.FILTERS] !== 'undefined' ||
        typeof input[constants_1.URLParameter.FILTERS] !== 'undefined') {
        query[constants_1.URLParameter.FILTERS] = (0, parameter_1.buildQueryFiltersForMany)([
            ...(input[constants_1.Parameter.FILTERS] ? [input[constants_1.Parameter.FILTERS]] : []),
            ...(input[constants_1.URLParameter.FILTERS] ? [input[constants_1.URLParameter.FILTERS]] : []),
        ]);
    }
    if (typeof input[constants_1.Parameter.PAGINATION] !== 'undefined' ||
        typeof input[constants_1.URLParameter.PAGINATION] !== 'undefined') {
        query[constants_1.URLParameter.PAGINATION] = (0, parameter_1.buildQueryPaginationForMany)([
            ...(input[constants_1.Parameter.PAGINATION] ? [input[constants_1.Parameter.PAGINATION]] : []),
            ...(input[constants_1.URLParameter.PAGINATION] ? [input[constants_1.URLParameter.PAGINATION]] : []),
        ]);
    }
    if (typeof input[constants_1.Parameter.RELATIONS] !== 'undefined' ||
        typeof input[constants_1.URLParameter.RELATIONS] !== 'undefined') {
        query[constants_1.URLParameter.RELATIONS] = (0, parameter_1.buildQueryRelationsForMany)([
            ...(input[constants_1.Parameter.RELATIONS] ? [input[constants_1.Parameter.RELATIONS]] : []),
            ...(input[constants_1.URLParameter.RELATIONS] ? [input[constants_1.URLParameter.RELATIONS]] : []),
        ]);
    }
    if (typeof input[constants_1.Parameter.SORT] !== 'undefined' ||
        typeof input[constants_1.URLParameter.SORT] !== 'undefined') {
        query[constants_1.URLParameter.SORT] = (0, parameter_1.buildQuerySortForMany)([
            ...(input[constants_1.Parameter.SORT] ? [input[constants_1.Parameter.SORT]] : []),
            ...(input[constants_1.URLParameter.SORT] ? [input[constants_1.URLParameter.SORT]] : []),
        ]);
    }
    return (0, utils_1.buildURLQueryString)(query);
}
exports.buildQuery = buildQuery;
//# sourceMappingURL=module.js.map