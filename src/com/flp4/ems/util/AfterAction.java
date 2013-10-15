package com.flp4.ems.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class AfterAction {
	abstract public String doAction(HttpServletRequest request,HttpServletResponse response);
}
