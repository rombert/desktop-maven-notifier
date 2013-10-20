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

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;

import ro.lmn.maven.dmn.api.NotificationType;

public class SystemTrayNotifier extends AbstractNotifier {
    
    public static void main(String[] args) throws IOException {
        
        SystemTrayNotifier notifier = new SystemTrayNotifier();
        notifier.notify("Build successful", "Built Vaile-Maven-Plugin", NotificationType.FAIL);
    }

    private SystemTray tray;

    @Override
    public void notify(final String title, final String details,
            final NotificationType notificationType) throws IOException {
        
        final CountDownLatch latch = new CountDownLatch(1);
        final TrayIcon icon = createTrayIcon();
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    tray.add(icon);
                    MessageType messageType = notificationType == NotificationType.FAIL ? MessageType.ERROR : MessageType.INFO;
                    icon.displayMessage(title, details, messageType);
                    latch.countDown();
                } catch (AWTException e) {
                    // don't care, no reporting channel
                }
            }
        });

        // stay visible for 5 seconds
        try {
            latch.await();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        
        tray.remove(icon);
    }

    @Override
    public boolean isAvailable() {
        return SystemTray.isSupported();
    }

    @Override
    public int getPriority() {
        return 0;
    }
    

    private TrayIcon createTrayIcon() throws IOException {
        Dimension size = tray().getTrayIconSize();
        BufferedImage icon = ImageIO.read(getClass().getResource("/systray-icon.gif"));
        return new TrayIcon(icon.getScaledInstance( size.width  , size.height, Image.SCALE_SMOOTH));
    }

    private SystemTray tray() {
        
        if ( tray == null ) {
            tray = SystemTray.getSystemTray();
        }
        return tray;
    }    

}
