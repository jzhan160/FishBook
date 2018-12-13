package com.se.fishbook.util;
/**
 *The Result class contains response code and message as
 * a response body for ajax
 *
 */
public class Result {
	private String code;
	private String msg;

	public Result() {
		super();
	}

	public Result(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}