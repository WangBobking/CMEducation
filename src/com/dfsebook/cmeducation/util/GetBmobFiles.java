package com.dfsebook.cmeducation.util;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.dfsebook.cmeducation.bean.Cme;

public class GetBmobFiles {

	private BmobFileListener bmobFileListener;
	
	private List<BmobFile> bmobFiles = new ArrayList<BmobFile>();
	
	public void getFiles() {		
		BmobQuery<Cme> bmobQuery = new BmobQuery<Cme>();
		bmobQuery.findObjects(new FindListener<Cme>() {
		    @Override
		    public void done(List<Cme> object,BmobException e) {
		        if(e == null){
		            for (Cme cme : object) {
		                BmobFile file = cme.getFile();
		               if(file!= null){
		                   bmobFiles.add(file); 
		               }		        	
		            }
		            if (bmobFileListener != null) {
		            	bmobFileListener.onGetFile(bmobFiles);
		            }
		        }else{
		        	if (bmobFileListener != null) {
		            	bmobFileListener.onGetFile(null);
		            }
		        }
		    }
		});
	}
	
	public void setBmobFileListener(BmobFileListener bmobFileListener) {
		this.bmobFileListener = bmobFileListener;
	}

	public interface BmobFileListener {
		void onGetFile(List<BmobFile> bmobFiles);
	}
}
