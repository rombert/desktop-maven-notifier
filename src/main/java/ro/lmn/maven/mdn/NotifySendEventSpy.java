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
import java.util.List;

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

        ProcessBuilder builder = new ProcessBuilder("/usr/bin/notify-send", title, details, "--icon=" + icon,
                "--app-name=Maven");
        Process process = builder.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupted status
            return;
        }
    }
}
