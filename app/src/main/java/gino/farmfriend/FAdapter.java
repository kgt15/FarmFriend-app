package gino.farmfriend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Gino on 18-02-2016.
 */

    public class FAdapter extends RecyclerView.Adapter<FAdapter.MyViewHolder>{
        private LayoutInflater inflater;
        private Context context;
        static int t;
        DrawerLayout d;
        View k;
        List<Information> data= Collections.emptyList();
        NavigationDrawerFragment n=new NavigationDrawerFragment();
        SharedPreferences sp;
        SharedPreferences.Editor ed;
        int delay_time=200;
        public FAdapter(Context context, List<Information> data){
            this.context=context;
            inflater=LayoutInflater.from(context);
            this.data=data;
        }
        public void get(DrawerLayout mdl,View v){
            d=mdl;
            k=v;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=inflater.inflate(R.layout.custom_row, parent,false);
            MyViewHolder holder=new MyViewHolder(view);
            sp= PreferenceManager.getDefaultSharedPreferences(this.context);
            t=Integer.valueOf(sp.getString("track",""));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Information current=data.get(position);
            holder.title.setTextColor(Color.BLACK);
            holder.title.setText(current.title);
            holder.icon.setImageResource(current.iconId);


        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            ImageView icon;

            public MyViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                title = (TextView) itemView.findViewById(R.id.listText);
                icon = (ImageView) itemView.findViewById(R.id.listIcon);
            }



            @Override
            public void onClick(View v) {
                ed=sp.edit();
                switch (getPosition()) {
                    case 0: {
                        if(t==0)
                            d.closeDrawer(k);
                        else {
                            d.closeDrawer(k);
                            ed.putString("track","0");
                            ed.apply();
                            Intent i=new Intent(context,Home.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(new Intent(context, Home.class));
                        }
                        break;
                    }
                    case 1: {
                        ed.putString("track","1");
                        ed.apply();
                        d.closeDrawer(k);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent goto1 = new Intent(context, crop_info.class);
                                goto1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(goto1);
                            }
                        },delay_time);
                        break;
                    }
                    case 2: {
                        ed.putString("track","2");
                        ed.apply();
                        d.closeDrawer(k);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent goto1 = new Intent(context, new_policies.class);
                                goto1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(goto1);
                            }
                        }, delay_time);
                        break;

                    }
                    case 3: {
                        ed.putString("track", "3");
                        ed.apply();
                        d.closeDrawer(k);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent goto1 = new Intent(context, forum.class);
                                goto1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(goto1);
                            }
                        }, delay_time);
                        break;
                    }
                    case 4: {
                        ed.putString("track","3");
                        ed.apply();
                        d.closeDrawer(k);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent goto1 = new Intent(context, contact_details.class);
                                goto1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(goto1);
                            }
                        }, delay_time);
                        break;

                    }
                    case 5: {
                        ed.putString("track","4");
                        ed.apply();
                        d.closeDrawer(k);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent goto1 = new Intent(context, about_us.class);
                                goto1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(goto1);
                            }
                        }, delay_time);
                        break;

                    }
                    default:break;
                }
            }
        }
    }


