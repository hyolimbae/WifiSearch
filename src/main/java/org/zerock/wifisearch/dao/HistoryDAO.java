package org.zerock.wifisearch.dao;

import lombok.Cleanup;
import org.zerock.wifisearch.domain.HistoryVO;
import org.zerock.wifisearch.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDAO
{
    /* 매개 VO 데이터 추가 */
    public void Insert(HistoryVO newData) throws Exception {
        String sql = "insert into tbl_history (LAT,LNT,RecordTime) values (?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getHistoryConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDouble(1,newData.getLat());
        preparedStatement.setDouble(2,newData.getLnt());
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();
    }

    /* ID 를 가진 데이터 삭제 */
    public void Delete(long ID) throws Exception {
        String sql = "delete from tbl_history where id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getHistoryConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1,ID);
        preparedStatement.executeUpdate();
    }

    public ArrayList<HistoryVO> GetHistoryList() throws Exception {
        ArrayList<HistoryVO> newList = new ArrayList<>();

        String sql = "select * from tbl_history order by id";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getHistoryConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            HistoryVO newData = HistoryVO.builder()
                    .id(resultSet.getInt("ID"))
                    .lnt(resultSet.getDouble("LNT"))
                    .lat(resultSet.getDouble("LAT"))
                    .date(resultSet.getTimestamp("RecordTime").toLocalDateTime())
                    .build();

            newList.add(newData);
        }

        return newList;
    }
}
