package indi.app.material;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Gajanana Hegde on 26/08/2015.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {


    public AdClickListner adclickListner;
    List<DataHolder> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    public DataAdapter(Context context, List<DataHolder >data) {
        inflater = LayoutInflater.from(context);
        this.context =context;
        this.data=data;
        Log.d("GAJU","DataAdapter");
    }
    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_row,viewGroup,false);
        Log.d("GAJU","Myviewholder");
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        DataHolder info = data.get(position);
        myViewHolder.title.setText(info.title);
        Log.d("GAJU", "onBindViewHolder");
        myViewHolder.Icon.setImageResource(info.iconID);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListner(AdClickListner clickListner)
    {
        this.adclickListner= clickListner;
    }
    public interface AdClickListner{
        public void itemClicked(View view,int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView Icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title =(TextView)itemView.findViewById(R.id.listText);
            Icon =(ImageView)itemView.findViewById(R.id.listImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context,"ITEM clicked at "+getLayoutPosition(),Toast.LENGTH_SHORT).show();
           // delete(getPosition());

            if(null != adclickListner)
            {
                adclickListner.itemClicked(v,getAdapterPosition());
            }
        }
    }
}
