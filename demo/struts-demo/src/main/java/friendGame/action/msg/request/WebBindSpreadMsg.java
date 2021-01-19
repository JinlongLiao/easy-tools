package friendGame.action.msg.request;

import static friendGame.action.msg.MsgType.WEB_BIND_SPREAD;


import friendGame.action.annotation.FieldDef;
import friendGame.action.annotation.RequestMsgType;
import friendGame.action.msg.IRequest;

@RequestMsgType(msgType = WEB_BIND_SPREAD)
public class WebBindSpreadMsg extends IRequest {

    /**
     * 52L
     */
    @FieldDef(fieldLength = 52)
    private String unionId;

    /**
     * 邀请人的userId
     */
    private int spreadId;

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
        return " ";
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public int getSpreadId() {
        return spreadId;
    }

    public void setSpreadId(int spreadId) {
        this.spreadId = spreadId;
    }
}
