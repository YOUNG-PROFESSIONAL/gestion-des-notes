"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.loadScriptFileExportSync = exports.loadScriptFileSync = void 0;
const utils_1 = require("./utils");
const locator_1 = require("../../../locator");
const utils_2 = require("../../utils");
function loadScriptFileSync(data) {
    const filePath = (0, locator_1.isLocatorInfo)(data) ?
        (0, utils_2.buildLoaderFilePath)(data) :
        data;
    try {
        // eslint-disable-next-line @typescript-eslint/no-var-requires, global-require,import/no-dynamic-require
        return require(filePath);
    }
    catch (e) {
        /* istanbul ignore next */
        return undefined;
    }
}
exports.loadScriptFileSync = loadScriptFileSync;
function loadScriptFileExportSync(data, filterFn) {
    const filePath = (0, locator_1.isLocatorInfo)(data) ?
        (0, utils_2.buildLoaderFilePath)(data) :
        data;
    try {
        const data = loadScriptFileSync(filePath);
        return (0, utils_1.getExportItem)(data, filterFn);
    }
    catch (e) {
        /* istanbul ignore next */
        return undefined;
    }
}
exports.loadScriptFileExportSync = loadScriptFileExportSync;
//# sourceMappingURL=sync.js.map