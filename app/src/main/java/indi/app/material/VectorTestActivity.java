package indi.app.material;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_test);
        if (null != toolbar) {
            toolbar = (Toolbar) findViewById(R.id.app_bar);
        }
        imageView =(ImageView)findViewById(R.id.VectorImage);
        Drawable drawable = MrVector.inflate(getResources(),R.drawable.vector_android);
        if(Build.VERSION.SDK_INT >=16)
        {
            imageView.setBackground(drawable);
        }
        else
        {
            imageView.setBackgroundDrawable(drawable);
        }
    }

}
