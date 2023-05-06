package org.zerock.wifisearch.dto;

import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WifiDTO
{
    private double distance;
    private String x_SWIFI_MGR_NO;
    private String x_SWIFI_WRDOFC;
    private String x_SWIFI_MAIN_NM;
    private String x_SWIFI_ADRES1;
    private String x_SWIFI_ADRES2;
    private String x_SWIFI_INSTL_FLOOR;
    private String x_SWIFI_INSTL_TY;
    private String x_SWIFI_INSTL_MBY;
    private String x_SWIFI_SVC_SE;
    private String x_SWIFI_CMCWR;
    private String x_SWIFI_CNSTC_YEAR;
    private String x_SWIFI_INOUT_DOOR;
    private String x_SWIFI_REMARS3;
    private double lnt;
    private double lat;
    private String work_DTTM;
}
