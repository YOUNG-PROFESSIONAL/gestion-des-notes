"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildQueryFilters = exports.buildQueryFiltersForMany = void 0;
const utils_1 = require("../utils");
const constants_1 = require("./constants");
const utils_2 = require("./utils");
const utils_3 = require("../../utils");
function buildQueryFiltersForMany(input) {
    let data;
    for (let i = 0; i < input.length; i++) {
        if (data) {
            data = (0, utils_3.mergeDeep)(data, input[i]);
        }
        else {
            data = input[i];
        }
    }
    return buildQueryFilters(data);
}
exports.buildQueryFiltersForMany = buildQueryFiltersForMany;
function buildQueryFilters(data) {
    return (0, utils_1.flattenNestedProperties)(transformOperatorConfigToValue(data));
}
exports.buildQueryFilters = buildQueryFilters;
const OperatorWeight = {
    [constants_1.FilterOperator.NEGATION]: 0,
    [constants_1.FilterOperator.LIKE]: 50,
    [constants_1.FilterOperator.LESS_THAN_EQUAL]: 150,
    [constants_1.FilterOperator.LESS_THAN]: 450,
    [constants_1.FilterOperator.MORE_THAN_EQUAL]: 1350,
    [constants_1.FilterOperator.MORE_THAN]: 4050,
    [constants_1.FilterOperator.IN]: 13105,
};
function transformValue(value) {
    if (typeof value === 'undefined') {
        return null;
    }
    return value;
}
function transformOperatorConfigToValue(data) {
    if (Object.prototype.toString.call(data) !== '[object Object]') {
        return data;
    }
    if ((0, utils_2.isFilterOperatorConfig)(data)) {
        data.value = transformValue(data.value);
        if (Array.isArray(data.operator)) {
            // merge operators
            data.operator = data.operator
                .sort((a, b) => OperatorWeight[a] - OperatorWeight[b])
                .join('');
        }
        return data;
    }
    const keys = Object.keys(data);
    for (let i = 0; i < keys.length; i++) {
        data[keys[i]] = transformValue(data[keys[i]]);
        data[keys[i]] = transformOperatorConfigToValue(data[keys[i]]);
    }
    return data;
}
//# sourceMappingURL=build.js.map