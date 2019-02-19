package com.wanghuaxiang.demo.application.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: 王华翔
 * @version: 创建时间：2019年2月19日 下午8:46:29
 * @description:
 */
public class MyApplicationEvent extends ApplicationEvent {

	public MyApplicationEvent(Object source) {
		super(source);
		System.out.println("发出消息：" + source);
	}

}
