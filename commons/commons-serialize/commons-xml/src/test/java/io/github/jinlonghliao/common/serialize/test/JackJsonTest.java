package io.github.jinlonghliao.common.serialize.test;

import io.github.jinlonghliao.common.serialize.XmlParserUtils;
import org.junit.Assert;
import org.junit.Test;


public class JackJsonTest {
    public static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <Student><id>22</id><name>jj</name><age>232</age></Student>";


    @Test
    public void test1() throws Exception {
        final Student student = new Student();
        student.setAge(232);
        student.setId("22");
        student.setName("jj");
        final String xml = XmlParserUtils.getJackSonXmlParser().java2xml(student);
        System.out.println("xml = " + xml);
    }

    @Test
    public void test2() throws Exception {
        final Student student = XmlParserUtils
                .getJackSonXmlParser()
                .xml2java(xml, Student.class);
        Assert.assertEquals("EQ", student.getId(), "22");
    }

}
