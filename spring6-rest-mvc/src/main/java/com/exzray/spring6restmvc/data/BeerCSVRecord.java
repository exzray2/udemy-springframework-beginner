package com.exzray.spring6restmvc.data;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BeerCSVRecord {
    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "quantity")
    private Integer quantity;

    @CsvBindByName(column = "price")
    private Double price;
}
