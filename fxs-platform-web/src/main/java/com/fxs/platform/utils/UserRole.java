/**
 * 
 */
package com.fxs.platform.utils;

/**
 * @author Charles
 *
 */
public enum UserRole {
	LITIGANT(0), LAWER(1), ADMIN(2);

	private int type;

	private UserRole(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
