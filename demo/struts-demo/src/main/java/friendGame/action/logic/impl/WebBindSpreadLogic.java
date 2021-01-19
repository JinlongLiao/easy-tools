package friendGame.action.logic.impl;

import static friendGame.action.msg.MsgType.WEB_BIND_SPREAD;

import friendGame.action.annotation.RequestMsgType;
import friendGame.action.logic.ILogic;
import friendGame.action.msg.IResponse;
import friendGame.action.msg.request.WebBindSpreadMsg;


@RequestMsgType(msgType = WEB_BIND_SPREAD)
public class WebBindSpreadLogic extends ILogic<WebBindSpreadMsg> {


    @Override
    public IResponse handle(WebBindSpreadMsg msg, String clientIp) {
        return null;

    }

}
