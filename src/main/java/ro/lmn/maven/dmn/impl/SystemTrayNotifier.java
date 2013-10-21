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
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.*;

import ro.lmn.maven.dmn.api.NotificationType;

public class SystemTrayNotifier extends AbstractNotifier {

    private SystemTray tray;
    
    public static void main(String[] args) throws IOException {
        
        SystemTrayNotifier notifier = new SystemTrayNotifier();
        notifier.notify("Build successful", "Hello world", NotificationType.SUCCESS);
    }

    @Override
    public void notify(final String title, final String details, final NotificationType notificationType) throws IOException {

        final SystemTray tray = SystemTray.getSystemTray();
        final CountDownLatch latch = new CountDownLatch(1);
        final TrayIcon icon = getTrayIcon(notificationType);
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    tray.add(icon);
                    icon.displayMessage(title, details, getMessageType(notificationType));
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

    private TrayIcon getTrayIcon(NotificationType notificationType) throws IOException {
        Dimension size = tray().getTrayIconSize();
        String imageName = null;
        switch (notificationType) {
            case SUCCESS:
                imageName = "/icons/ok.png";
                break;
            case FAIL:
                imageName = "/icons/error.png";
                break;
        }
        BufferedImage icon = ImageIO.read(getClass().getResourceAsStream(imageName));
        return new TrayIcon(icon.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
    }

    private TrayIcon.MessageType getMessageType(NotificationType notificationType) {
        switch (notificationType) {
            case SUCCESS:
                return TrayIcon.MessageType.INFO;
            case FAIL:
                return TrayIcon.MessageType.ERROR;
            default:
                return TrayIcon.MessageType.NONE;
        }
    }

    private SystemTray tray() {
        if (tray == null) {
            tray = SystemTray.getSystemTray();
        }
        return tray;
    }
}
