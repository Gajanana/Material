package indi.app.material;

import android.os.Build;

/**
 * Created by GHegde on 1/20/2016.
 */
public class Util {
    public static boolean isLollipoporGreater()
    {
        return Build.VERSION.SDK_INT>=21?true:false;
    }

    public static boolean isJellyBeanorGreater()
    {
        return Build.VERSION.SDK_INT>=16?true:false;
    }
}
