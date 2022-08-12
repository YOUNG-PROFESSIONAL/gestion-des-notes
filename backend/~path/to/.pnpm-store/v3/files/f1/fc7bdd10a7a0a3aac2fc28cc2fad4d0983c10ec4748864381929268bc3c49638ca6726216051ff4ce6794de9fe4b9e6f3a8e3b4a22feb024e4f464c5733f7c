"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildURLQueryString = void 0;
function buildURLQueryString(data, withQuestionMark = true) {
    if (typeof data === 'undefined' || data === null)
        return '';
    // If the data is already a string, return it as-is
    if (typeof (data) === 'string')
        return data;
    // Create a query array to hold the key/value pairs
    const query = [];
    // Loop through the data object
    const keys = Object.keys(data);
    for (let i = 0; i < keys.length; i++) {
        if (Object.prototype.hasOwnProperty.call(data, keys[i])) {
            let value = data[keys[i]];
            if (value && typeof value === 'object' && value.constructor === Array) {
                value = value.join(',');
            }
            if (value && typeof value === 'object' && value.constructor === Object) {
                const valueKeys = Object.keys(value);
                for (let j = 0; j < valueKeys.length; j++) {
                    let v = value[valueKeys[j]];
                    if (v && typeof v === 'object' && v.constructor === Array) {
                        v = v.join(',');
                    }
                    query.push(`${encodeURIComponent(`${keys[i]}[${valueKeys[j]}]`)}=${encodeURIComponent(v)}`);
                }
                continue;
            }
            // Encode each key and value, concatenate them into a string, and push them to the array
            query.push(`${encodeURIComponent(keys[i])}=${encodeURIComponent(value)}`);
        }
    }
    // Join each item in the array with a `&` and return the resulting string
    return (withQuestionMark ? '?' : '') + query.join('&');
}
exports.buildURLQueryString = buildURLQueryString;
//# sourceMappingURL=url.js.map