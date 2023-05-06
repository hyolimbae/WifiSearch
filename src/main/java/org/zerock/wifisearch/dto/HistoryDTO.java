package org.zerock.wifisearch.dto;

import lombok.*;

import java.time.LocalDateTime;


@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO
{
    private int id;
    private double lnt;
    private double lat;
    private LocalDateTime date;
}
