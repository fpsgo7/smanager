package com.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Control {
	void exec(HttpServletRequest request , HttpServletResponse response) throws Exception;
}
