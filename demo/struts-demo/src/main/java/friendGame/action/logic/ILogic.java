package friendGame.action.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import friendGame.action.msg.IRequest;
import friendGame.action.msg.IResponse;

/**
 * 具体业务处理基本单元
 *
 * @author **
 * @since 2020/11/17 20:15
 */
public abstract class ILogic<T extends IRequest> {

    protected static Logger logger = LoggerFactory.getLogger(ILogic.class);

    /**
     * 处理具体的业务逻辑
     *
     * @param msg
     * @param clientIp
     * @return
     */
    public abstract IResponse handle(T msg, String clientIp);
}
