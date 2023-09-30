package com.github.tabtipinterop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Allows for native access to open and close the Windows TabTip keyboard which is it's default touch screen keyboard.
 */
public class TabTipInterop {

   static {
      try (InputStream inputStream = TabTipInterop.class.getResourceAsStream("/lib/tabtipinterop.dll")) {
         File tempFile = File.createTempFile("tabtipinterop", ".dll");
         tempFile.deleteOnExit();
         try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while (inputStream != null && (bytesRead = inputStream.read(buffer)) != -1) {
               outputStream.write(buffer, 0, bytesRead);
            }
         }
         System.load(tempFile.getAbsolutePath());
      } catch (IOException e) {
         throw new RuntimeException("Failed to load native DLL", e);
      }
   }

   /**
    * Opens up the Windows TabTip touch screen keyboard
    */
   public static native void openTabTip();

   /**
    * Closes the Windows TabTip touch screen keyboard
    */
   public static native void closeTabTip();

}