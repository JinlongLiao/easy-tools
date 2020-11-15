import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class ObjectTest {
    @Test
    public void test1() {
        final Object a = new A();
        final ClassLayout classLayout = ClassLayout.parseInstance(a);
        System.out.println(classLayout.toPrintable());
    }

}

class A {
    private String name;
    private Integer age;
    private Boolean sex;
}
