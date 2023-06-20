package com.exzray.spring6restmvc.service;

import com.exzray.spring6restmvc.data.BeerCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CSVServiceImpl implements CSVService {
    @Override
    public List<BeerCSVRecord> convertCSV(File file) {
        try {
            return new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(file))
                    .withType(BeerCSVRecord.class)
                    .build()
                    .parse();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
