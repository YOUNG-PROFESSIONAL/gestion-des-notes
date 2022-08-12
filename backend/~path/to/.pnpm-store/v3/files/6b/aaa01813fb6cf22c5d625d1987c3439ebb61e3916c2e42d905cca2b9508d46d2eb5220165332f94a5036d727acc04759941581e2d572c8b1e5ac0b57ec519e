"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildQueryFields = exports.buildQueryFieldsForMany = void 0;
const utils_1 = require("../utils");
const utils_2 = require("../../utils");
function buildQueryFieldsForMany(inputs) {
    let data;
    for (let i = 0; i < inputs.length; i++) {
        if (data) {
            const current = inputs[i];
            if (typeof data === 'string' || typeof current === 'string') {
                data = inputs[i];
            }
            else {
                data = (0, utils_2.mergeDeep)(data, current);
            }
        }
        else {
            data = inputs[i];
        }
    }
    return buildQueryFields(data);
}
exports.buildQueryFieldsForMany = buildQueryFieldsForMany;
function buildQueryFields(data) {
    switch (true) {
        case typeof data === 'string':
            return data;
        case Array.isArray(data):
            return data;
        default:
            return (0, utils_1.flattenNestedProperties)(data);
    }
}
exports.buildQueryFields = buildQueryFields;
//# sourceMappingURL=build.js.map