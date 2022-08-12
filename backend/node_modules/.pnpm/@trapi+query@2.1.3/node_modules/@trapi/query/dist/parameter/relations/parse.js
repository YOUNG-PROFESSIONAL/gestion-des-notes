"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseQueryRelations = void 0;
const minimatch_1 = __importDefault(require("minimatch"));
const utils_1 = require("../../utils");
// --------------------------------------------------
// --------------------------------------------------
function includeParents(data, options) {
    const ret = [];
    for (let i = 0; i < data.length; i++) {
        const parts = data[i].split('.');
        let value = parts.shift();
        /* istanbul ignore next */
        if (options.aliasMapping &&
            (0, utils_1.hasOwnProperty)(options.aliasMapping, value)) {
            value = options.aliasMapping[value];
        }
        if (ret.indexOf(value) === -1) {
            ret.push(value);
        }
        while (parts.length > 0) {
            const postValue = parts.shift();
            value += `.${postValue}`;
            /* istanbul ignore next */
            if (options.aliasMapping &&
                (0, utils_1.hasOwnProperty)(options.aliasMapping, value)) {
                value = options.aliasMapping[value];
            }
            if (ret.indexOf(value) === -1) {
                ret.push(value);
            }
        }
    }
    return ret;
}
function parseQueryRelations(data, options) {
    var _a;
    options !== null && options !== void 0 ? options : (options = {});
    // If it is an empty array nothing is allowed
    if (Array.isArray(options.allowed) &&
        options.allowed.length === 0) {
        return [];
    }
    if (options.aliasMapping) {
        options.aliasMapping = (0, utils_1.buildObjectFromStringArray)(options.aliasMapping);
    }
    else {
        options.aliasMapping = {};
    }
    (_a = options.includeParents) !== null && _a !== void 0 ? _a : (options.includeParents = true);
    let items = [];
    const prototype = Object.prototype.toString.call(data);
    if (prototype !== '[object Array]' &&
        prototype !== '[object String]') {
        return [];
    }
    if (prototype === '[object String]') {
        items = data.split(',');
    }
    if (prototype === '[object Array]') {
        items = data.filter((el) => typeof el === 'string');
    }
    if (items.length === 0) {
        return [];
    }
    items = items
        .map((item) => {
        if ((0, utils_1.hasOwnProperty)(options.aliasMapping, item)) {
            item = options.aliasMapping[item];
        }
        return item;
    });
    if (options.allowed) {
        items = items
            .filter((item) => {
            for (let i = 0; i < options.allowed.length; i++) {
                if ((0, minimatch_1.default)(item, options.allowed[i])) {
                    return true;
                }
            }
            return false;
        });
    }
    if (options.includeParents) {
        if (Array.isArray(options.includeParents)) {
            const parentIncludes = items.filter((item) => item.includes('.') && options.includeParents.filter((parent) => (0, minimatch_1.default)(item, parent)).length > 0);
            items.unshift(...includeParents(parentIncludes, options));
        }
        else {
            items = includeParents(items, options);
        }
    }
    items = Array.from(new Set(items));
    return items
        .map((relation) => {
        let key;
        if (relation.includes('.')) {
            key = relation.split('.').slice(-2).join('.');
        }
        else {
            key = options.defaultAlias ? `${options.defaultAlias}.${relation}` : relation;
        }
        return {
            key,
            value: relation.split('.').pop(),
        };
    });
}
exports.parseQueryRelations = parseQueryRelations;
//# sourceMappingURL=parse.js.map