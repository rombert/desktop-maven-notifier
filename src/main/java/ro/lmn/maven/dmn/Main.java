/*
 * Copyright 2013 Robert Munteanu
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
package ro.lmn.maven.dmn;

import ro.lmn.maven.dmn.api.NotificationType;
import ro.lmn.maven.dmn.api.Notifier;

public class Main {

    public static void main(String[] args) throws Exception {
        NotifierFactory notifierFactory = new NotifierFactory();
        Notifier notifier = notifierFactory.getNotifier();
        if (notifier != null) {
            if (args.length > 0 && "failure".equals(args[0])) {
                notifier.notify("Build failed", "Something went wrong...", NotificationType.FAIL);
            } else {
                notifier.notify("Build successful", "All is right", NotificationType.SUCCESS);
            }
        }
    }
}
