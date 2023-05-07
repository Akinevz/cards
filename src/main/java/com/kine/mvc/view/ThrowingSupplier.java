package com.kine.mvc.view;

@FunctionalInterface
interface ThrowingSupplier<R> {

    R get() throws Exception;
}