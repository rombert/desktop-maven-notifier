
# Desktop Notifier for Maven

Simple build extension for Maven 3.0.2 or newer which notifies the user when a build is complete.

Currently supported on Linux, Mac OS X and Windows (through various notification senders) and on all systems running Java 6.

## How To ##

Pre-built versions of the notifier are available in the [releases area](https://github.com/rombert/desktop-maven-notifier/releases) . 
Download the latest jar and place it  to the `lib/ext` directory of your Maven installation.

If you prefer to built the notifier yourself, download a copy of the sources, and then from within the directory:

    mvn package

The resulting jar will be located in `target/desktop-maven-notifier-*.jar`. 

### Linux ###
On Linux Maven Desktop Notifier uses `notify-send` and, where available, `kdialog`. Tests were performed on  Gnome 3, KDE 4 and Unity
but other compliant implementations, for instance those provided by Cinnamon and Xfce, should work just fine.

### Mac OS X ###
On Mac OS X 10.8 or higher, Maven Desktop Notifier uses `terminal-notifier`. Installation instructions for `terminal-notifier` can be found
[here](https://github.com/alloy/terminal-notifier/).

### Windows ###
There is preliminary support for Windows 7 and 8 using [Snarl](http://sourceforge.net/projects/snarlwin/).

### Java System Tray ###
If none of the notification senders described above are available on your machine, or if simply you're running a different operating system,
Maven Desktop Notifier will fallback to using the Java System Tray mechanism for displaying pop-ups. This requires Java 6 or newer.

## Screenshots

### Gnome

![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-gnome.png)
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-gnome-failure.png)

### KDE

![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-kde.png)
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-kde-failure.png)

### Unity

![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-unity.png)
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-unity-failure.png)

### Mac OS X
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-macosx.png)
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-macosx-failure.png)
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-macosx-notification-centre.png)

### Windows
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-windows.png)
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-windows-failure.png)

### Java System Tray
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-java.png)
![](https://raw.github.com/wiki/rombert/desktop-maven-notifier/images/maven-desktop-notifier-java-failure.png)
