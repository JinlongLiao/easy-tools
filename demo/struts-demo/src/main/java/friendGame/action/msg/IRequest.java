package friendGame.action.msg;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import friendGame.action.annotation.FieldDef;

/**
 * 抽象消息
 *
 * @author caohuihui
 */
public abstract class IRequest {
    /**
     * 签名密钥
     */
    private String signature;
    /**
     * 消息类型
     */
    private int msgType;
    /**
     * 时间戳
     */
    private int timestamp;
    /**
     * MAC信息
     */
    @FieldDef(fieldLength = 40)
    private String mac;
    /**
     * 平台信息 0:pc 1:安卓 2:IOS
     */
    private int platform;
    /**
     * 渠道号
     */
    private int agentId;

    /**
     * 版本号
     */
    @FieldDef(fieldLength = 16)
    private String versionNum;
    /**
     * 用户Id
     */
    private int userId;
    /**
     * 用户Token
     */
    @FieldDef(fieldLength = 32)
    private String userToken;
    /**
     * 省份标识符，某个玩法地区ID
     */
    private int pid;
    /**
     * 用户登录信息，由客户端参数解析后赋值
     */

    private int gameGroupId;

    private int period;

    /**
     * 获取加密源串
     *
     * @return
     */
    public abstract byte[] getOriginal();

    /**
     * 重新ToString
     */
    @Override
    public abstract String toString();

    /**
     * 获取父类的字符串
     *
     * @return
     */
    protected String getSuperToString() {
        return "[" + Integer.toHexString(msgType) + "," + mac + "," + platform + ","
                + agentId + "," + versionNum + "," + userId + "," + userToken + "," + pid;
    }

    private static final String oridinalSuffix = "stpx1!#s0f8p6v5sdq";

    /**
     * 生成签名密钥
     *
     * @return
     */
    public String generateSignature() {
        byte[] oridinal = getOriginal();
        byte[] original = new byte[oridinal.length + 18];
        System.arraycopy(oridinal, 0, original, 0, oridinal.length);
        byte[] keys = oridinalSuffix.getBytes();
        for (int index = 0; index < keys.length; index++) {
            original[oridinal.length + index] = keys[index];
        }

        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(original);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        String signature = new String(encodeHex(msgDigest.digest()));
        return signature;
    }

    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 十六进制编码
     *
     * @param data
     * @return
     */
    protected char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }


    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getGameGroupId() {
        return gameGroupId;
    }

    public void setGameGroupId(int gameGroupId) {
        this.gameGroupId = gameGroupId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

}
