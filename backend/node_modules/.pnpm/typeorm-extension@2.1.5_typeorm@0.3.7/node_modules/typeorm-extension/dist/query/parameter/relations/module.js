"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.applyRelations = exports.applyQueryRelations = exports.applyQueryRelationsParseOutput = void 0;
const query_1 = require("@trapi/query");
/**
 * Apply parsed include/relation parameter data on the db query.
 *
 * @param query
 * @param data
 */
function applyQueryRelationsParseOutput(query, data) {
    for (let i = 0; i < data.length; i++) {
        /* istanbul ignore next */
        query.leftJoinAndSelect(data[i].key, data[i].value);
    }
    return data;
}
exports.applyQueryRelationsParseOutput = applyQueryRelationsParseOutput;
/**
 * Apply raw include/relations parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyQueryRelations(query, data, options) {
    return applyQueryRelationsParseOutput(query, (0, query_1.parseQueryRelations)(data, options));
}
exports.applyQueryRelations = applyQueryRelations;
/**
 * Apply raw include/relations parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyRelations(query, data, options) {
    return applyQueryRelations(query, data, options);
}
exports.applyRelations = applyRelations;
//# sourceMappingURL=module.js.map