package sg.olaparty.network.custom;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Time:2023/10/30 18:47
 * Author:
 * Description:
 */
final class CWireResponseBodyConverter<T extends Message<T, ?>>
    implements Converter<ResponseBody, T> {
    private final ProtoAdapter<T> adapter;

    CWireResponseBodyConverter(ProtoAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return adapter.decode(value.source());
        } finally {
            value.close();
        }
    }
}
