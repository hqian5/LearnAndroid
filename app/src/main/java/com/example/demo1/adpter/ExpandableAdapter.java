package com.example.demo1.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo1.R;
import com.example.demo1.entity.App;
import com.example.demo1.entity.Group;

import java.util.ArrayList;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<Group> gData;
    private ArrayList<ArrayList<App>> cData;

    public ExpandableAdapter(Context mContext, ArrayList<Group> gData, ArrayList<ArrayList<App>> cData) {
        this.mContext = mContext;
        this.gData = gData;
        this.cData = cData;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return cData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (holder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group, parent, false);
            holder = new GroupViewHolder();
            holder.tvGroup = convertView.findViewById(R.id.tv_group);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.tvGroup.setText(gData.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (holder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child, parent, false);
            holder = new ChildViewHolder();
            holder.ivChild = convertView.findViewById(R.id.iv_child);
            holder.tvChild = convertView.findViewById(R.id.tv_child);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        App app = cData.get(groupPosition).get(childPosition);
        holder.ivChild.setImageResource(app.getImgId());
        holder.tvChild.setText(app.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView tvGroup;
    }

    static class ChildViewHolder {
        ImageView ivChild;
        TextView tvChild;
    }
}
