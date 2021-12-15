package com.david.inventory.configuration.jackson.codecs;

import com.david.inventory.domain.ProductCategory;
import com.david.inventory.domain.ProductId;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductCategoryParser {

    public static class Serializer extends JsonSerializer<ProductCategory> {

        @Override
        public void serialize(ProductCategory value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class Deserializer extends JsonDeserializer<ProductCategory> {
        @Override
        public ProductCategory deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return new ProductCategory(p.getValueAsString());
        }
    }

}
