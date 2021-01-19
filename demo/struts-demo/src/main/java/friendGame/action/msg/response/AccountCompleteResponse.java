package friendGame.action.msg.response;

import friendGame.action.annotation.RequestMsgType;
 import friendGame.action.msg.IResponse;
import friendGame.action.msg.MsgType;

@RequestMsgType(msgType = MsgType.HANDLE_ACCOUNT_COMPLETE)
public class AccountCompleteResponse extends IResponse {

	public AccountCompleteResponse (int ret) {
		super(ret);
	}
	

	@Override
	public String generateResponse() {
	 ;
		return "";
	}

}
