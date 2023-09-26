package sg.partying.lcb.android.config;

public class AppConfig {
    public enum AppMode {
        Debug, Alpha, Product
    }

    private static IConfigProvider sConfigProvider;

    public static void init(IConfigProvider configProvider) {
        sConfigProvider = configProvider;
        if (sConfigProvider == null) {
            throw new RuntimeException("you must setProvider first");
        }
        AppMode appMode = AppMode.Debug;
        sConfigProvider.beforeInit();
        switch (appMode) {
            case Debug:
                sConfigProvider.initDebug();
                break;
            case Alpha:
                sConfigProvider.initAlpha();
                break;
            default:
                sConfigProvider.initProduct();
                break;
        }
        sConfigProvider.afterInit();
    }
}
