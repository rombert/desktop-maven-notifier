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

import java.util.List;

import org.apache.maven.eventspy.AbstractEventSpy;
import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.ExecutionEvent.Type;
import org.codehaus.plexus.component.annotations.Component;

@Component(role = EventSpy.class)
public class NotifySendEventSpy extends AbstractEventSpy {

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

        Notifier notifier = new Notifier();

        if (exceptions == null || exceptions.isEmpty()) {
            notifier.notifySend("Build successful", "Built " + projectName, Notifier.ICON_INFO);
        } else {
            String errorMessage = exceptions.get(0).getMessage();
            notifier.notifySend("Build failed", projectName + " failed : " + errorMessage, Notifier.ICON_INFO);
        }
    }

    
}
