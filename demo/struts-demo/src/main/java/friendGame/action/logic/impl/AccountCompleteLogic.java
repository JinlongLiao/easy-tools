package friendGame.action.logic.impl;

import friendGame.action.annotation.RequestMsgType;
import friendGame.action.logic.ILogic;
import friendGame.action.msg.IResponse;
import friendGame.action.msg.MsgType;
import friendGame.action.msg.request.AccountCompleteMsg;
import friendGame.action.msg.response.AccountCompleteResponse;


/**
 * 哈灵帐号信息完善
 *
 * @author yuzhoujie
 */
@RequestMsgType(msgType = MsgType.HANDLE_ACCOUNT_COMPLETE)
public class AccountCompleteLogic extends ILogic<AccountCompleteMsg> {


    public AccountCompleteLogic() {

    }

    @Override
    public IResponse handle(AccountCompleteMsg msg, String clientIp) {

        return new AccountCompleteResponse(0);
    }

}
