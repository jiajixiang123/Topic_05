package com.example.a1.topic_05.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.topic_05.R;
import com.example.a1.topic_05.ben.NewsBen;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 1
 * on 2018/3/6.
 * at 北京
 */

public class NewsBenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsBen.ResultBean.DataBean> data;
    private Context context;
    private Listener listener;
    public NewsBenAdapter(List<NewsBen.ResultBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if(TextUtils.isEmpty(data.get(position).getThumbnail_pic_s02())&&TextUtils.isEmpty(data.get(position).getThumbnail_pic_s03())){
            type=1;
        }else if(TextUtils.isEmpty(data.get(position).getThumbnail_pic_s03())){
            type=2;
        }else{
            type=3;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 1:
                View inflate = LayoutInflater.from(context).inflate(R.layout.one, parent, false);
                holder=new OneViewHolder(inflate);
                break;
            case 2:
                View view = LayoutInflater.from(context).inflate(R.layout.two, parent, false);
                holder=new TwoViewHolder(view);
                break;
            case 3:
                View view1 = LayoutInflater.from(context).inflate(R.layout.three, parent, false);
                holder=new ThreeViewHolder(view1);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if(TextUtils.isEmpty(data.get(position).getThumbnail_pic_s02())&&TextUtils.isEmpty(data.get(position).getThumbnail_pic_s03())){
                ((OneViewHolder)holder).name.setText(data.get(position).getAuthor_name());
                ((OneViewHolder)holder).title.setText(data.get(position).getTitle());
                Picasso.with(context).load(data.get(position).getThumbnail_pic_s()).into(((OneViewHolder)holder).pic);
            }else if(TextUtils.isEmpty(data.get(position).getThumbnail_pic_s03())){
                ((TwoViewHolder)holder).name.setText(data.get(position).getAuthor_name());
                ((TwoViewHolder)holder).title.setText(data.get(position).getTitle());
                Picasso.with(context).load(data.get(position).getThumbnail_pic_s()).into(((TwoViewHolder)holder).pic);
                Picasso.with(context).load(data.get(position).getThumbnail_pic_s02()).into(((TwoViewHolder)holder).pic_1);
            }else{
                ((ThreeViewHolder)holder).name.setText(data.get(position).getAuthor_name());
                ((ThreeViewHolder)holder).title.setText(data.get(position).getTitle());
                Picasso.with(context).load(data.get(position).getThumbnail_pic_s()).into(((ThreeViewHolder)holder).pic);
                Picasso.with(context).load(data.get(position).getThumbnail_pic_s02()).into(((ThreeViewHolder)holder).pic_1);
                Picasso.with(context).load(data.get(position).getThumbnail_pic_s03()).into(((ThreeViewHolder)holder).pic_2);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ItemListener(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView title;
        private final ImageView pic;

        public OneViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder{

        private final ImageView pic_1;
        private final ImageView pic;
        private final TextView title;
        private final TextView name;

        public TwoViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            pic_1 = itemView.findViewById(R.id.pic_1);
        }
    }

    class ThreeViewHolder extends RecyclerView.ViewHolder{

        private final ImageView pic_2;
        private final ImageView pic_1;
        private final ImageView pic;
        private final TextView title;
        private final TextView name;

        public ThreeViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            pic_1 = itemView.findViewById(R.id.pic_1);
            pic_2 = itemView.findViewById(R.id.pic_2);
        }
    }

    public interface Listener{
        void ItemListener(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
