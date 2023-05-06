package org.zerock.wifisearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryVO
{
    private long id;
    private double lnt;
    private double lat;
    private LocalDateTime date;
}
