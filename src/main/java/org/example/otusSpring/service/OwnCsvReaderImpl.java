package org.example.otusSpring.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OwnCsvReaderImpl<T> implements OwnCsvReader<T> {

    private final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
    private final CsvMapper mapper = new CsvMapper();
    private ResourceLoader resourceLoader;


    public OwnCsvReaderImpl() {}

    private <T> MappingIterator<T> getIterator(File file, Class<?> T) throws IOException {
                return mapper
                        .reader(T)
                        .with(bootstrapSchema)
                        .readValues(file);
    }

    public List<T> getData(String path, Class<?> T) throws IOException {
        List<T> values = new ArrayList<T>();
        MappingIterator<T> iterator = getIterator(getFile(path), T);
        while(iterator.hasNext()){
            values.add(iterator.next());
        }
        return values;
    }

    private Resource getResource(String path) {
        return resourceLoader.getResource("classpath:" + path);
    }

    private File getFile(String path) throws IOException {
        return getResource(path).getFile();
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
