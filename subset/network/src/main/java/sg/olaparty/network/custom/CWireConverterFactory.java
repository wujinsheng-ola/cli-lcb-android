package sg.olaparty.network.custom;

import androidx.annotation.Nullable;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Time:2023/10/30 18:46
 * Author:
 * Description:
 */
public class CWireConverterFactory  extends Converter.Factory {
    public static CWireConverterFactory create() {
        return new CWireConverterFactory();
    }

    private CWireConverterFactory() {}

    @Override
    public @Nullable Converter<ResponseBody, ?> responseBodyConverter(
        Type type, Annotation[] annotations, Retrofit retrofit) {
        if (!(type instanceof Class<?>)) {
            return null;
        }
        Class<?> c = (Class<?>) type;
        if (!Message.class.isAssignableFrom(c)) {
            return null;
        }
        //noinspection unchecked
        ProtoAdapter<? extends Message> adapter = ProtoAdapter.get((Class<? extends Message>) c);
        return new CWireResponseBodyConverter<>(adapter);
    }

    @Override
    public @Nullable Converter<?, RequestBody> requestBodyConverter(
        Type type,
        Annotation[] parameterAnnotations,
        Annotation[] methodAnnotations,
        Retrofit retrofit) {
        if (!(type instanceof Class<?>)) {
            return null;
        }
        Class<?> c = (Class<?>) type;
        if (!Message.class.isAssignableFrom(c)) {
            return null;
        }
        //noinspection unchecked
        ProtoAdapter<? extends Message> adapter = ProtoAdapter.get((Class<? extends Message>) c);
        return new CWireRequestBodyConverter<>(adapter);
    }
}
