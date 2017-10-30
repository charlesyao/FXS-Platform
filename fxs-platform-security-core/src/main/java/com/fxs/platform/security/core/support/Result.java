package com.fxs.platform.security.core.support;

/**
 * 
 * @author Charles
 *
 */
public class Result {

    @SuppressWarnings("rawtypes")
	private static final ResponseMessage RESPONSE_MESSAGE_SUCCESS = new ResponseMessage(ResponseMessageCodeEnum.SUCCESS.getCode(), "");

    @SuppressWarnings("rawtypes")
	public static ResponseMessage success() {
        return RESPONSE_MESSAGE_SUCCESS;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> ResponseMessage<T> success(T t) {
        return new ResponseMessage(ResponseMessageCodeEnum.SUCCESS.getCode(), "", t);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> ResponseMessage<T> success(String message, T t) {
        return new ResponseMessage(ResponseMessageCodeEnum.SUCCESS.getCode(), message, t);
    }

    @SuppressWarnings("rawtypes")
	public static ResponseMessage error() {
        return error("");
    }

    @SuppressWarnings("rawtypes")
	public static ResponseMessage error(String message) {
        return error(ResponseMessageCodeEnum.ERROR.getCode(), message);
    }

    @SuppressWarnings("rawtypes")
	public static ResponseMessage error(String code, String message) {
        return error(code, message, null);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> ResponseMessage<T> error(String code, String message, T t) {
        return new ResponseMessage(code, message, t);
    }
}
