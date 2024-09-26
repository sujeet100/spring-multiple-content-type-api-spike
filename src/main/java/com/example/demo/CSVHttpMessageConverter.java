package com.example.demo;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class CSVHttpMessageConverter implements HttpMessageConverter<CSVSerializable> {
    MediaType TEXT_CSV = new MediaType("text", "csv");
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return TEXT_CSV.equals(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return TEXT_CSV.equals(mediaType);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(TEXT_CSV);
    }

    @Override
    public CSVSerializable read(Class<? extends CSVSerializable> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(CSVSerializable o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Writer writer = new StringWriter();
        outputMessage.getHeaders().setContentType(contentType);
        outputMessage.getHeaders().add("Content-Disposition","attachment; filename=\"foo.csv\"");
        StatefulBeanToCsv<CSVBean> beanToCsv = new StatefulBeanToCsvBuilder<CSVBean>(writer).build();
        try {
            beanToCsv.write(o.toCSV());
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
        writer.close();
        outputMessage.getBody().write(writer.toString().getBytes());
    }
}
