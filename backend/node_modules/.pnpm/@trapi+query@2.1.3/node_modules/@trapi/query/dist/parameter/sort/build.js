"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildQuerySort = exports.buildQuerySortForMany = void 0;
const utils_1 = require("../utils");
const utils_2 = require("../../utils");
function buildQuerySortForMany(inputs) {
    let data;
    for (let i = 0; i < inputs.length; i++) {
        if (data) {
            const current = inputs[i];
            if (typeof data === 'string' ||
                typeof current === 'string') {
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
    return buildQuerySort(data);
}
exports.buildQuerySortForMany = buildQuerySortForMany;
function buildQuerySort(data) {
    switch (true) {
        case typeof data === 'string':
            return data;
        case Array.isArray(data):
            return data;
        default:
            return (0, utils_1.flattenNestedProperties)(data);
    }
}
exports.buildQuerySort = buildQuerySort;
//# sourceMappingURL=build.js.map