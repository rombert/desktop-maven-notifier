/*
 * Copyright 2013 Radu Cotescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.lmn.maven.dmn.impl;

/**
 * Determines the OS type running on this platform.
 */
public enum OSType {

    LINUX("Linux"),
    MAC("Mac OS X"),
    WINDOWS_7("Windows 7"),
    WINDOWS_8("Windows 8");

    private String os;

    private OSType(String os) {
        this.os = os;
    }

    public static OSType getConstantForValue(String value) {
        for (OSType constant : OSType.values()) {
            if (constant.os.equals(value)) {
                return constant;
            }
        }
        return null;
    }
}
