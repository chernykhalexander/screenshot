package ru.chernykh.screenshot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by АЛЕКСАНДР on 04.03.2017.
 */
public class ScreenShot {
    public void makeScreenshot() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        for (int i = 0; i < gs.length; i++) {
            processGraphicsDevice(gs[i],"screenshot/display_" + i + ".png");
        }
    }

    private void processGraphicsDevice(GraphicsDevice graphicsDevice, String path) {
        System.out.println(graphicsDevice.getIDstring());
        DisplayMode mode = graphicsDevice.getDisplayMode();
        System.out.println("Width : " + mode.getWidth() + " ; Height :" + mode.getHeight());
        Rectangle bounds = graphicsDevice.getDefaultConfiguration().getBounds();
        System.out.println(String.format("bounds:Xmin %f Ymin %f Xmax %f Ymax %f",bounds.getMinX(),bounds.getMinY(),bounds.getMaxX(),bounds.getMaxY()));
        BufferedImage screenshot = null;
        try (OutputStream outputStream = new FileOutputStream(path);) {
            screenshot = new Robot(graphicsDevice).createScreenCapture(bounds);
            ImageIO.write(screenshot, "PNG", outputStream);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ScreenShot().makeScreenshot();
    }
}
