/*
 * Copyright 2013 Robert Munteanu, Emmanuel Hugonnet, Radu Cotescu
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

import static ro.lmn.maven.dmn.api.NotificationType.FAIL;

import java.io.File;
import java.io.IOException;

import ro.lmn.maven.dmn.api.NotificationType;

/**
 * Linux notifier built on top of {@code /usr/bin/kdialog} and {@code /usr/bin/notify-send}.
 */
public class LinuxNotifier extends AbstractNotifier {

    private static final String KDE_NOTIFIER = "/usr/bin/kdialog";
    private static final String NOTIFIER = "/usr/bin/notify-send";
    private static final String ICON_INFO = "dialog-information";
    private static final String ICON_ERROR = "dialog-error";
    private File notifier;

    public LinuxNotifier() {
        if (Boolean.parseBoolean(System.getenv("KDE_FULL_SESSION"))) {
            notifier = new File(KDE_NOTIFIER);
        } else {
            notifier = new File(NOTIFIER);
        }
    }

    @Override
    public void notify(String title, String details, NotificationType notificationType) throws IOException {
        String icon = (notificationType == FAIL) ? ICON_ERROR : ICON_INFO;
        ProcessBuilder builder = prepareLinuxNotifier(title, details, icon, 2);
        executeProcess(builder);
    }

    @Override
    public boolean isAvailable() {
        if (OSType.LINUX != OSType.getConstantForValue(getOSName())) {
            return false;
        }
        return notifier.exists() && notifier.canExecute();
    }

    /**
     * Create a notification for GNU/Linux
     *
     * @param title the title of the notification.
     * @param details the message of the notification.
     * @param icon the icon to be displayed.
     * @param timeout the duration after which the notification will be dismissed without the user intervention.
     * @return the processbuilder for GNU/Linux
     */
    private ProcessBuilder prepareLinuxNotifier(String title, String details, String icon, int timeout) {
        if (KDE_NOTIFIER.equals(notifier.getAbsolutePath())) {
            return new ProcessBuilder(KDE_NOTIFIER, "--passivepopup", details, "--title", title, "--icon", icon,
                    "" + timeout);
        }
        return new ProcessBuilder(NOTIFIER, title, details, "--icon=" + icon, "--hint=int:transient:1");
    }
}
