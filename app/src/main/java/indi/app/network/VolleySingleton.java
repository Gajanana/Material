package indi.app.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import indi.app.material.MyAppContext;

/**
 * Created by ghegde on 12/11/2015.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance=null;
    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader ;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyAppContext.getappContext());
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> lruCache = new LruCache<>((int )(Runtime.getRuntime().maxMemory()/1024)/8);
            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url,bitmap);
            }
        });

    }
    public static VolleySingleton getsInstance()
    {
        if(null == sInstance)
        {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }
    public RequestQueue getRequestQueue(){
        return  mRequestQueue;

    }
    public ImageLoader getImageLoader(){
        return  imageLoader;

    }
}
