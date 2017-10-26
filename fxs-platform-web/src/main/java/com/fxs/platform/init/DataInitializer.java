package com.fxs.platform.init;

/**
 * 
 * @author Charles
 *
 */
public interface DataInitializer {

	/**
	 * Initialize index
	 * 
	 * @return
	 */
	Integer getIndex();

	void init() throws Exception;

}
