package com.mapath.bpf.utils;

public class UUID {

	public static String uuid36() {
		return java.util.UUID.randomUUID().toString();
	}

	/**
	 * 生成32位随机数
	 * @return
	 */
	public static String uuid32() {
		StringBuilder sb = new StringBuilder();
		char[] cs = uuid36().toCharArray();
		for (char c : cs) {
			if (c != '-') {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
