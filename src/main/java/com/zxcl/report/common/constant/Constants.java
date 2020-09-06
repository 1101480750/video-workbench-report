package com.zxcl.report.common.constant;

/**
 * @author shiding
 */
public final class Constants {

	/**
	 * 供应商移动端——管理员角色
	 */
	public static final Integer SUPPLIER_ROLE_SUPER = 1;

	/**
	 * 供应商移动端——默认密码
	 */
	public static final String DEFAULT_USER_PASSWORD = "123456";

	/**
	 * 供应商移动端——接单员角色
	 */
	public static final Integer SUPPLIER_ROLE_AVERAGE = 2;

	public static final Integer RECEIVE_UNACCESS = 0;

	public static final Integer RECEIVE_ACCESS= 1;

	public static final Integer RECEIVE_DELIVER = 2;

	public static final int ZERO = 0;

	public static final Integer RECEIVE_ONE_NEGATIVE = -1;

	/**
	 * 乐观锁循环次数
	 */
	public static final Integer HAPPY_LOCK_COUNT = 10;
	/**
	 * 循环间隔时间
	 */
	public static final Integer HAPPY_LOCK_LOOP_TIME = 5;
	public static final Integer SUPPLIERGROUP_ZERO = 0;
	public static final Integer SUPPLIERGROUP_ONE = 1;
	public static final Integer SUPPLIERGROUP_TWO = 2;
	public static final Integer SUPPLIERGROUP_THREE = 3;
}
