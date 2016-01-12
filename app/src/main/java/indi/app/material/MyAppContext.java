package indi.app.material;

import android.app.Application;
import android.content.Context;

/**
 * Created by ghegde on 12/11/2015.
 */
public class MyAppContext extends Application{
    private static MyAppContext sInstance;
    private static Context mAppContext;
    @Override
     public void onCreate()
    {
        super.onCreate();
        sInstance =this;

        this.setAppContext(getApplicationContext());
    }
    public static MyAppContext getsInstance()
    {
        return sInstance;
    }
    public static Context getappContext()
    {
        return mAppContext;
    }

    public void setAppContext(Context appContext) {
        this.mAppContext = appContext;
    }
}
