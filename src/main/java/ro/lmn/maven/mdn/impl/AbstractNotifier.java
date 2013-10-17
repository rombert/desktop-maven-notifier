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
package ro.lmn.maven.mdn.impl;

import java.io.IOException;

import ro.lmn.maven.mdn.api.Notifier;

/**
 * Base abstract class for {@link Notifier}s.
 */
public abstract class AbstractNotifier implements Notifier {

    protected static String getOSName() {
        return System.getProperty("os.name");
    }

    protected void executeProcess(ProcessBuilder processBuilder) throws IOException {
        Process process = processBuilder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
