package it.discovery.mongo.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class BinaryService {

    private final GridFsTemplate gridFsTemplate;

    public InputStream load(String objectId) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(objectId)));
        if (file == null) {
            return null;
        }

        GridFsResource resource = gridFsTemplate.getResource(file);
        return resource.getInputStream();
    }

    public ObjectId save(Path path, String ownerId) throws IOException {
        return gridFsTemplate.store(Files.newInputStream(path),
                path.getFileName().toString(),
                Files.probeContentType(path),
                Map.of("ownerId", Optional.ofNullable(ownerId).orElse("")));
    }

    public void delete(String objectId) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(objectId)));
    }
}
