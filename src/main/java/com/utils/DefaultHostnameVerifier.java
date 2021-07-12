package com.utils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class DefaultHostnameVerifier implements HostnameVerifier {

	@Override
	public boolean verify(String hostname, SSLSession session) {
		// TODO Auto-generated method stub
		return true;
	}
}
