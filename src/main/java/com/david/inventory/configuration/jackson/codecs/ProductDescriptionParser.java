package com.david.inventory.configuration.jackson.codecs;

import com.david.inventory.domain.ProductDescription;
import com.david.inventory.domain.ProductId;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductDescriptionParser {

    public static class Serializer extends JsonSerializer<ProductDescription> {

        @Override
        public void serialize(ProductDescription value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class Deserializer extends JsonDeserializer<ProductDescription> {
        @Override
        public ProductDescription deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return new ProductDescription(p.getValueAsString());
        }
    }

}
