"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.flattenNestedProperties = void 0;
const utils_1 = require("../../utils");
function flattenNestedProperties(data, prefixParts = []) {
    let query = {};
    const keys = Object.keys(data);
    for (let i = 0; i < keys.length; i++) {
        const key = keys[i];
        switch (true) {
            case typeof data[key] === 'boolean' ||
                typeof data[key] === 'string' ||
                typeof data[key] === 'number' ||
                typeof data[key] === 'undefined' ||
                data[key] === null ||
                Array.isArray(data[key]):
                {
                    const destinationKey = [...prefixParts, key].join('.');
                    query[destinationKey] = data[key];
                    break;
                }
            default: {
                // todo: this might be risky, if an entity has 'operator' and 'value' properties :( ^^
                if (typeof data[key] !== 'object') {
                    continue;
                }
                if ((0, utils_1.hasOwnProperty)(data[key], 'operator') &&
                    (0, utils_1.hasOwnProperty)(data[key], 'value')) {
                    const value = Array.isArray(data[key].value) ? data[key].value.join(',') : data[key].value;
                    const destinationKey = [...prefixParts, key].join('.');
                    query[destinationKey] = `${data[key].operator}${value}`;
                    continue;
                }
                query = Object.assign(Object.assign({}, query), flattenNestedProperties(data[key], [...prefixParts, key]));
                break;
            }
        }
    }
    return query;
}
exports.flattenNestedProperties = flattenNestedProperties;
//# sourceMappingURL=flatten.js.map