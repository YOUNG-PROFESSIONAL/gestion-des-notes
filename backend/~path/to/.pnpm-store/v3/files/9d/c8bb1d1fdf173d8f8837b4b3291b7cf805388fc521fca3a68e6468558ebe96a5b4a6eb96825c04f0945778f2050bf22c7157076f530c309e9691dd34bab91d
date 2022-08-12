"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.applyFields = exports.applyQueryFields = exports.applyQueryFieldsParseOutput = void 0;
const query_1 = require("@trapi/query");
/**
 * Apply parsed fields parameter data on the db query.
 *
 * @param query
 * @param data
 */
/* istanbul ignore next */
function applyQueryFieldsParseOutput(query, data) {
    if (data.length === 0) {
        return data;
    }
    for (let i = 0; i < data.length; i++) {
        const prefix = (data[i].alias ? `${data[i].alias}.` : '');
        const key = `${prefix}${data[i].key}`;
        switch (data[i].value) {
            case query_1.FieldOperator.INCLUDE:
                query.addSelect(key);
                break;
            case query_1.FieldOperator.EXCLUDE:
                // todo: not implemented yet :/
                break;
            default:
                query.select(key);
                break;
        }
    }
    return data;
}
exports.applyQueryFieldsParseOutput = applyQueryFieldsParseOutput;
/**
 * Apply raw fields parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyQueryFields(query, data, options) {
    return applyQueryFieldsParseOutput(query, (0, query_1.parseQueryFields)(data, options));
}
exports.applyQueryFields = applyQueryFields;
/**
 * Apply raw fields parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyFields(query, data, options) {
    return applyQueryFields(query, data, options);
}
exports.applyFields = applyFields;
//# sourceMappingURL=module.js.map