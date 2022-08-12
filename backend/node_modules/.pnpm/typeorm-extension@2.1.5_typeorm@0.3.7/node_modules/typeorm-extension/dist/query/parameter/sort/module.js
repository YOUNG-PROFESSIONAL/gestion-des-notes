"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.applySort = exports.applyQuerySort = exports.applyQuerySortParseOutput = void 0;
const query_1 = require("@trapi/query");
// --------------------------------------------------
/**
 * Apply parsed sort parameter data on the db query.
 *
 * @param query
 * @param data
 */
function applyQuerySortParseOutput(query, data) {
    if (data.length === 0) {
        return data;
    }
    const sort = {};
    for (let i = 0; i < data.length; i++) {
        const prefix = data[i].alias ? `${data[i].alias}.` : '';
        const key = `${prefix}${data[i].key}`;
        sort[key] = data[i].value;
    }
    query.orderBy(sort);
    return data;
}
exports.applyQuerySortParseOutput = applyQuerySortParseOutput;
/**
 * Apply raw sort parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyQuerySort(query, data, options) {
    return applyQuerySortParseOutput(query, (0, query_1.parseQuerySort)(data, options));
}
exports.applyQuerySort = applyQuerySort;
/**
 * Apply raw sort parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applySort(query, data, options) {
    return applyQuerySortParseOutput(query, (0, query_1.parseQuerySort)(data, options));
}
exports.applySort = applySort;
//# sourceMappingURL=module.js.map