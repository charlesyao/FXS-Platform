package com.fxs.platform.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fxs.platform.security.core.properties.SecurityProperties;
import com.fxs.platform.security.core.validate.code.image.ImageCodeGenerator;


/**
 * Extension configuration. 
 * Business system can override the default Bean definition
 * 
 * @author Charles
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * Imgae validate code generator
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator(); 
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}

}
