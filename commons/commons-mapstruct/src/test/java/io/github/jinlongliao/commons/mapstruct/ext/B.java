package io.github.jinlongliao.commons.mapstruct.ext;

public class B {
    private A a;
    private Boolean as;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public Boolean getAs() {
        return as;
    }

    public void setAs(Boolean as) {
        this.as = as;
    }

    @Override
    public String toString() {
        return "B{" +
                "a=" + a +
                ", as='" + as + '\'' +
                '}';
    }
}
