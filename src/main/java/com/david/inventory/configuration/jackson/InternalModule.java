package com.david.inventory.configuration.jackson;

import com.david.inventory.configuration.jackson.codecs.*;
import com.david.inventory.domain.*;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class InternalModule extends SimpleModule {

    private static final String NAME = "InternalModule";

    public InternalModule() {
        super(NAME, Version.unknownVersion());

        addSerializer(ProductId.class, new ProductIdParser.Serializer());
        addDeserializer(ProductId.class, new ProductIdParser.Deserializer());

        addSerializer(ProductName.class, new ProductNameParser.Serializer());
        addDeserializer(ProductName.class, new ProductNameParser.Deserializer());

        addSerializer(ProductQuantity.class, new ProductQuantityParser.Serializer());
        addDeserializer(ProductQuantity.class, new ProductQuantityParser.Deserializer());

        addSerializer(ProductCategory.class, new ProductCategoryParser.Serializer());
        addDeserializer(ProductCategory.class, new ProductCategoryParser.Deserializer());

        addSerializer(ProductDescription.class, new ProductDescriptionParser.Serializer());
        addDeserializer(ProductDescription.class, new ProductDescriptionParser.Deserializer());
    }
}
