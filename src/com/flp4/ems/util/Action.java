package com.flp4.ems.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Action {

	abstract public String doAction(HttpServletRequest req, HttpServletResponse res);
}
