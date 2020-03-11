package com.zbl.concurrent.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给ArrayList上个锁
 */
public class T03_SynchronizedList {
	public static void main(String[] args) {
		List<String> strs = new ArrayList<>();
		List<String> strsSync = Collections.synchronizedList(strs);
	}
}
