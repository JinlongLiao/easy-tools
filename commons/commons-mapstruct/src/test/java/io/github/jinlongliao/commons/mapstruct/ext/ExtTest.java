package io.github.jinlongliao.commons.mapstruct.ext;


import io.github.jinlongliao.commons.mapstruct.BeanCopierUtils;
import org.junit.Test;

public class ExtTest {
    private Object[] data = new Object[]{new A(true), false};

    @Test
    public void convertTest() {
        B b = BeanCopierUtils.getData2Object(B.class).toArrayConverter(data);
        System.out.println(b);
    }

    // {
    //     io.github.jinlongliao.commons.mapstruct.ext.B tmp = new io.github.jinlongliao.commons.mapstruct.ext.B();
    //     tmp.setA(((io.github.jinlongliao.commons.mapstruct.ext.A) $1.get("a")));
    //     tmp.setB(io.github.jinlongliao.commons.mapstruct.core.InnerCoreDataConverter.getInt($1.get("b")));
    //     return tmp;
    // }
}
