package indi.app.material;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavigationDrawerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigationDrawerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationDrawerFragment extends Fragment implements DataAdapter.AdClickListner{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PREF_FILE_NAME = "MyPrefName" ;
    private static final String PREF_USER_LEARNED_DRAWER = "MyDrawer" ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mdrawerToggle;
    private DataAdapter dataAdapter;
    private DrawerLayout mDrawerLayout;
    private  boolean bUserLearnedDrawer;
    private boolean bFromSavedInstanceState;
    private View containerView;

    // TODO: Rename and change types of parameters
   // private String mParam1;
//    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationDrawerFragment.
     */
    // TODO: Rename and change types and number of parameters
/*    public static NavigationDrawerFragment newInstance(String param1, String param2) {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static List<DataHolder> getRecyclerData()
    {
        List<DataHolder> Information = new ArrayList<>();
        int[] icon ={R.mipmap.ic_image1,R.mipmap.ic_action2};
        String[] title ={"Gja","Hegd"};
        Log.d("gaj","Length"+title.length);
        for(int i=0;i<title.length;i++)
        {
            DataHolder current = new DataHolder();
            current.iconID=icon[i];
            current.title=title[i];
            Information.add(current);
        }
        return Information;
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();
    }

    public static String readPreferences(Context context, String preferenceName, String defaultValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bUserLearnedDrawer=Boolean.valueOf(readPreferences(getActivity(),PREF_USER_LEARNED_DRAWER,"false"));
        if(savedInstanceState != null)
        {
            bFromSavedInstanceState = true;
        }

       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView =(RecyclerView)layout.findViewById(R.id.drawerlist);
        //New adapter which holds data for reCyclerView
        dataAdapter = new DataAdapter(getActivity(),getRecyclerData());
        dataAdapter.setClickListner(this);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclertouchListner(getActivity(), recyclerView, new ClickListner() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getActivity(),"onClick"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View v, int position) {
                Toast.makeText(getActivity(),"onLongClick"+position,Toast.LENGTH_SHORT).show();
            }
        }));
        return layout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setUp(int FragmentId, DrawerLayout drawerLayout, final Toolbar toolBar) {
        containerView =getActivity().findViewById(FragmentId);
    mDrawerLayout = drawerLayout;
        mdrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolBar,R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                if(!bUserLearnedDrawer)
                {
                    bUserLearnedDrawer = true;
                    saveToPreferences(getActivity(),PREF_USER_LEARNED_DRAWER,bUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerSlide(View drawerView,float slideOffset)
            {
                Log.d("GAJA", "offset" + slideOffset);
                if(slideOffset<0.6)
                toolBar.setAlpha(1-slideOffset);
            }
        };
        if(!bUserLearnedDrawer && !bFromSavedInstanceState)
        {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mdrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawerToggle.syncState();
            }
        }) ;
    }

/**
 *Method implementation of interface inside adapter class
 * Called from ClickListner Onclick() in an adpter
 * **/
    @Override
    public void itemClicked(View view, int position) {
        startActivity(new Intent(getActivity(),SubActivity.class));

    }


    public  static  interface ClickListner{
        public void onClick(View v, int position);
        public void onLongClick(View v, int position);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    class RecyclertouchListner implements  RecyclerView.OnItemTouchListener
    {
       private GestureDetector gestureDetector;
        private ClickListner clickListner;
        public RecyclertouchListner (Context context, final RecyclerView recyclerView, final ClickListner clickListner)
        {
            this.clickListner =clickListner;
            gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d("GAJA","onSingleTapUp"+e);
                    //return super.onSingleTapUp(e);
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {


                    View child =recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(null != child && clickListner != null)
                    {
                        clickListner.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                    }
                    Log.d("GAJA", "onLongPress" + e);
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child =rv.findChildViewUnder(e.getX(), e.getY());
            if (null !=child && clickListner != null && gestureDetector.onTouchEvent(e))
            {
                clickListner.onClick(child,rv.getChildAdapterPosition(child));
            }
            //Manually we neeed to forward this call .gestureDetector
            //will analyze

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
