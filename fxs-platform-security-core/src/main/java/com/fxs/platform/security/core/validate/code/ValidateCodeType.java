package com.fxs.platform.security.core.validate.code;

import com.fxs.platform.security.core.properties.SecurityConstants;

/**
 * 
 * ValidateCodeType
 * 
 */
public enum ValidateCodeType {

	SMS {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};

	/**
	 * 校验时从请求中获取的参数的名字
	 * 
	 * @return
	 */
	public abstract String getParamNameOnValidate();

}
