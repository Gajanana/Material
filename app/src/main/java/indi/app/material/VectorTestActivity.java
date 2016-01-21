package indi.app.material;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.telly.mrvector.MrVector;

public class VectorTestActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_test);
        toolbar =(Toolbar)findViewById(R.id.app_bar);
        if (null != toolbar) {
            toolbar = (Toolbar) findViewById(R.id.app_bar);
        }
        imageView =(ImageView)findViewById(R.id.VectorImage);
        Drawable drawable = null;
        if(Util.isLollipoporGreater())
        {
            drawable = MrVector.inflate(getResources(),R.drawable.animated_vector_clock);
        }
        else
        {
            drawable = MrVector.inflate(getResources(),R.drawable.vector_clock);
        }

        if(Util.isJellyBeanorGreater())
        {
            imageView.setBackground(drawable);
        }
        else
        {
            imageView.setBackgroundDrawable(drawable);
        }
    }

}
