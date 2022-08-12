"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseQueryParameter = void 0;
const parameter_1 = require("../../parameter");
const constants_1 = require("../../constants");
function parseQueryParameter(key, data, options, relations) {
    switch (key) {
        case constants_1.Parameter.FIELDS:
        case constants_1.URLParameter.FIELDS:
            return (0, parameter_1.parseQueryFields)(data, Object.assign(Object.assign({}, (invalidToEmptyObject(options))), (relations ? { relations } : {})));
        case constants_1.Parameter.FILTERS:
        case constants_1.URLParameter.FILTERS:
            return (0, parameter_1.parseQueryFilters)(data, Object.assign(Object.assign({}, (invalidToEmptyObject(options))), (relations ? { relations } : {})));
        case constants_1.Parameter.PAGINATION:
        case constants_1.URLParameter.PAGINATION:
            return (0, parameter_1.parseQueryPagination)(data, Object.assign({}, (invalidToEmptyObject(options))));
        case constants_1.Parameter.RELATIONS:
        case constants_1.URLParameter.RELATIONS:
            return (0, parameter_1.parseQueryRelations)(data, Object.assign({}, (invalidToEmptyObject(options))));
        default:
            return (0, parameter_1.parseQuerySort)(data, Object.assign(Object.assign({}, (invalidToEmptyObject(options))), (relations ? { relations } : {})));
    }
}
exports.parseQueryParameter = parseQueryParameter;
function invalidToEmptyObject(value) {
    return typeof value === 'boolean' ||
        typeof value === 'undefined' ?
        {} :
        value;
}
//# sourceMappingURL=module.js.map