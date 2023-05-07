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
public class BookmarkVO
{
    private long id;
    private String name;
    private long priority;
    private LocalDateTime registerTime;
    private LocalDateTime modifiedTime;
    private String wifiName;
    private LocalDateTime wifiRegisteredTime;
}
