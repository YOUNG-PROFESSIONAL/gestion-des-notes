"use strict";
/*
 * Copyright (c) 2022-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.isSimpleValue = void 0;
function isSimpleValue(value, options) {
    if (typeof value === 'string' ||
        typeof value === 'boolean' ||
        typeof value === 'number') {
        return true;
    }
    options = options || {};
    if (options.withNull) {
        if (value === null) {
            return true;
        }
    }
    if (options.withUndefined) {
        if (typeof value === 'undefined') {
            return true;
        }
    }
    return false;
}
exports.isSimpleValue = isSimpleValue;
//# sourceMappingURL=simple.js.map