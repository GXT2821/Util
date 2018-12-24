package com.test.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtostuffUtil {
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    private static <T> Schema<T> getSchma(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }

    public static <T> byte[] serialize(T obj){
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try{
            Schema<T> schema = getSchma(clazz);
            return serializeInternal(obj, schema, buffer);
        }finally {
            buffer.clear();
        }
    }

    public static <T> T descrialize(byte[] bytes, Class<T> clazz){
        try{
            Schema<T> schema = getSchma(clazz);
            return deserializeInternal(bytes, schema.newMessage(), schema);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static <T> byte[] serializeInternal(T source, Schema<T> schema, LinkedBuffer buffer){
        return ProtobufIOUtil.toByteArray(source, schema, buffer);
    }

    private static <T> T deserializeInternal(byte[] bytes, T result, Schema<T> schema){
        ProtobufIOUtil.mergeFrom(bytes, result, schema);
        return result;
    }
}
