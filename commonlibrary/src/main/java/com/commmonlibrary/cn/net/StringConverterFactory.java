package com.commmonlibrary.cn.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 解析成为字符串
 * Created by wei on 2018/8/25.
 */

public final class StringConverterFactory extends Converter.Factory {

    public static Converter.Factory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (type == String.class) {
            return StringResponseBodyConverter.INSTANCE;
        }
        return null;
    }

    static class StringResponseBodyConverter implements Converter<ResponseBody, String> {
        private static final StringResponseBodyConverter INSTANCE = new StringResponseBodyConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

}
