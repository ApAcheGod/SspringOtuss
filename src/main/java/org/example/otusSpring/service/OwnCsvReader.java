package org.example.otusSpring.service;

import java.io.IOException;
import java.util.List;

public interface OwnCsvReader<T> {

    List<T> getData(String path, Class<?> T) throws IOException;

}
