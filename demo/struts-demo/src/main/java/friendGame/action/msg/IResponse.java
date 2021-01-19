package friendGame.action.msg;

import java.lang.reflect.Field;


/**
 * 公共响应公共抽象
 */
public abstract class IResponse {

    private int ret;

    public IResponse() {

    }

    public IResponse(int ret) {
        this.ret = ret;
    }

    /**
     * 生成返回值
     *
     * @return
     */
    public abstract String generateResponse();

    /**
     * 生成返回值
     *
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public String generateJSONResponse() throws IllegalArgumentException, IllegalAccessException {
        return null;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("");
        try {
            buffer.append(this.getClass().getSimpleName());
            buffer.append(" [");

            Class<?> clazz = this.getClass();
            Field[] allFields = clazz.getDeclaredFields();

            for (Field fieldInfo : allFields) {
                buffer.append(" ");
                buffer.append(fieldInfo.getName());
                buffer.append("=");
                buffer.append(clazz.getMethod("get" + fieldInfo.getName().substring(0, 1).toUpperCase() + fieldInfo.getName().substring(1)).invoke(this, new Object[]{}));
                buffer.append(" ");
            }
            buffer.append(" ]");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }
}
