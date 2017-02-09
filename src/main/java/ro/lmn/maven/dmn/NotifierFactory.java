/*
 * Copyright 2013 Radu Cotescu, Robert Munteanu
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ro.lmn.maven.dmn.api.Notifier;
import ro.lmn.maven.dmn.impl.LinuxNotifier;
import ro.lmn.maven.dmn.impl.MacOSXNotifier;
import ro.lmn.maven.dmn.impl.MacOSXOsaNotifier;
import ro.lmn.maven.dmn.impl.SystemTrayNotifier;
import ro.lmn.maven.dmn.impl.WindowsNotifier;

public class NotifierFactory {

    private final List<Notifier> notifiers = new ArrayList<Notifier>();
    {
        notifiers.add(new LinuxNotifier());
        notifiers.add(new MacOSXNotifier());
        notifiers.add(new MacOSXOsaNotifier());
        notifiers.add(new WindowsNotifier());
        notifiers.add(new SystemTrayNotifier());
        
        Collections.sort(notifiers, new Comparator<Notifier>() {
            @Override
            public int compare(Notifier o1, Notifier o2) {
                return Integer.valueOf(o2.getPriority()).compareTo(o1.getPriority());
            }
        });
    }
    
    public Notifier getNotifier() {
        for (Notifier n : notifiers) {
            if (n.isAvailable()) {
                return n;
            }
        }
        return null;
    }
}
