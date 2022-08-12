"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseQueryFilters = void 0;
const utils_1 = require("../../utils");
const utils_2 = require("./utils");
// --------------------------------------------------
// --------------------------------------------------
function buildOptions(options) {
    var _a;
    options !== null && options !== void 0 ? options : (options = {});
    if (options.aliasMapping) {
        options.aliasMapping = (0, utils_1.buildObjectFromStringArray)(options.aliasMapping);
    }
    else {
        options.aliasMapping = {};
    }
    (_a = options.relations) !== null && _a !== void 0 ? _a : (options.relations = []);
    return options;
}
function parseQueryFilters(data, options) {
    options = options !== null && options !== void 0 ? options : {};
    // If it is an empty array nothing is allowed
    if (typeof options.allowed !== 'undefined' &&
        Object.keys(options.allowed).length === 0) {
        return [];
    }
    // Object.prototype.toString.call(data)
    /* istanbul ignore next */
    if (typeof data !== 'object') {
        return [];
    }
    const { length } = Object.keys(data);
    if (length === 0) {
        return [];
    }
    options = buildOptions(options);
    const temp = {};
    // transform to appreciate data format & validate input
    let keys = Object.keys(data);
    for (let i = 0; i < keys.length; i++) {
        /* istanbul ignore next */
        if (!Object.prototype.hasOwnProperty.call(data, keys[i])) {
            continue;
        }
        let value = data[keys[i]];
        if (typeof value !== 'string' &&
            typeof value !== 'number' &&
            typeof value !== 'boolean' &&
            value !== null) {
            continue;
        }
        if (typeof value === 'string') {
            value = value.trim();
            const stripped = value.replace('/,/g', '');
            if (stripped.length === 0) {
                continue;
            }
            if (value.toLowerCase() === 'null') {
                value = null;
            }
        }
        if (Object.prototype.hasOwnProperty.call(options.aliasMapping, keys[i])) {
            keys[i] = options.aliasMapping[keys[i]];
        }
        const fieldDetails = (0, utils_1.getFieldDetails)(keys[i]);
        if (!(0, utils_1.isFieldAllowedByRelations)(fieldDetails, options.relations, { defaultAlias: options.defaultAlias })) {
            continue;
        }
        const keyWithAlias = (0, utils_1.buildFieldWithAlias)(fieldDetails, options.defaultAlias);
        if (typeof options.allowed !== 'undefined' &&
            options.allowed.indexOf(keys[i]) === -1 &&
            options.allowed.indexOf(keyWithAlias) === -1) {
            continue;
        }
        let alias;
        if (typeof fieldDetails.path === 'undefined' &&
            typeof fieldDetails.alias === 'undefined') {
            alias = options.defaultAlias ?
                options.defaultAlias :
                undefined;
        }
        else {
            alias = fieldDetails.alias;
        }
        temp[keyWithAlias] = Object.assign(Object.assign({ key: fieldDetails.name }, (alias ? { alias } : {})), { value: value });
    }
    const items = [];
    /* istanbul ignore next */
    keys = Object.keys(temp);
    for (let i = 0; i < keys.length; i++) {
        /* istanbul ignore next */
        if (!Object.prototype.hasOwnProperty.call(temp, keys[i])) {
            continue;
        }
        const filter = Object.assign(Object.assign({}, (temp[keys[i]].alias ? { alias: temp[keys[i]].alias } : {})), { key: temp[keys[i]].key, value: temp[keys[i]].value });
        if (typeof filter.value === 'string') {
            const { value, operators } = (0, utils_2.determineFilterOperatorLabelsByValue)(filter.value);
            if (operators.length > 0) {
                filter.value = value;
                filter.operator = {};
                for (let i = 0; i < operators.length; i++) {
                    filter.operator[operators[i]] = true;
                }
            }
        }
        items.push(filter);
    }
    return items;
}
exports.parseQueryFilters = parseQueryFilters;
//# sourceMappingURL=parse.js.map