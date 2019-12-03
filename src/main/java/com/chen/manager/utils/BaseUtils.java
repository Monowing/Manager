package com.chen.manager.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class BaseUtils {

	/**
	 * id的字符串拼接转化为id的list
	 * 
	 * @param ids
	 *            id的字符串拼接，以","分隔
	 * @return
	 */
	public static List<Long> convertStringToLong(String ids) {

		if (StringUtils.isEmpty(ids)) {
			return null;
		}

		String[] split = ids.split(",");
		if (split == null || split.length <= 0) {
			return null;
		}

		List<Long> result = new ArrayList<>();
		for (String string : split) {
			Long item = Long.valueOf(string);
			result.add(item);
		}

		return result;
	}
}
