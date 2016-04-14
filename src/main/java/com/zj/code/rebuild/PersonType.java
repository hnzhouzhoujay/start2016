/**
* 文件名：PersonType.java
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
public abstract class PersonType {
	public  double pay(int price){
		switch(getType()){
		case CHILD:
			return price*0.5;
		case NORMAL:
			return price;
		case OLD:
			return price*0.7;
	}
	return 0;
	}
	 abstract AgeType getType();
	 abstract double caculate(int price);
}
