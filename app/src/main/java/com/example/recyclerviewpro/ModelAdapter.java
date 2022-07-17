package com.example.recyclerviewpro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Description
 * @Author PC
 * @QQ 1578684787
 */
class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewModel>{
    private List<Model> modelList;

    public ModelAdapter(List<Model> modelList) {
        this.modelList = modelList;
    }
    //是否是 组的第一个
    public Boolean isGroupHeader(int position){
        if (position==0){
            return true;
        }else{
            String currentName = getGroupName(position);
            String preGroupName = getGroupName(position-1);
            if (preGroupName.equals(currentName)){
                return false;
            }else {
                return true;
            }
        }
    }

    public String getGroupName(int position){
        return modelList.get(position).getGroupName();
    }

    @NonNull
    @Override
    public ModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        return new ModelViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewModel holder, int position) {
        holder.tv.setText(modelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return modelList==null?0: modelList.size();
    }

    public class ModelViewModel extends RecyclerView.ViewHolder{
        private TextView tv;
        public ModelViewModel(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_model);
        }
    }
}
