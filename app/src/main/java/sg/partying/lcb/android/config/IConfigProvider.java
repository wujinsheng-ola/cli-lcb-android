package sg.partying.lcb.android.config;

public interface IConfigProvider {
    void beforeInit();
    void initDebug();

    void initAlpha();

    void initProduct();

    void afterInit();
}
