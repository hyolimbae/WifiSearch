package org.zerock.wifisearch.dto;

import lombok.*;

import java.util.List;

@ToString
@Builder
@Data
public class ApiDTO
{
    private String list_total_count;
    private ResultDTO RESULT;
    private List<RowDTO> row;
}
