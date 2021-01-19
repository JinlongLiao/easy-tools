package friendGame.action.msg.response;

import friendGame.action.annotation.RequestMsgType;
import friendGame.action.msg.IResponse;

import static friendGame.action.msg.MsgType.WEB_BIND_SPREAD;

@RequestMsgType(msgType = WEB_BIND_SPREAD)
public class WebBindSpreadResponse extends IResponse {

    public WebBindSpreadResponse(int ret) {
        super.setRet(ret);
    }

    @Override
    public String generateResponse() {

        return "hex";
    }
}
