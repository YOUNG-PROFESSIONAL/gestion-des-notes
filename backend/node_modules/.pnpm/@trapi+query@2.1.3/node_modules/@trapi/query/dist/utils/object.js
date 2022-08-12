"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildObjectFromStringArray = exports.hasOwnProperty = void 0;
function hasOwnProperty(obj, prop) {
    return Object.prototype.hasOwnProperty.call(obj, prop);
}
exports.hasOwnProperty = hasOwnProperty;
/**
 * Build alias mapping from strings in array or object representation to object representation.
 *
 * {field1: 'field1', ...} => {field1: 'field1', ...}
 * ['field1', 'field2'] => {field1: 'field1', field2: 'field2'}
 *
 * @param rawFields
 */
function buildObjectFromStringArray(rawFields) {
    if (Array.isArray(rawFields)) {
        const record = {};
        rawFields
            .filter((field) => typeof field === 'string')
            .map((field) => {
            record[field] = field;
            return field;
        });
        return record;
    }
    return rawFields;
}
exports.buildObjectFromStringArray = buildObjectFromStringArray;
//# sourceMappingURL=object.js.map