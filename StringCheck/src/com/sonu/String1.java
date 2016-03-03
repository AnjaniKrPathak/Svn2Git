package com.sonu;

public class String1 {

	
	public static void main(String[] args) {

		String str1 = new String("gabbar");
		String str2 = new String("gabbar");
		String str3 = "gabbar";

		System.out.println( str1 == str1.intern() );
		System.out.println( str3 == str1.intern() );

	}

}
