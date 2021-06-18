package ua.com.alevel.financialsystem.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OperationJdbc {

    @CsvBindByName
    private Long id;
    @CsvBindByName
    private Integer total;
    @CsvBindByName
    private Instant date;
    @CsvBindByName
    private Long billId;
}