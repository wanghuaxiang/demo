package com.wanghuaxiang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;

import com.wanghuaxiang.demo.application.event.MyApplicationEvent;
import com.wanghuaxiang.demo.common.utils.SpringIOCUtil;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.addListeners(new ApplicationListener<MyApplicationEvent>() {
			@Override
			public void onApplicationEvent(MyApplicationEvent event) {
				System.out.println("收到消息：" + event.getSource());
			}
		});
		app.run(args);
		SpringIOCUtil.getApplicationContext().publishEvent(new MyApplicationEvent("hello world"));
	}
}
