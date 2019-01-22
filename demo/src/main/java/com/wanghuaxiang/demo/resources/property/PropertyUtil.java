package com.wanghuaxiang.demo.resources.property;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: 王华翔
 * @version: 创建时间：2018年12月31日 下午10:17:07
 * @description:
 */
@Component
public class PropertyUtil {
	private final static Logger	logger	= LoggerFactory.getLogger(PropertyUtil.class);

	private static Properties	properties;

	private static String		FILENAME;

	@Autowired
	PropertyUtil(@Value("${user.dir}") String baseUri) {
		FILENAME = baseUri + File.separator + "runtime.properties";
		start();
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static void writeProperty(String key, String value) {
		OutputStream ops = null;
		try {
			ops = new FileOutputStream(new File(FILENAME));
			properties.put(key, value);
			properties.store(ops, "update key:" + key);
		} catch (Exception e) {
			logger.error("writeProperty Error ,message={}", e.getMessage());
		} finally {
			try {
				if (ops != null) {
					ops.close();
				}
			} catch (Exception e) {
				logger.error("writeProperty ops.close Error ,message={}", e.getMessage());
			}
		}
	}

	private void start() {
		InputStream ips = null;
		try {
			File file = new File(FILENAME);
			if (!file.exists()) {
				file.createNewFile();
			}
			properties = new Properties();
			ips = new BufferedInputStream(new FileInputStream(FILENAME));
			properties.load(ips);
		} catch (Exception e) {
			logger.error("start Error ,message={},FILENAME={}", e.getMessage(), FILENAME);
		} finally {
			if (ips != null) {
				try {
					ips.close();
				} catch (Exception e) {
					logger.error("start ips.close Error ,message={}", e.getMessage());
				}
			}
		}
	}

}
