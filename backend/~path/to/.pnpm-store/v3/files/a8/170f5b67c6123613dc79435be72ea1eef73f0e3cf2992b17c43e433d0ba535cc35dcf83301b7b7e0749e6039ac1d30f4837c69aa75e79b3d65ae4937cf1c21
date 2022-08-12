"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.getFieldDetails = void 0;
function getFieldDetails(field) {
    const parts = field.split('.');
    return {
        name: parts.pop(),
        path: parts.length > 0 ? parts.join('.') : undefined,
        alias: parts.length > 0 ? parts.pop() : undefined,
    };
}
exports.getFieldDetails = getFieldDetails;
//# sourceMappingURL=field.js.map