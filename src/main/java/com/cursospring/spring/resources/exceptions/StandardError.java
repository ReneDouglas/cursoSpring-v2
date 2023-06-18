package com.cursospring.spring.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer error;
	private String msg;
	private Long timeStamp;
	
	public StandardError(Integer error, String msg, Long timeStamp) {
		super();
		this.error = error;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
