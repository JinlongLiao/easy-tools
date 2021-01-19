package friendGame.action.msg.request;

import friendGame.action.annotation.FieldDef;
import friendGame.action.annotation.RequestMsgType;
import friendGame.action.msg.IRequest;
import friendGame.action.msg.MsgType;

@RequestMsgType(msgType = MsgType.HANDLE_ACCOUNT_COMPLETE)
public class AccountCompleteMsg extends IRequest {

    @FieldDef(fieldLength = 20)
    private String account;
    @FieldDef(fieldLength = 32)
    private String password;
    @FieldDef(fieldLength = 12)
    private String mobile;
    @FieldDef(fieldLength = 12)
    private String validCode;
    private int operateType;
    //是否需要领取绑定奖励
    private int ifGetAward;

    /*
     * @see friendGame.action.msg.IRequest#getOridinal()
     */
    @Override
    public byte[] getOriginal() {
        return null;
    }

    /*
     * @see friendGame.action.msg.IRequest#toString()
     */
    @Override
    public String toString() {

        return super.getSuperToString() + ", " + account + ", " +
                password + ", " + mobile + ", " + validCode + ", " +
                operateType + "," + ifGetAward + "]";
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public int getIfGetAward() {
        return ifGetAward;
    }

    public void setIfGetAward(int ifGetAward) {
        this.ifGetAward = ifGetAward;
    }

}
