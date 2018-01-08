package com.fxs.platform.utils;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class SessionVariableManager {
	public static void clearSession(HttpSession session) {
		session.removeAttribute(SystemConstants.FALLTYPUS_LEVEL1_TYPE);
		session.removeAttribute(SystemConstants.FALLTYPUS_LEVEL2_TYPE);
		session.removeAttribute(SystemConstants.QA_MAP);
		session.removeAttribute(SystemConstants.GEN_CASES);
		session.removeAttribute(SystemConstants.SEARCH_FROM_KEY);
		session.removeAttribute(SystemConstants.SEARCH_FROM_FALLTYPUS);
		session.removeAttribute(SystemConstants.CASE_DATASET_WITH_FILTER_CONDITION);
		session.removeAttribute(SystemConstants.REQUEST_FROM_LAWYER_USER_CENTER);
		session.removeAttribute(SystemConstants.SEARCH_FROM_LAWYER_CASEPOOL);
		session.removeAttribute(SystemConstants.SEARCH_FROM_LAWYER_DASHBOARD);
		session.setAttribute(SystemConstants.QA_MAP, new HashMap<Integer, Object[]>());
	}
}
