package sg.partying.lcb.android.config;


import java.util.ArrayList;
import java.util.List;

public class ConfigProvider implements IConfigProvider {
    private List<IConfigProvider> mProviderList = new ArrayList<>();

    private void registerConfigProvider() {
        if (mProviderList.isEmpty()) {
            mProviderList.add(new NetworkConfigProvider());
        }
    }

    private void releaseConfigProvider() {
        mProviderList.clear();
    }

    @Override
    public void beforeInit() {
        for (IConfigProvider provider : mProviderList) {
            provider.beforeInit();
        }
        registerConfigProvider();
    }

    @Override
    public void initDebug() {
        for (IConfigProvider provider : mProviderList) {
            provider.initDebug();
        }
    }

    @Override
    public void initAlpha() {
        for (IConfigProvider provider : mProviderList) {
            provider.initAlpha();
        }
    }

    @Override
    public void initProduct() {
        for (IConfigProvider provider : mProviderList) {
            provider.initProduct();
        }
    }

    @Override
    public void afterInit() {
        for (IConfigProvider provider : mProviderList) {
            provider.afterInit();
        }
        releaseConfigProvider();
    }
}
