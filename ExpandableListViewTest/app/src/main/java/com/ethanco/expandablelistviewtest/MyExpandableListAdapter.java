package com.ethanco.expandablelistviewtest;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableListAdapter implements ExpandableListAdapter {
    private Context context;

    public MyExpandableListAdapter(Context context) {
        this.context = context;
    }

    private String[] armTypes = new String[]{
            "WORD", "EXCEL", "EMAIL", "PPT"
    };
    private String[][] arms = new String[][]{
            {"文档编辑", "文档排版", "文档处理", "文档打印"},
            {"表格编辑", "表格排版", "表格处理", "表格打印"},
            {"收发邮件", "管理邮箱", "登录登出", "注册绑定"},
            {"演示编辑", "演示排版", "演示处理", "演示打印"},
    };

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() { ////获取group数量
        return armTypes.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) { //获取children数量
        return arms[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) { //获取Group
        return armTypes[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) { //获取Child
        return arms[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) { //获取GroupId
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) { //获取child的Id
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.group, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textGroup);
        textView.setText(getGroup(groupPosition).toString());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.childs, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textChild);
        textView.setText(getChild(groupPosition, childPosition).toString());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { //子列表是否可选
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
