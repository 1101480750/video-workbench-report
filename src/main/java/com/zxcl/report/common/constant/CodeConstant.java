package com.zxcl.report.common.constant;

/**
 * 错误码常量
 *
 * @author YAZUO)WU-TONG
 */
public interface CodeConstant {

    /** 成功 */
    String SUCCESS = "10000";

    /** 未知错误 */
    String FATAL_UNKNOWN = "602001001001";

    /** 配置错误 */
    String ERROR_CONFIG = "606001001002";

    /** 业务错误 */
    String ERROR_BUSINESS = "603001001003";

    /** 参数错误 */
    String ERROR_PARAMETER = "603001003004";

    /** 空指针异常:NullPointerException */
    String FATAL_NULL_POINTER = "602001100001";

    /** 计算异常:ArithmeticException */
    String FATAL_ARITHMETIC = "602001100002";

    /** 类型转换异常:ClassCastException */
    String FATAL_CLASS_CAST = "602001100003";

    /** 集合负数异常:NegativeArraySizeException */
    String FATAL_NEGATIVE_ARRAY_SIZE = "602001100004";

    /** 集合超出范围异常:ArrayIndexOutOfBoundsException */
    String FATAL_ARRAY_INDEX_OUT_OF_BOUNDS = "602001100005";

    /** 文件未找到异常:FileNotFoundException */
    String FATAL_FILE_NOT_FOUND = "602001100006";

    /** 数字格式异常:NumberFormatException */
    String FATAL_NUMBER_FORMAT = "602001100007";

    /** SQL异常:SQLException */
    String FATAL_SQL = "604001100008";

    /** 读写异常:IOException */
    String FATAL_IO = "602001100009";

    /** 方法未找到异常:NoSuchMethodException */
    String FATAL_NO_SUCH_METHOD = "602001100010";

    /** 请求方法未找到异常:NoSuchRequestHandlingMethodException */
    String FATAL_NO_SUCH_REQUEST_HANDLING_METHOD = "602001100011";

    /** 请求方法不支持异常:HttpRequestMethodNotSupportedException */
    String FATAL_HTTP_REQUEST_METHOD_NOT_SUPPORTED = "602001100012";

    /** 请求类型不支持异常:HttpMediaTypeNotSupportedException */
    String FATAL_HTTP_MEDIA_TYPE_NOT_SUPPORTED = "602001100013";

    /** 请求类型不接受异常:HttpMediaTypeNotAcceptableException */
    String FATAL_HTTP_MEDIA_TYPE_NOT_ACCEPTABLE = "602001100014";

    /** 缺失路径变量异常:MissingPathVariableException */
    String FATAL_MISSING_PATH_VARIABLE = "602001100015";

    /** 缺失Servlet请求参数异常:MissingServletRequestParameterException */
    String FATAL_MISSING_SERVLET_REQUEST_PARAMETER = "602001100016";

    /** Servlet请求绑定异常:ServletRequestBindingException */
    String FATAL_REQUEST_BINDING = "602001100017";

    /** 转换不支持异常:ConversionNotSupportedException */
    String FATAL_CONVERSION_NOT_SUPPORTED = "602001100018";

    /** 类型不匹配异常:TypeMismatchException */
    String FATAL_TYPE_MISMATCH = "602001100019";

    /** HttpMessage不可读异常:HttpMessageNotReadableException */
    String FATAL_HTTP_MESSAGE_NOT_READABLE = "602001100020";

    /** HttpMessage不可写异常:HttpMessageNotWritableException */
    String FATAL_HTTP_MESSAGE_NOT_WRITABLE = "602001100021";

    /** 参数校验无效异常:MethodArgumentNotValidException */
    String FATAL_METHOD_ARGUMENT_NOT_VALID = "603001100022";

    /** 缺失Servlet请求异常:MissingServletRequestPartException */
    String FATAL_MISSING_SERVLET_REQUEST_PART = "602001100023";

    /** 绑定异常:BindException */
    String FATAL_BIND = "602001100024";

    /** 找不到处理器异常:NoHandlerFoundException */
    String FATAL_NO_HANDLER_FOUND = "602001100025";

    /** 异步请求超时异常:AsyncRequestTimeoutException */
    String FATAL_ASYNC_REQUEST_TIMEOUT = "602001100026";

    /** 服务调用客户端异常:ClientException */
    String FATAL_NETFLIX_CLIENT = "602001100027";

    /** 服务调用重试异常:RetryableException */
    String FATAL_NETFLIX_RETRYABLE = "602001100028";

    /** 服务调用重试异常:RestClientResponseException */
    String FATAL_REST_CLIENT_RESPONSE = "602001100029";

    /** Zuul网关异常:com.netflix.zuul.exception.ZuulException */
    String ERROR_ZUUL_GATEWAY = "602001100030";
}
