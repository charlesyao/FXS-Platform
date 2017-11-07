package com.fxs.platform.init;

public interface DataInitializer {

	/**
	 * Initialize index
	 * 
	 * @return
	 */
	Integer getIndex();

	void init() throws Exception;

}
