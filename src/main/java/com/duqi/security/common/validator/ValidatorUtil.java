//package com.duqi.security.common.validator;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import org.apache.commons.lang3.StringUtils;
//
//public class ValidatorUtil {
//
//	private static final Pattern mobile_pattern = Pattern.compile("^((13[0-9])|(14[5,6,7,9])|(15[^4])|(16[5,6])|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$");
//
//	public static boolean isMobile(String src) {
//		if(StringUtils.isEmpty(src)) {
//			return false;
//		}
//		Matcher m = mobile_pattern.matcher(src);
//		return m.matches();
//	}
//
//}
