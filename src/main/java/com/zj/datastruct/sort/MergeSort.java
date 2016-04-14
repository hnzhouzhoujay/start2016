/**
* 文件名：MergeSort.java
* 创建日期： 2016-4-14
* 作者：     周杰
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016-4-14
*   修改人：Administrator
*   修改内容：
*/
package com.zj.datastruct.sort;

/**
 * 功能描述：
 *
 */
public class MergeSort {
	public static void sort(int a[],int low,int high){
		int middle=(high-low)/2;
		if(low<high){
			sort(a,low,middle-1);
			sort(a,middle+1,high);
			merge(a,low,high);
		}
	}
	public static void merge(int a[],int low,int high){
		int[] temp=new int[high-low+1];
		int li1=low,mid=(high-low)/2,li2=mid+1,k=0;
		while(li1<=mid&&li2<=high){
			if(a[li1]<a[li2]){
				temp[k++]=a[li1++];
			}else{
				temp[k++]=a[li2++];
			}
		}
		while(li1<=mid){
			temp[k++]=a[li1++];
		}
		while(li2<=high){
			temp[k++]=a[li2++];
		}
		for(int i=0;i<temp.length;i++){
			a[low+i]=temp[i];
		}
	}
	public static void main(String[] args) {
		int a[]={0,6,2,1,3,10,7,8};
		sort(a,0,a.length);
		for(int i=0;i<a.length;i++){
			System.out.print(a+" ");
		}
		
	}
}
