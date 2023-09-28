import java.io.File;

/**
 * Allows for native access to open and close the Windows TabTip keyboard which is it's default touch screen keyboard.
 */
public class TabTipInterop {

   static {
      System.load(new File("." + File.separator + "lib" + File.separator + System.mapLibraryName("tabtipinterop")).getAbsolutePath());
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