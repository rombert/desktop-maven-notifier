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
package ro.lmn.maven.dmn.api;

import java.io.IOException;

/**
 * Defines a notifier used to pop-up a notification on various operating systems' desktop managers providing useful information about the
 * outcome of a Maven build.
 */
public interface Notifier {

    static final int DEFAULT_PRIORITY = 0;

    /**
     * Sends a notification event to the underlying notifications system provided by the OS.
     *
     * @param title            the title of the notification
     * @param details          the notification's details (e.g. status text)
     * @param notificationType the notification type
     * @throws IOException if the notification cannot be sent
     */
    void notify(String title, String details, NotificationType notificationType) throws IOException;

    /**
     * Checks if this notifier can run on this platform.
     *
     * @return {@code true} if the notifier is supported on this platform, {@code false} otherwise
     */
    boolean isAvailable();

    /**
     * Returns the priority of this notifier, compared to other notifiers that could be available on the platform.
     * @return the priority
     */
    int getPriority();
}
