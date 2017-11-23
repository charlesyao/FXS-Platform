package com.fxs.platform.utils;

import javax.servlet.http.HttpSession;

public class SessionVariableManager {
	public static void clearSession(HttpSession session) {
		session.removeAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE);
		session.removeAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE);
		session.removeAttribute(SystemConstants.QA_MAP);
		session.removeAttribute(SystemConstants.GEN_CASES);
	}
}
