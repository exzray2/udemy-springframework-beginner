package com.exzray.spring6restmvc.service;

import com.exzray.spring6restmvc.data.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface CSVService {
    List<BeerCSVRecord> convertCSV(File file);
}
