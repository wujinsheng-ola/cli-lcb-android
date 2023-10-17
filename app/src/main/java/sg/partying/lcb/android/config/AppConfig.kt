package sg.partying.lcb.android.config;

import com.salton123.config.IConfigProvider;

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
        AppMode appMode = AppMode.Product;
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
