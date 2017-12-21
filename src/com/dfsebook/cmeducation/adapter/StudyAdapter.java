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

public class StudyAdapter extends BaseAdapter {

	private List<BmobFile> bmobFiles;

	private LayoutInflater mInflater;
	
	public StudyAdapter(Context context, List<BmobFile> bmobFiles) {
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
            convertView = mInflater.inflate(R.layout.study_item, null);
            holder.fileName = (TextView)convertView.findViewById(R.id.fileName);
            convertView.setTag(holder);
        } else {
        	holder = (ViewHolder)convertView.getTag();
        }
        String name = bmobFiles.get(position).getFilename();
        holder.fileName.setText(name.substring(0, name.indexOf(".")));
		return convertView;
	}
	
	public class ViewHolder {
		public TextView fileName;
	}
}
