package com.demojpaapp.configurations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.inject.Qualifier;


@Qualifier
@Retention(RUNTIME)
@Target({FIELD, METHOD, TYPE, PARAMETER })
public @interface PropertyQualifier {
}
