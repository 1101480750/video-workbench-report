package com.zxcl.report.common.constant;

/**
 * 错误码常量
 *
 * @author jiming.miao
 * @version 0.1 Date: 2018/9/28
 */
public interface MsgCdConstant {
    /**
     * 成功
     */
    String SUCCESS = "10000";

    /**
     * 登录模块
     */
    /**
     * 用户登录账号为空
     */
    String LOGIN_USER_ACCOUNT_EMPTY = "303021100001";

    /**
     * 用户登录密码为空
     */
    String LOGIN_USER_PASSWORD_EMPTY= "303021100002";

    /**
     * 用户信息不存在
     */
    String LOGIN_USER_INFO_EMPTY= "303021100003";

    /**
     * 用户密码错误
     */
    String LOGIN_USER_PASSWORD_ERROR= "303021100004";


    /**
     * 用户密码错误
     */
    String LOGIN_USER_CLIENT_ERROR= "303021100005";


    /**
     * 支付宝相关
     */
    /**
     * 支付宝鉴权失败
     */
    String ALIPAY_AUTHENTICATION_FAIL = "610021100000";

    /**
     * 用戶信息取得失败
     */
    String GET_USER_INFO_FAIL = "603021100238";

    /**
     * 二维码生成失败
     */
    String QC_PRODUCE_FAIL = "602021100004";

    /**
     * 供应商登录相关
     */
    /**
     * 联系电话不能为空
     */
    String TELEPHONE_IS_NOT_NONE = "303021100547";

    /**
     * 身份证半身像不能为空
     */
    String ID_BSX_IS_NOT_NONE = "303021100546";

    /**
     * 身份证反面不能为空
     */
    String ID_UN_FRONT_IS_NOT_NONE = "303021100545";

    /**
     * 身份证正面不能为空
     */
    String ID_FRONT_IS_NOT_NONE = "303021100544";

    /**
     * 身份证号不能为空
     */
    String ID_NUMBER_IS_NOT_NONE = "303021100543";

    /**
     * 营业执照不能为空
     */
    String BUSINESS_LICENSE_IS_NOT_NONE = "303021100542";

    /**
     * 信用代码不能为空
     */
    String CREDIT_CODE_IS_NOT_NONE = "303021100541";

    /**
     * 联系人不能为空
     */
    String CONTACTS_IS_NOT_NONE = "303021100540";

    /**
     * 供应商全称不能为空
     */
    String SUPPLIER_NAME_IS_NONE = "303021100539";

    /**
     * 验证码错误
     */
    String PROVING_CODE_ERROR = "303021100538";

    /**
     * 验证发送失败，请重新发送验证码
     */
    String PROVING_FAIL = "303021100537";
    /**
     * 主键不能为空或者0
     */
    String PK_MANDATORY = "303021100018";

    /**
     * 物资不能为空
     */
    String INVENTORY_CANNOT_NULL = "303021100163";

    /**
     * 物资ID不能为空
     */
    String MATERIAL_MANDATORY = "303021100274";

    /**
     * 用户不存在
     */
    String USER_IS_EXSIT = "303021100430";

    /**
     * 相同品牌，规格，单位的物资已存在
     */
    String MATERIAL_IS_EXISTED = "303021100448";

    /**
     * 您还未添加客户，请至“我的—我的客户”模块添加
     */
    String SUPPLIER_IS_NOT_EXIST = "303021100420";

    /**
     * 参数错误
     */
    String COMMON_PARAM_ERROR = "303021100019";

    /**
     * 更新失败
     */
    String UPDATE_FAIL = "603021100002";

    /**
     * 新增失败
     */
    String ADD_FAIL = "603021100001";

    /**
     * 找不到指定的对象
     */
    String COMMON_NOTFIND_ERROR = "303021100125";

    /**
     * 商户ID不能为空
     */
    String GROUPID_MANDATORY = "303021100022";

    /**
     * 该集团已申请通过，不可重复申请
     */
    String GROUP_IS_APPLIED = "303021100433";

    /**
     * 供应商已存在
     */
    String SUPPLIER_IS_EXISTED = "303021100432";

    /**
     * 供应商不能为空
     */
    String STKBILLSUPPLIERID_MANDATORY = "303021100054";

    /**
     * 手机号码已被使用
     */
    String SUPPLIER_EXIST_PHONE = "303021100443";


    /**
     * 已完成认证无法修改
     */
    String AUTH_IS_COMPIETED = "303021100437";


    /**
     * 传递参数不能为空
     */
    String PARAMPETER_MANDATORY = "303021100180";

