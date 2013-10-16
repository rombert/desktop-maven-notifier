/*
 * Copyright 2008-2013 Robert Munteanu
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
package ro.lmn.maven.mdn;

import java.io.IOException;

import org.apache.maven.eventspy.AbstractEventSpy;
import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.ExecutionEvent.Type;
import org.codehaus.plexus.component.annotations.Component;

@Component(role = EventSpy.class)
public class NotifySendEventSpy extends AbstractEventSpy {

    private static final String ICON_INFO = "dialog-information";
    private static final String ICON_ERROR = "dialog-error";

    public static void main(String[] args) throws IOException {

        new NotifySendEventSpy().notifySend("Test message", "This is an error!", ICON_ERROR);
    }

    @Override
    public void onEvent(Object event) throws Exception {

        if (!(event instanceof ExecutionEvent)) {
            return;
        }

        ExecutionEvent ee = (ExecutionEvent) event;

        if (ee.getType() != Type.SessionEnded) {
            return;
        }

        String projectName = ee.getSession().getTopLevelProject().getName();
        List<Throwable> exceptions = ee.getSession().getResult().getExceptions();

        if (exceptions == null || exceptions.isEmpty()) {
            notifySend("Build successful", "Built " + projectName, ICON_INFO);
        } else {
            String errorMessage = exceptions.get(0).getMessage();
            notifySend("Build failed", projectName + " failed : " + errorMessage, ICON_ERROR);
        }
    }

    private void notifySend(String title, String details, String icon) throws IOException {

        ProcessBuilder builder = null;
        OSName os = OSName.getConstantForValue(System.getProperty("os.name"));
        if (os != null) {
            switch (os) {
                case LINUX: 
                    builder = prepareLinuxNotifier(title, details, icon, 2);
                    break;
                case MAC:
                    builder = new ProcessBuilder("terminal-notifier", "-title", title, "-message", details);
            }

            Process process = builder.start();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupted status
                return;
            }
        }
    }
    
    /**
     * Create a notification for GNU/Linux
     * @param title the title of the notification.
     * @param details the message of the notification.
     * @param icon the icon to be displayed.
     * @param timeout the duration after which the notification will be dismissed without the user intervention. 
     * @return the processbuilder for GNU/Linux
     */
    private ProcessBuilder prepareLinuxNotifier(String title, String details, String icon, int timeout) {
        if(Boolean.parseBoolean(System.getenv("KDE_FULL_SESSION"))) {
            return new ProcessBuilder("/usr/bin/kdialog", "--passivepopup",  details , "--title",  title, "--icon", icon, "" + timeout);
        }
        return new ProcessBuilder("/usr/bin/notify-send", title, details, "--icon=" + icon, "--hint=int:transient:1");
    }

    private enum OSName {

        LINUX("Linux"),
        MAC("Mac OS X");

        private String osName;

        private OSName(String osName) {
            this.osName = osName;
        }

        public static OSName getConstantForValue(String value) {
            for (OSName constant : OSName.values()) {
                if (constant.osName.equals(value)) {
                    return constant;
                }
            }
            return null;
        }

    }
}
