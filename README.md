# Maven Desktop Notifier

Simple build extension for Maven 3.0.2 or newer which notifies the user when a build is complete.

Currently supported on Linux and Mac OS X 10.8 or higher.

## How To ##

To install the desktop notifier, simply drop it inside the `lib/ext` directory of your Maven installation.

### Linux ###
On Linux Maven Desktop Notifier uses `notify-send`. Tests were performed on  Gnome 3, KDE 4, Unity but other
compliant implementations, for instance those provided by Cinnamon and Xfce should work just fine.

### Mac OS X ###
On Mac OS X 10.8 or higher, Maven Desktop Notifier uses `terminal-notifier`. Installation instructions for `terminal-notifier` can be found
[here](https://github.com/alloy/terminal-notifier/).

## Screenshots

### Gnome

![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-gnome.png)
![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-gnome-failure.png)

### KDE

![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-kde.png)
![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-kde-failure.png)

### Unity

![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-unity.png)
![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-unity-failure.png)

### Mac OS X
![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-macosx.png)
![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-macosx-failure.png)
![](https://raw.github.com/wiki/rombert/maven-desktop-notifier/images/maven-desktop-notifier-macosx-notification-centre.png)
