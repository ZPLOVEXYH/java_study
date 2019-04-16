package com.java.oracle.study.java_study.lamdbas.expression;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface Processor {
    String process(Callable<String> c) throws Exception;

    String process(Supplier<String> supplier);

    String processSup(Supplier<String> s);
}
