package com.dfsebook.cmeducation.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dfsebook.cmeducation.R;
import com.dfsebook.cmeducation.bean.Subject;

public class StudyAdapter extends BaseAdapter {

	private List<Subject> subjects;
	
	private LayoutInflater mInflater;
	
	public StudyAdapter(Context context, List<Subject> subjects) {
		super();
		mInflater = LayoutInflater.from(context);
		this.subjects = subjects;
	}

	@Override
	public int getCount() {
		return subjects == null ? 0 : subjects.size();
	}

	@Override
	public Object getItem(int position) {
		return subjects.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) { 
            holder = new ViewHolder(); 
            convertView = mInflater.inflate(R.layout.study_item, null);
            holder.text = (TextView)convertView.findViewById(R.id.text);   
            holder.aSel = (TextView)convertView.findViewById(R.id.aSel); 
            holder.bSel = (TextView)convertView.findViewById(R.id.bSel);
            holder.cSel = (TextView)convertView.findViewById(R.id.cSel); 
            holder.dSel = (TextView)convertView.findViewById(R.id.dSel); 
            holder.eSel = (TextView)convertView.findViewById(R.id.eSel);
            holder.aBtn = (CheckBox)convertView.findViewById(R.id.aBtn);
            holder.bBtn = (CheckBox)convertView.findViewById(R.id.bBtn);
            holder.cBtn = (CheckBox)convertView.findViewById(R.id.cBtn);
            holder.dBtn = (CheckBox)convertView.findViewById(R.id.dBtn);
            holder.eBtn = (CheckBox)convertView.findViewById(R.id.eBtn);
            convertView.setTag(holder); 
		}
		else {
        	holder = (ViewHolder)convertView.getTag();
        }
        Subject subject = subjects.get(position);
        holder.aBtn.setChecked(false);
        holder.bBtn.setChecked(false);
        holder.cBtn.setChecked(false);
        holder.dBtn.setChecked(false);
        holder.eBtn.setChecked(false);
        if (subject.getOptions()[4] != null) {
        	holder.eBtn.setVisibility(View.VISIBLE);
        }
//        holder = (ViewHolder)convertView.getTag(); 
        holder.text.setText(subject.getStem()); 
        holder.aSel.setText("A." + subject.getOptions()[0]);
        holder.bSel.setText("B." + subject.getOptions()[1]);
        holder.cSel.setText("C." + subject.getOptions()[2]);
        holder.dSel.setText("D." + subject.getOptions()[3]);
        if (subject.getOptions()[4] != null) {
        	holder.eSel.setVisibility(View.VISIBLE);
        	holder.eSel.setText("E." + subject.getOptions()[4]);
        }
        if (subject.getAnswers()[0] == 1)
    		holder.aBtn.setChecked(true);
        if (subject.getAnswers()[1] == 1)
    		holder.bBtn.setChecked(true);
    	if (subject.getAnswers()[2] == 1)
    		holder.cBtn.setChecked(true);
    	if (subject.getAnswers()[3] == 1)
    		holder.dBtn.setChecked(true);
    	if (subject.getAnswers()[4] == 1)
    		holder.eBtn.setChecked(true);
        return convertView;  
	}

	public class ViewHolder {  
        public TextView text;
        public TextView aSel;
        public TextView bSel;
        public TextView cSel;
        public TextView dSel;
        public TextView eSel;        
        public CheckBox aBtn; 
        public CheckBox bBtn; 
        public CheckBox cBtn; 
        public CheckBox dBtn; 
        public CheckBox eBtn; 
    } 
}
