/**
* 文件名：QuickSort.java
* 创建日期： 2016-4-13
* 作者：     周杰
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016-4-13
*   修改人：Administrator
*   修改内容：
*/
package com.zj.datastruct.sort;

/**
 * 功能描述：
 *
 */
public class QuickSort {
	public static void sort(int low ,int high,int array[]){
		if(low>=high) return;
		int midVal=array[low];
		int head=low,tail=high;
		while(head!=tail){
			while(head<tail&&array[tail]>midVal){
				tail--;
			}
			if(head<tail)
				array[head++]=array[tail];
			while(head<tail&&array[head]<midVal){
				head++;
			}
			if(head<tail) 
				array[tail--]=array[head];
		}
		array[head]=midVal;
		int middle=head;
		sort(low,middle-1,array);
		sort(middle+1,high,array);
	}
	public static int findOrder(int low ,int high,int array[]){
		int midVal=array[low];
		int head=low,tail=high;
		while(head!=tail){
			while(head<tail&&array[tail]>midVal){
				tail--;
			}
			if(head<tail)
				array[head++]=array[tail];
			while(head<tail&&array[head]<midVal){
				head++;
			}
			if(head<tail) 
				array[tail--]=array[head];
		}
		array[head]=midVal;
		return head;
	}
	public static void print(int array[]){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+",");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int[] a={99,1,6,7,3,100,0,5,8,75,121,5};
		sort(0,a.length-1,a);
		print(a);
	}

}
