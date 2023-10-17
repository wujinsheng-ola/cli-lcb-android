package com.salton123.config;

public interface IConfigProvider {
    void beforeInit();
    void initDebug();

    void initAlpha();

    void initProduct();

    void afterInit();
}