    /**
     * 目标单据类型ID不能为空
     */
    String TOOBJECT_MANDATORY = "303021100038";

    /**
     * 指定的编码规则不存在
     */
    String RULE_NOTEXISTS = "303021100001";

    /**
     * 获取对象属性值时失败
     */
    String GETFIELDVALUE_ERROR = "303021100088";

    /**
     * 对应编码记录大于一条
     */
    String BASEBILLNO_MORETHANONE = "303021100013";

    /**
     * 获取订单号超时
     */
    String GETBILL_TIMEOUT = "303021100160";

    /**
     * 指定条件的单据编码记录存在多于一条
     */
    String RECORD_MORETHANONE = "303021100249";

    /**
     * 无可用订单！
     */
    String BILL_IS_INVALID = "303021100438";
    /**
     * 操作失败！
     */
    String OPERATE_FAIL = "303021100439";
    /**
     * 商户不存在
     */
    String GROUP_IS_NOT_EXISTED = "303021100434";
    /**
     * 报价时存在相同物资！
     */
    String SAME_MATERIAL_QUOTATION = "303021100641";
    /**
     * 物资在系统中不存在
     */
    String SCMINOUTMATERIALNOTFOUND_NOTFOUND = "303021100003";

    /**
     * 当前报价不可用，请重试后联系管理员
     */
    String QUOTATION_IS_FAIL = "303021100656";

    /**
     * 物资添加成功,以下物资重复:
     */
    String MATERIAL_REPEAT_OTHERMATERIAL_ADDSUC = "303021100663";
    /**
     * 物资已存在
     */
    String MATERIAL_IS_EXISTENCE = "303021100664";

    /**
     * 该订单已被接单
     */
    String ORDER_COVER_RECEIPT = "303021100688";

    /**
     * 该订单已被拣货
     */
    String ORDER_COVER_PICK = "303021100690";

    /**
     * 订单已发生变化，请及时刷新！
     */
    String ORDER_CHANGED_REFRESH = "303021100694";

    /**
     * 登录信息无效
     */
    String SUPPLIER_LOGIN_INVALID = "303021100689";

    /**
     * 指定的员工不存在，请重试或联系客服
     */
    String SUPPLIER_USER_NOT_EXIST = "303021100691";

    /**
     * 密码错误
     */
    String SUPPLIER_PASSWORD_ERROR = "303021100692";

    /**
     * 登录授权失败，请重新登录
     */
    String SUPPLIER_AUTH_FAIL = "303021100693";

    /**
     * 获取供应商信息失败
     */
    String SUPPLIER_INFO_FAIL = "303021100695";

    /**
     * 鉴权失败
     */
    String AUTH_FAIL = "303021100699";

    /**
     * 机构ID不能为空
     */
    String ORG_MANDATORY = "303021100023";


    /**
     * 消息推送使用
     * <p>
     * 你有1个{0}的{1}，{2}！
     */
    String MESSAGE_PUSH = "101021100024";

    /**
     * 该号码不是已登录的号码
     */
    String NUMBER_IS_NOT_LOGIN_NUMBER = "303021100711";

    /**
     * 密码不一致
     */
    String PASSWORD_NOT_SAME = "303021100710";

    /**
     * 不能设置为初始密码
     */
    String NOTSET_INITIAL_PASSWORD = "303021100712";


    /**
     * 供应商信息不能为空
     */
    String SUPPLIERID_MANDATORY = "303021100714";



    /**
     * 供应商集团id不能为空
     */
    String SUPPLIERGROUPID_MANDATORY = "303021100715";



    /**
     * 推送人员信息错误
     */
    String PUSH_USERINFO_ERROR = "303021100716";


    /**
     * 注册ID不能为空
     */
    String REGISTRATIONID_IS_NOT = "303021100717";


    /**
     * 用户id不能为空
     */
    String CURRID_IS_NOT_NULL = "303021100718";

    /**
     * 设备类型不能为空
     */
    String DEVICETYPE_IS_NOT_NULL = "303021100719";

    /**
     * 电话号码不能为空
     */
    String CUEETEL_IS_NOT_NULL = "303021100720";

    /**
     * 停用的人员不允许登录！
     */
    String DISABLE_LOGIN = "303021100726";

    /**
     * 密码长度在6位及以上，密码可包换数字，大小写字母，特殊字符等
     */
    String PASSWORDSIX_ERROR = "303021100727";

    /**
     * 密码长度最多15位及以下，密码可包换数字，大小写字母，特殊字符等
     */
    String PASSWORDFIFTEEN_ERROR = "303021100728";

    /**
     * 该人员已被删除
     */
    String USER_DELETE = "303021100733";

}
