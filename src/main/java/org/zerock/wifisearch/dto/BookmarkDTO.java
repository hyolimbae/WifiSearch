package org.zerock.wifisearch.dto;

import lombok.*;
import java.time.LocalDateTime;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDTO
{
    private long id;
    private String name;
    private long priority;
    private LocalDateTime registerTime;
    private LocalDateTime modifiedTime;
    private LocalDateTime wifiRegisteredTime;
    private String wifiName;
}