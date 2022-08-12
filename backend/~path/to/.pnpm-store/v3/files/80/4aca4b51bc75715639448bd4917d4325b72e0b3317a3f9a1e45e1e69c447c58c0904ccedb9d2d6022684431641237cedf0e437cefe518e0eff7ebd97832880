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
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.locateFile = exports.locateFiles = void 0;
const glob_1 = require("glob");
const path_1 = __importDefault(require("path"));
const util_1 = require("util");
const utils_1 = require("./utils");
const globAsync = (0, util_1.promisify)(glob_1.glob);
function locateFiles(pattern, options) {
    return __awaiter(this, void 0, void 0, function* () {
        options = (0, utils_1.buildLocatorOptions)(options);
        const patterns = Array.isArray(pattern) ?
            pattern :
            [pattern];
        const items = [];
        for (let i = 0; i < patterns.length; i++) {
            for (let j = 0; j < options.path.length; j++) {
                const files = yield globAsync(patterns[i], {
                    absolute: true,
                    cwd: options.path[j],
                    nodir: true,
                });
                for (let k = 0; k < files.length; k++) {
                    const fileInfo = path_1.default.parse(files[k]);
                    items.push({
                        path: fileInfo.dir.split('/').join(path_1.default.sep),
                        fileName: fileInfo.name,
                        fileExtension: fileInfo.ext,
                    });
                }
            }
        }
        return items;
    });
}
exports.locateFiles = locateFiles;
function locateFile(pattern, options) {
    return __awaiter(this, void 0, void 0, function* () {
        options = (0, utils_1.buildLocatorOptions)(options);
        const patterns = Array.isArray(pattern) ?
            pattern :
            [pattern];
        for (let i = 0; i < patterns.length; i++) {
            for (let j = 0; j < options.path.length; j++) {
                const files = yield globAsync(patterns[i], {
                    absolute: true,
                    cwd: options.path[j],
                    nodir: true,
                });
                const element = files.shift();
                if (element) {
                    const fileInfo = path_1.default.parse(element);
                    return {
                        path: fileInfo.dir.split('/').join(path_1.default.sep),
                        fileName: fileInfo.name,
                        fileExtension: fileInfo.ext,
                    };
                }
            }
        }
        return undefined;
    });
}
exports.locateFile = locateFile;
//# sourceMappingURL=async.js.map