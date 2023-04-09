package it.discovery.mongo.service;

import it.discovery.mongo.BaseMongoTest;
import org.apache.commons.compress.utils.IOUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BinaryServiceTest extends BaseMongoTest {

    @Autowired
    BinaryService binaryService;

    @Test
    void load_binaryFileExists_success() throws IOException {
        ObjectId objectId = binaryService.save(Paths.get("src/test/resources/sample.txt"), "1");
        assertNotNull(objectId);

        InputStream inputStream = binaryService.load(objectId.toHexString());
        assertNotNull(inputStream);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        assertEquals("Mongo!", new String(bytes));
    }
}