"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseQuerySort = void 0;
const utils_1 = require("../../utils");
const type_1 = require("./type");
// --------------------------------------------------
// --------------------------------------------------
function isMultiDimensionalArray(arr) {
    if (!Array.isArray(arr)) {
        return false;
    }
    return arr.length > 0 && Array.isArray(arr[0]);
}
/**
 * Transform sort data to appreciate data format.
 * @param data
 * @param options
 */
function parseQuerySort(data, options) {
    options = options !== null && options !== void 0 ? options : {};
    // If it is an empty array nothing is allowed
    if (Array.isArray(options.allowed) &&
        options.allowed.length === 0) {
        return [];
    }
    options.aliasMapping = options.aliasMapping ? (0, utils_1.buildObjectFromStringArray)(options.aliasMapping) : {};
    const prototype = Object.prototype.toString.call(data);
    /* istanbul ignore next */
    if (prototype !== '[object String]' &&
        prototype !== '[object Array]' &&
        prototype !== '[object Object]') {
        return [];
    }
    let parts = [];
    if (typeof data === 'string') {
        parts = data.split(',');
    }
    if (Array.isArray(data)) {
        parts = data.filter((item) => typeof item === 'string');
    }
    if (typeof data === 'object') {
        const keys = Object.keys(data);
        for (let i = 0; i < keys.length; i++) {
            /* istanbul ignore next */
            if (!(0, utils_1.hasOwnProperty)(data, keys[i]) ||
                typeof keys[i] !== 'string' ||
                typeof data[keys[i]] !== 'string')
                continue;
            const fieldPrefix = data[keys[i]]
                .toLowerCase() === 'desc' ? '-' : '';
            parts.push(fieldPrefix + keys[i]);
        }
    }
    const items = {};
    for (let i = 0; i < parts.length; i++) {
        let direction = type_1.SortDirection.ASC;
        if (parts[i].substring(0, 1) === '-') {
            direction = type_1.SortDirection.DESC;
            parts[i] = parts[i].substring(1);
        }
        let key = parts[i];
        if ((0, utils_1.hasOwnProperty)(options.aliasMapping, key)) {
            key = options.aliasMapping[key];
        }
        const fieldDetails = (0, utils_1.getFieldDetails)(key);
        if (!(0, utils_1.isFieldAllowedByRelations)(fieldDetails, options.relations, { defaultAlias: options.defaultAlias })) {
            continue;
        }
        const keyWithAlias = (0, utils_1.buildFieldWithAlias)(fieldDetails, options.defaultAlias);
        if (typeof options.allowed !== 'undefined' &&
            !isMultiDimensionalArray(options.allowed) &&
            options.allowed.indexOf(key) === -1 &&
            options.allowed.indexOf(keyWithAlias) === -1) {
            continue;
        }
        let { alias } = fieldDetails;
        if (typeof fieldDetails.path === 'undefined' &&
            typeof fieldDetails.alias === 'undefined') {
            alias = options.defaultAlias;
        }
        items[keyWithAlias] = Object.assign(Object.assign({ key: fieldDetails.name }, (alias ? { alias } : {})), { value: direction });
    }
    if (isMultiDimensionalArray(options.allowed)) {
        // eslint-disable-next-line no-labels,no-restricted-syntax
        outerLoop: for (let i = 0; i < options.allowed.length; i++) {
            const temp = [];
            for (let j = 0; j < options.allowed[i].length; j++) {
                const keyWithAlias = options.allowed[i][j];
                const key = keyWithAlias.includes('.') ?
                    keyWithAlias.split('.').pop() :
                    keyWithAlias;
                if ((0, utils_1.hasOwnProperty)(items, key) ||
                    (0, utils_1.hasOwnProperty)(items, keyWithAlias)) {
                    const item = (0, utils_1.hasOwnProperty)(items, key) ?
                        items[key] :
                        items[keyWithAlias];
                    temp.push(item);
                }
                else {
                    // eslint-disable-next-line no-labels
                    continue outerLoop;
                }
            }
            return temp;
        }
        // if we get no match, the sort data is invalid.
        return [];
    }
    return Object.values(items);
}
exports.parseQuerySort = parseQuerySort;
//# sourceMappingURL=parse.js.map