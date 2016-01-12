package indi.app.material;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import indi.app.network.VolleySingleton;

/**
 * Created by ghegde on 12/9/2015.
 */
public class MyFragment extends Fragment {
    private TextView textView;

    public static MyFragment getInstanceOf(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle arg = new Bundle();
        arg.putInt("position", position);
        myFragment.setArguments(arg);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_my, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Log.d("Gaja", "Crashed");
        Bundle bundle = getArguments();
        if (null != bundle) {
            textView.setText("page selected is " + bundle.getInt("position"));

        }
        VolleySingleton volleySingleton =VolleySingleton.getsInstance();
        RequestQueue requestQueue = volleySingleton.getRequestQueue();
           StringRequest request = new StringRequest(Request.Method.GET, "http://php.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"Response"+response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Error"+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);

        return layout;
    }

}