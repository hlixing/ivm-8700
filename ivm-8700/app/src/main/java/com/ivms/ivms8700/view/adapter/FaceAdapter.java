package com.ivms.ivms8700.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ivms.ivms8700.R;
import com.ivms.ivms8700.bean.FaceEntity;
import com.ivms.ivms8700.utils.LocalDbUtil;
import com.ivms.ivms8700.view.FaceDetailActivity;

import java.util.List;

/***
 *
 * 人脸识别适配器 by Alan
 *
 */

public class FaceAdapter  extends RecyclerView.Adapter<FaceAdapter.ViewHolder> {

    private List<FaceEntity> faceList;
    private Context mContext;
    private LocalDbUtil localDbUtil;
    private String local_url;

    public FaceAdapter(List<FaceEntity> mFaceList, Context mContext) {
        this.faceList = mFaceList;
        this.mContext=mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.face_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        localDbUtil = new LocalDbUtil(mContext);
        local_url = localDbUtil.getString("local_url");
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FaceEntity faceEntity = faceList.get(position);
        holder.count.setText((position+1)+"、");
        holder.num.setText("工号："+faceEntity.getEmployeeNumber());
        holder.name.setText("姓名："+faceEntity.getName());
        holder.date_txt.setText("日期："+faceEntity.getDate());

        holder.item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(mContext, FaceDetailActivity.class);
                detailIntent.putExtra("entity",faceEntity);
                mContext.startActivity(detailIntent);
            }
        });
        Glide.with(mContext).load(local_url+"/shm/"+faceEntity.getModelPhoto()).into(holder.user_img);

    }

    @Override
    public int getItemCount() {
        return faceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_view;
        TextView count;
        TextView name;
        TextView num;
        TextView date_txt;
        ImageView user_img;

        public ViewHolder(View view) {
            super(view);
            item_view=(LinearLayout) view.findViewById(R.id.item_view);
            count = (TextView) view.findViewById(R.id.count);
            name = (TextView) view.findViewById(R.id.name);
            num=(TextView)view.findViewById(R.id.num);
            date_txt=(TextView)view.findViewById(R.id.date_txt);
            user_img=(ImageView)view.findViewById(R.id.user_img);
        }
    }


}
