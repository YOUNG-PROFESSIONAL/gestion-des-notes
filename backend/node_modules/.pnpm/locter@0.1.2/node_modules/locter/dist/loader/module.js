"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.loadFileSync = exports.loadFile = void 0;
const file_type_1 = require("./file-type");
const utils_1 = require("./utils");
function loadFile(info) {
    return __awaiter(this, void 0, void 0, function* () {
        if (!info) {
            return undefined;
        }
        const filePath = (0, utils_1.buildLoaderFilePath)(info);
        if (info.fileExtension === '.json') {
            return (0, file_type_1.loadJsonFile)(filePath);
        }
        return (0, file_type_1.loadScriptFile)(filePath);
    });
}
exports.loadFile = loadFile;
function loadFileSync(info) {
    if (!info) {
        return undefined;
    }
    const filePath = (0, utils_1.buildLoaderFilePath)(info);
    if (info.fileExtension === '.json') {
        return (0, file_type_1.loadJsonFileSync)(filePath);
    }
    return (0, file_type_1.loadScriptFileSync)(filePath);
}
exports.loadFileSync = loadFileSync;
//# sourceMappingURL=module.js.map