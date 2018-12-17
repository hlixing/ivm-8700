package com.ivms.ivms8700.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivms.ivms8700.R;
import com.ivms.ivms8700.bean.FaceEntity;

import java.util.List;

/***
 *
 * 人脸识别适配器 by Alan
 *
 */

public class FaceAdapter  extends RecyclerView.Adapter<FaceAdapter.ViewHolder> {

    private List<FaceEntity> faceList;
    private Context mContext;
    public FaceAdapter(List<FaceEntity> mFaceList, Context mContext) {
        this.faceList = mFaceList;
        this.mContext=mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.face_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FaceEntity faceEntity = faceList.get(position);
        holder.count.setText((position+1)+"、");
        holder.num.setText("工号："+faceEntity.getEmployeeNumber());
        holder.name.setText("姓名："+faceEntity.getName());
        holder.date_txt.setText("日期："+faceEntity.getDate());
    }

    @Override
    public int getItemCount() {
        return faceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView count;
        TextView name;
        TextView num;
        TextView date_txt;
        public ViewHolder(View view) {
            super(view);
            count = (TextView) view.findViewById(R.id.count);
            name = (TextView) view.findViewById(R.id.name);
            num=(TextView)view.findViewById(R.id.num);
            date_txt=(TextView)view.findViewById(R.id.date_txt);
        }
    }


}