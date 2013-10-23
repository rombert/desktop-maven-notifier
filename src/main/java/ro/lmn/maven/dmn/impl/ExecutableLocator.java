/*
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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

/**
 * Locates executables on a search path, including support for
 * the OS PATH environment variable.
 */
public class ExecutableLocator {

    /** Use this to reference the OS PATH environment variable in the search path */
    public static final String PATH_ENV = "PATH";

    private static final boolean IS_WINDOWS = isWindows();

    private List<String> searchPath;
    private File last;

    /**
     * Searches on the paths in the OS PATH environment variable.
     */
    public ExecutableLocator() {
        setSearchPath(new String[] { PATH_ENV });
    }

    /**
     * Searches on the custom defined path list. Use {@link #PATH_ENV} to refer to
     * the OS PATH environment variable in the list (it will be expanded).
     */
    public ExecutableLocator(String[] searchPath) {
        setSearchPath(searchPath);
    }

    public void setSearchPath(String[] sp) {
        searchPath = new ArrayList<String>();
        for (String path : sp) {
            if (PATH_ENV.equals(path)) {
                Collections.addAll(searchPath, getPathEnv());
            } else {
                searchPath.add(path);
            }
        }
        //System.out.printf("Search path: %s", StringUtils.join(searchPath.toArray(), ", "));
    }

    /** Clears the search path. Use to {@link #add(String) add paths} step by step. */
    public void clear() {
        searchPath.clear();
    }

    /** Adds a path at the end of the search path list. */
    public void add(String path) {
        searchPath.add(path);
    }

    /** Checks whether a given executable command is available and executable on the search path. */
    public boolean isAvailable(String cmd) {
        return getPath(cmd) != null;
    }

    /**
     * Returns the full absolute path for the given executable command or null
     */
    public String getPath(String cmd) {
        if (IS_WINDOWS && cmd != null && !cmd.endsWith(".exe")) {
            cmd = cmd + ".exe";
        }
        // quick check for last used executable
        if (last != null && last.getName().equals(cmd) && last.exists()) {
            //System.out.printf("Found executable '%s' (cached): %s", cmd, last.getAbsolutePath());
            return last.getAbsolutePath();
        }

        if (searchPath != null) {
            for (String path : searchPath) {
                File file = new File(path, cmd);
                if (file.exists() && file.canExecute()) {
                    last = file;
                    //System.out.printf("Found executable '%s': %s", cmd, file.getAbsolutePath());
                    return file.getAbsolutePath();
                }
            }
        }

        //System.out.printf("Could not find executable '%s'!", cmd);

        // not found
        return null;
    }

    private static String[] getPathEnv() {
        String path = System.getenv("PATH");
        if (path == null) {
            path = System.getenv("Path");
        }

        if (path == null) {
            return new String[0];
        }

        return StringUtils.split(path, File.pathSeparatorChar + "");
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }
}
