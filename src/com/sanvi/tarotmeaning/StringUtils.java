package com.sanvi.tarotmeaning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;




/**
 * @author Sanvi E-mail:sanvibyfish@gmail.com
 * @version 创建时间：2010-8-31 下午01:22:13
 */
public class StringUtils {

	private static final String TAG = "StringUtils";

	public static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
	
	public static boolean isEmpty(String str){
		if(str != null && !"".equals(str)){
			return false;
		}
		return true;
	}
}
