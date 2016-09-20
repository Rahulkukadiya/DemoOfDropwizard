package com.sellnews.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationClass {
	static private Pattern pattern;
	static private Matcher matcher;
	static private  final String EMAIL_PATTERN="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

public static boolean verificationOfEmail(String email)
{
	pattern = Pattern.compile(EMAIL_PATTERN);
	matcher = pattern.matcher(email);
	return matcher.matches();
}
}
