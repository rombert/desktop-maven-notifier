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

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Notifier notifier = new Notifier();

        if (args.length > 0 && "failure".equals(args[0])) {
            notifier.notifySend("Build failed", "Something went wrong...", Notifier.ICON_ERROR);
        } else {
            notifier.notifySend("Build successful", "All is right", Notifier.ICON_INFO);
        }

    }

}
