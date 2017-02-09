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

import org.apache.maven.artifact.versioning.ComparableVersion;
import ro.lmn.maven.dmn.api.NotificationType;

import java.io.IOException;

/**
 * NotifierFactory for Mac OS X based on OSA <code><a href="https://developer.apple.com/library/content/documentation/AppleScript/Conceptual/AppleScriptLangGuide/reference/ASLR_cmds.html#//apple_ref/doc/uid/TP40000983-CH216-SW224>display notification</a></code>.
 * Support has been introduced with <code><a href="https://developer.apple.com/library/content/releasenotes/AppleScript/RN-AppleScript/RN-10_9/RN-10_9.html">Mac OS X 10.9</a></code>.
 */
public class MacOSXOsaNotifier extends AbstractNotifier {

    public static final String OSASCRIPT = "/usr/bin/osascript";

    @Override
    public void notify(String title, String details, NotificationType notificationType) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(OSASCRIPT, "-e", "display notification \"" + details + "\" with title \"" + title + "\"");
        executeProcess(builder);
    }

    @Override
    public boolean isAvailable() {
        return OSType.MAC == OSType.getDetected() && osaScriptHasDisplayNotification();
    }

    private static boolean osaScriptHasDisplayNotification() {
        return new ComparableVersion(System.getProperty("os.version")).compareTo(new ComparableVersion("10.9")) >= 0;
    }
}
