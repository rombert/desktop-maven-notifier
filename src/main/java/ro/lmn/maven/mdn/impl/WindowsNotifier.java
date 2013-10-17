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

import java.io.File;
import java.io.IOException;

import ro.lmn.maven.mdn.api.NotificationType;

/**
 * NotifierFactory for Microsoft Windows based on <a href="http://snarl.fullphat.net/">Snarl</a>.
 */
public class WindowsNotifier extends AbstractNotifier {

    private static final File heysnarl = new File(System.getenv("ProgramFiles(x86)") + "\\full phat\\Snarl\\tools\\heysnarl.exe");;

    @Override
    public void notify(String title, String details, NotificationType notificationType) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(heysnarl.getAbsolutePath(),
                "\"register?app-sig=maven-desktop-notifier&uid=maven-desktop-notifier&title=Maven Desktop NotifierFactory&keep-alive=1\"");
        executeProcess(builder);
        String icon = "!system-info";
        switch (notificationType) {
            case SUCCESS:
                icon = "!system-yes";
                break;
            case FAIL:
                icon = "!system-critical";
                break;
        }
        builder = new ProcessBuilder(heysnarl.getAbsolutePath(),
                "\"notify?app-sig=maven-desktop-notifier&title=" + title + "&text=" + details + "&icon=" + icon + "\"");
        executeProcess(builder);
    }

    @Override
    public boolean isAvailable() {
        OSType osType = OSType.getConstantForValue(AbstractNotifier.getOSName());
        if (OSType.WINDOWS_7 != osType && OSType.WINDOWS_8 != osType) {
            return false;
        }
        return heysnarl.exists() && heysnarl.canExecute();
    }

    @Override
    public int getPriority() {
        return 100;
    }
}
