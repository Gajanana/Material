package indi.app.material;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Gajanana Hegde on 26/08/2015.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    List<DataHolder> data = Collections.emptyList();
    public DataAdapter(Context context, List<DataHolder >data) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_row,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        DataHolder info = data.get(position);
        myViewHolder.title.setText(info.title);
        myViewHolder.Icon.setImageResource(info.iconID);
    }


    @Override
    public int getItemCount() {
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView Icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title =(TextView)itemView.findViewById(R.id.listText);
            Icon =(ImageView)itemView.findViewById(R.id.listImage);
        }
    }
}
