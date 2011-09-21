package com.lyh.licenseworkflow.system;

/**
 * 运行时异常
 *
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-21上午11:07
 * @Email liuyuhui007@gmail.com
 */
public class OceanRuntimeException extends RuntimeException {
    /**
     * Out to client abnormal need a errorcode error code
     */
    private String errorCode;

    /**
     * The exception is thrown to the client the required parameters
     */
    private Object[] params;

    /**
     * Need to display to the interface parameters, taking into account most of the error message of the need for a parameter,
     * so add this simple argument, if there are multiple parameters, use the array params
     */
    private String errorParam;

    /**
     * @param message    Message
     * @param errorParam Error Parameter
     */
    public OceanRuntimeException(String message, String errorParam) {
        super(message);
        this.errorParam = errorParam;
    }

    public OceanRuntimeException(String errorCode, Object[] params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    /**
     * @param errorCode Error code
     */
    public OceanRuntimeException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param message Exception Summary Information
     * @param cause   Root exception
     */
    public OceanRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }


    public String getErrorParam() {
        return errorParam;
    }


    public void setErrorParam(String errorParam) {
        this.errorParam = errorParam;
    }
}
