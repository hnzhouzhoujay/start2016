/**
* 文件名：Visitor.java
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
public class Visitor {
	PersonType person;
	public double pay(int price ){
		return person.pay(price);
	}

}
