package org.zerock.wifisearch.util;

public class CommonUtil
{
    /* (lat1,long1) 과 (lat2, long2) 사이의 직선 거리 (km) 를 구하는 함수 */
    public static double GetDistance(double lat1, double long1, double lat2, double long2)
    {
        // Distance (km) = acos(sin(lat1)*sin(lat2)+cos(lat1)*cos(lat2)*cos(lon2-lon1)) * 6371

        double rLat1 = Math.toRadians(lat1);
        double rLat2 = Math.toRadians(lat2);
        double rLong1 = Math.toRadians(long1);
        double rLong2 = Math.toRadians(long2);

        System.out.println("lat1: " + lat1);
        System.out.println("lat2: " + lat2);
        System.out.println("long1: " + long1);
        System.out.println("long2: " + long2);

        return 6371.0f * Math.acos((Math.sin(rLat1) * Math.sin(rLat2)) + Math.cos(rLat1) * Math.cos(rLat2) * Math.cos(rLong2 - rLong1));
    }
}
