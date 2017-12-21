package com.dfsebook.cmeducation.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Cme extends BmobObject {

	private static final long serialVersionUID = 1974310232851744745L;

	private String name;
	
	private BmobFile file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BmobFile getFile() {
		return file;
	}

	public void setFile(BmobFile file) {
		this.file = file;
	}
	
}
