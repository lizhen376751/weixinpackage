package service.thirdservice.aes;

/**
 *
 */
public class AesException extends Exception {
    /**
     *
     */
    public static final int OK = 0;
    /**
     *
     */
    public static final int VALIDATESIGNATUREERROR = -40001;
    /**
     *
     */
    public static final int PARSEXMLERROR = -40002;
    /**
     *
     */
    public static final int COMPUTESIGNATUREERROR = -40003;
    /**
     *
     */
    public static final int ILLEGALAESKEY = -40004;
    /**
     *
     */
    public static final int VALIDATEAPPIDERROR = -40005;
    /**
     *
     */
    public static final int ENCRYPTAESERROR = -40006;
    /**
     *
     */
    public static final int DECRYPTAESERROR = -40007;
    /**
     *
     */
    public static final int ILLEGALBUFFER = -40008;
//public final static int EncodeBase64Error = -40009;
//public final static int DecodeBase64Error = -40010;
//public final static int GenReturnXmlError = -40011;
    /**
     *
     */
    private int code;

    AesException(int code) {
        super(getMessage(code));
        this.code = code;
    }

    /**
     * 错误码解析
     *
     * @param code 错误码
     * @return 错误字
     */
    private static String getMessage(int code) {
        switch (code) {
            case VALIDATESIGNATUREERROR:
                return "签名验证错误";
            case PARSEXMLERROR:
                return "xml解析失败";
            case COMPUTESIGNATUREERROR:
                return "sha加密生成签名失败";
            case ILLEGALAESKEY:
                return "SymmetricKey非法";
            case VALIDATEAPPIDERROR:
                return "appid校验失败";
            case ENCRYPTAESERROR:
                return "aes加密失败";
            case DECRYPTAESERROR:
                return "aes解密失败";
            case ILLEGALBUFFER:
                return "解密后得到的buffer非法";
//case EncodeBase64Error:
//return "base64加密错误";
//case DecodeBase64Error:
//return "base64解密错误";
//case GenReturnXmlError:
//return "xml生成失败";
            default:
                return null; // cannot be
        }
    }

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public int getCode() {
        return code;
    }

}
