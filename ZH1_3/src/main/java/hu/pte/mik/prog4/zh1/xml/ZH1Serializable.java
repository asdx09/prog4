package hu.pte.mik.prog4.zh1.xml;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZH1Serializable {
    String text() default "";
}
