/**
* 文件名：AgeType.java
* 创建日期： 2016-4-11
* 作者：     周杰
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016-4-11
*   修改人：Administrator
*   修改内容：
*/
package com.zj.code.rebuild;

/**
 * 功能描述：
 *
 */
public enum AgeType {
	CHILD("child",1),NORMAL("child",2),OLD("child",3);
	String name;
	int code;
	private AgeType(String name,int code){
		this.name=name;
		this.code=code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public static void main(String[] args) {
		AgeType type=AgeType.NORMAL;
		switch(type){
			case CHILD:
				System.out.println("child");
				break;
			case NORMAL:
				System.out.println("normal");
				break;
			case OLD:
				System.out.println("old");
				break;
		}
	}
	
}
