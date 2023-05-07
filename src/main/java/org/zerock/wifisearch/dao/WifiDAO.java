package org.zerock.wifisearch.dao;

import com.google.gson.Gson;
import lombok.Cleanup;
import org.zerock.wifisearch.api.WifiApi;
import org.zerock.wifisearch.domain.WifiVO;
import org.zerock.wifisearch.dto.DbDTO;
import org.zerock.wifisearch.util.CommonUtil;
import org.zerock.wifisearch.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WifiDAO
{
    /* Data 를 DB에 저장 */
    public void create() throws Exception
    {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        System.out.println("Database 연결 성공");

        String sql = "insert into tbl_wifi(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm,\n" +
                "                 x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor,\n" +
                "                 x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se,\n" +
                "                 x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door,\n" +
                "                 x_swifi_remars3, lat, lnt, work_dttm)\n" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("SQL Query 입력 완료");

        // 데이터 요청은 한번에 최대 1000건을 넘을 수 없다.
        int processMax = 999;
        int start = 0;
        int end = processMax;
        int current = 0;

        while(true)
        {
            // Okhttp 를 통해 Start-End 까지의 와이파이 정보 json format 으로 저장
            Gson gson = new Gson();
            String jsonString = WifiApi.getWifiData(start,end);
            DbDTO mainDTO = gson.fromJson(jsonString, DbDTO.class);

            int total = Integer.parseInt(mainDTO.getTbPublicWifiInfo().getList_total_count());
            WifiApi.totalWifiDataCount = total;

            boolean IsFinished = false;
            for (int i = 0; i < processMax; ++i)
            {
                preparedStatement.setString(1, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_MGR_NO());
                preparedStatement.setString(2, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_WRDOFC());
                preparedStatement.setString(3, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_MAIN_NM());
                preparedStatement.setString(4, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_ADRES1());
                preparedStatement.setString(5, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_ADRES2());
                preparedStatement.setString(6, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_INSTL_FLOOR());
                preparedStatement.setString(7, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_INSTL_TY());
                preparedStatement.setString(8, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_INSTL_MBY());
                preparedStatement.setString(9, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_SVC_SE());
                preparedStatement.setString(10, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_CMCWR());
                preparedStatement.setString(11, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_CNSTC_YEAR());
                preparedStatement.setString(12, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_INOUT_DOOR());
                preparedStatement.setString(13, mainDTO.getTbPublicWifiInfo().getRow().get(i).getX_SWIFI_REMARS3());
                preparedStatement.setDouble(14, mainDTO.getTbPublicWifiInfo().getRow().get(i).getLNT());    // LAT <-> LON 바꿔서 들어오고 있으므로
                preparedStatement.setDouble(15, mainDTO.getTbPublicWifiInfo().getRow().get(i).getLAT());
                preparedStatement.setString(16, mainDTO.getTbPublicWifiInfo().getRow().get(i).getWORK_DTTM());
                preparedStatement.executeUpdate();

                if (++current >= total)
                {
                    IsFinished = true;
                    break;
                }
            }

            if (IsFinished)
                break;

            start += processMax;
            end += processMax;
        }

    }

    /* 주어진 위도, 경도와 가까운 Data 20개를 리턴 */
    public ArrayList<WifiVO> FindClosestWifi(double latitude, double longitude) throws Exception {
        ArrayList<WifiVO> newList = new ArrayList<>();

        // Distance (km) = acos(sin(lat1)*sin(lat2)+cos(lat1)*cos(lat2)*cos(lon2-lon1)) * 6371

       String sql = "select * " +
                ", format((6371 * acos(sin(radians(" + latitude + ")) * sin(radians(lat))" +
                "+ cos(radians(" + latitude + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + longitude + ")))) " +
                ", 4) as distance " +
                " from tbl_wifi " +
                " order by distance" +
                " limit 20";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            double calculatedDistance = CommonUtil.GetDistance(latitude,longitude,resultSet.getDouble("LAT"),resultSet.getDouble("LNT"));
            WifiVO newData = WifiVO.builder()
                    .distance(calculatedDistance)
                    .X_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"))
                    .X_SWIFI_WRDOFC(resultSet.getString("X_SWIFI_WRDOFC"))
                    .X_SWIFI_MAIN_NM(resultSet.getString("X_SWIFI_MAIN_NM"))
                    .X_SWIFI_ADRES1(resultSet.getString("X_SWIFI_ADRES1"))
                    .X_SWIFI_ADRES2(resultSet.getString("X_SWIFI_ADRES2"))
                    .X_SWIFI_INSTL_FLOOR(resultSet.getString("X_SWIFI_INSTL_FLOOR"))
                    .X_SWIFI_INSTL_TY(resultSet.getString("X_SWIFI_INSTL_TY"))
                    .X_SWIFI_INSTL_MBY(resultSet.getString("X_SWIFI_INSTL_MBY"))
                    .X_SWIFI_SVC_SE(resultSet.getString("X_SWIFI_SVC_SE"))
                    .X_SWIFI_CMCWR(resultSet.getString("X_SWIFI_CMCWR"))
                    .X_SWIFI_CNSTC_YEAR(resultSet.getString("X_SWIFI_CNSTC_YEAR"))
                    .X_SWIFI_INOUT_DOOR(resultSet.getString("X_SWIFI_INOUT_DOOR"))
                    .X_SWIFI_REMARS3(resultSet.getString("X_SWIFI_REMARS3"))
                    .LNT(resultSet.getDouble("LNT"))
                    .LAT(resultSet.getDouble("LAT"))
                    .build();

            newList.add(newData);
        }

        return newList;
    }
}
