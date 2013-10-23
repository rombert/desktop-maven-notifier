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
package ro.lmn.maven.dmn.impl;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static ro.lmn.maven.dmn.impl.FileExistsMatcher.exists;

import java.io.File;

import org.junit.Test;

public class ExecutableLocatorTest {

    @Test
    public void getPath_UnixLike() {

        assumeThat(OSType.getDetected(), anyOf(is(OSType.LINUX), is(OSType.MAC)));

        String pathToLs = new ExecutableLocator(OSType.getDetected()).getPath("ls");

        assertThat(new File(pathToLs), exists());
    }

    @Test
    public void getPath_Windows() {

        assumeThat(OSType.getDetected() != null && OSType.getDetected().isWindows(), is(true));

        String pathToIpConfig = new ExecutableLocator(OSType.getDetected()).getPath("ipconfig");
        String pathToIpConfigExe = new ExecutableLocator(OSType.getDetected()).getPath("ipconfig.exe");

        assertThat(new File(pathToIpConfig), exists());
        assertThat(new File(pathToIpConfigExe), exists());
    }
}
