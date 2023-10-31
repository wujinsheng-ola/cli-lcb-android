package sg.olaparty.network.custom;

import com.blankj.utilcode.util.GsonUtils;
import com.salton123.log.XLog;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Time:2023/10/30 18:48
 * Author:
 * Description:
 */
public class CWireRequestBodyConverter<T extends Message<T, ?>> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");
    private static final String TAG = "CWireRequestBodyConverter";
    private final ProtoAdapter<T> adapter;

    CWireRequestBodyConverter(ProtoAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String params = GsonUtils.toJson(value);
        XLog.d(TAG, params);
        return RequestBody.create(MEDIA_TYPE, params);
    }
}
