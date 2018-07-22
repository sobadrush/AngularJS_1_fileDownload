package com.ctbc.model.vo;

import java.io.InputStream;
import java.io.Serializable;

public class EmpPhotoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer photoId;
	private String photoName;
	private InputStream photoFile;
	
	public EmpPhotoVO() {
		super();
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public InputStream getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(InputStream photoFile) {
		this.photoFile = photoFile;
	}

	@Override
	public String toString() {
		return "EmpPhotoVO [photoId=" + photoId + ", photoName=" + photoName + ", photoFile=" + photoFile + "]";
	}
	
}
