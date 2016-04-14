/**
* 文件名：Cache.java
* 创建日期： 2016-3-29
* 作者：     周杰
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016-3-29
*   修改人：Administrator
*   修改内容：
*/
package com.zj.code.ehcache;

import net.sf.ehcache.*;


/**
 * 功能描述：
 *
 */
public class MyCache {
	/**
	  * maxElementsInMemory：缓存中允许创建的最大对象数
	  * eternal：缓存中对象是否为永久的，如果是，超时设置将被忽略，对象从不过期。
	  * timeToIdleSeconds：缓存数据的钝化时间，也就是在一个元素消亡之前，两次访问时间的最大时间间隔值， 这只能在元素不是永久驻留时有效，
	  * 如果该值是 0 就意味着元素可以停顿无穷长的时间。
	  * timeToLiveSeconds：缓存数据的生存时间，也就是一个元素从构建到消亡的最大时间间隔值，这只能在元素不是永久驻留时有效，
	  * 如果该值是0就意味着元素可以停顿无穷长的时间。 overflowToDisk：内存不足时，是否启用磁盘缓存。
	  * memoryStoreEvictionPolicy：缓存满了之后的淘汰算法
	  *
	  * @param args
	 * @throws InterruptedException 
	  */
	 public static void main(String[] args) throws InterruptedException {
		 CacheManager manager = CacheManager.create();

	        // 取出所有的cacheName
	        String names[] = manager.getCacheNames();
	        System.out.println("----all cache names----");
	        for (int i = 0; i < names.length; i++) {
	            System.out.println(names[i]);
	        }

	        System.out.println("----------------------");
	        // 得到一个cache对象
	        Cache cache1 = manager.getCache(names[0]);

	        // 向cache1对象里添加缓存
	        cache1.put(new Element("key1", "values1"));
	        Element element = cache1.get("key1");

	        // 读取缓存
	        System.out.println("key1 \t= " + element.getObjectValue());

	        // 手动创建一个cache(ehcache里必须有defaultCache存在,"test"可以换成任何值)
	        Cache cache2 = new Cache("test", 1, true, false, 2, 3);
	        manager.addCache(cache2);

	        cache2.put(new Element("jimmy", "菩提树下的杨过"));

	        // 故意停1.5秒，以验证是否过期
	        Thread.sleep(1500);

	        Element eleJimmy = cache2.get("jimmy");

	        //1.5s < 2s 不会过期
	        if (eleJimmy != null) {
	            System.out.println("jimmy \t= " + eleJimmy.getObjectValue());
	        }

	        //再等上0.5s, 总时长：1.5 + 0.5 >= min(2,3),过期
	        Thread.sleep(500);

	        eleJimmy = cache2.get("jimmy");

	        if (eleJimmy != null) {
	            System.out.println("jimmy \t= " + eleJimmy.getObjectValue());
	        }

	        // 取出一个不存在的缓存项
	        System.out.println("fake \t= " + cache2.get("fake"));

	        manager.shutdown();
	  }
}
