package com.dfsebook.cmeducation.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.bmob.v3.datatype.BmobFile;

import com.dfsebook.cmeducation.R;

public class CourseAdapter extends BaseAdapter {

	private List<BmobFile> bmobFiles;

	private LayoutInflater mInflater;
	
	public CourseAdapter(Context context, List<BmobFile> bmobFiles) {
		this.bmobFiles = bmobFiles;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return bmobFiles == null ? 0 : bmobFiles.size();
	}

	@Override
	public Object getItem(int position) {
		return bmobFiles.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder; 
        if(convertView == null) { 
            holder = new ViewHolder(); 
            convertView = mInflater.inflate(R.layout.course_item, null);
            holder.fileName = (TextView)convertView.findViewById(R.id.fileName);
            convertView.setTag(holder);
        } else {
        	holder = (ViewHolder)convertView.getTag();
        }
        String name = bmobFiles.get(position).getFilename();
        name = name.substring(0, name.indexOf("."));
        if (name.length() > 12) {
        	name = name.substring(0, 12) + "...";
        }
        holder.fileName.setText(name);
		return convertView;
	}
	
	public class ViewHolder {
		public TextView fileName;
	}
}
