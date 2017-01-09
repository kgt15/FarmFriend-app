package gino.farmfriend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gino on 18-02-2016.
 */

    public class NavigationDrawerFragment extends Fragment {

        private RecyclerView recyclerView;
        public static final String PREF_FILE_NAME="testpref";
        public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
        private ActionBarDrawerToggle mDrawerToggle;
        public DrawerLayout mDrawerLayout;
        public FAdapter adapter;
        private boolean mUserLearnedDrawer;
        private boolean mFromSavedInstanceState;
        public View containerView;
        public static int tracker=0;

        public NavigationDrawerFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mUserLearnedDrawer=Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
            if(savedInstanceState!=null){
                mFromSavedInstanceState=true;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
            recyclerView=(RecyclerView)layout.findViewById(R.id.drawerList);
            adapter=new FAdapter(getActivity(),getData());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return layout;
        }

        public static List<Information> getData(){
            List<Information> data=new ArrayList<>();
            int[] icons={R.mipmap.ic_home_black_24dp,R.mipmap.ic_new_releases_black_24dp,R.mipmap.ic_note_black_24dp,R.mipmap.ic_description_black_24dp,R.mipmap.ic_phone_black_24dp,R.mipmap.ic_info_outline_black_24dp};
            String[] titles={"Home","Crop Info","New Policies","Forum","Contact Details","About Us" };
            for(int i=0;i<titles.length && i<icons.length;i++ ){
                Information current = new Information();
                current.iconId=icons[i];
                current.title=titles[i];
                data.add(current);
            }
            return data;
        }

        public void setUp(int fragmentId,DrawerLayout drawerlayout, final Toolbar toolbar) {
            containerView=getActivity().findViewById(fragmentId);
            mDrawerLayout=drawerlayout;
            adapter.get(mDrawerLayout,containerView);
            mDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerlayout,toolbar,R.string.drawer_open,R.string.drawer_close){
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    if(!mUserLearnedDrawer){
                        mUserLearnedDrawer=true;
                        saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+"");

                    }

                    getActivity().invalidateOptionsMenu();

                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);

                    getActivity().invalidateOptionsMenu();
                }

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    if(slideOffset<0.6) {
                        toolbar.setAlpha(1 - slideOffset);
                    }
                }
            };
            if(!mUserLearnedDrawer&& !mFromSavedInstanceState){
                mDrawerLayout.openDrawer(containerView);
            }

            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerLayout.post(new Runnable() {

                @Override
                public void run() {

                    mDrawerToggle.syncState();

                }
            });


        }
        public static void saveToPreferences(Context context,String preferenceName,String preferenceValue){
            SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE );
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(preferenceName, preferenceValue);
            editor.apply();
        }
        public static String readFromPreferences(Context context,String preferenceName,String defaultValue){
            SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE );
            return sharedPreferences.getString(preferenceName, defaultValue);
        }
    }


