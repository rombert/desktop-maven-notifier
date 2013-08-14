# Maven Desktop Notifier

Simple build extension for Maven 3.0.2 or newer which notifies the user when a build is complete.

Currently supported on Linux only using `notify-send`. I've tested Gnome 3, KDE 4, Unity but other
compliant implementations, for instance those provided by Cinnamon and Xfce should work just fine.

To install the desktop notifier, simply drop it inside the `lib/ext` directory of your Maven installation.

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