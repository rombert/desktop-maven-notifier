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

import java.io.File;
import java.io.IOException;

import ro.lmn.maven.dmn.api.NotificationType;

/**
 * NotifierFactory for Mac OS X based on <code><a href="https://github.com/alloy/terminal-notifier">terminal-notifier</a></code>.
 */
public class MacOSXNotifier extends AbstractNotifier {

    private static final File terminalNotifier = new File("/usr/local/bin/terminal-notifier");

    @Override
    public void notify(String title, String details, NotificationType notificationType) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(terminalNotifier.getAbsolutePath(), "-title", title, "-message", details);
        executeProcess(builder);
    }

    @Override
    public boolean isAvailable() {
        if (OSType.MAC != OSType.getConstantForValue(getOSName())) {
            return false;
        }
        return terminalNotifier.exists() && terminalNotifier.canExecute();
    }
}
