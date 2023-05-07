package org.zerock.wifisearch.dao;

import lombok.Cleanup;
import org.zerock.wifisearch.domain.BookmarkVO;
import org.zerock.wifisearch.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookmarkDAO
{
    /* 매개 VO 데이터 추가 */
    public void Insert(BookmarkVO newData) throws Exception {
        String sql = "insert into tbl_bookmark (priority,name,registeredTime,modifiedTime) values (?,?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getBookmarkConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1,newData.getPriority());
        preparedStatement.setString(2,newData.getName());
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();
    }

    /* ID 를 가진 데이터 삭제 */
    public void Delete(long ID) throws Exception {
        String sql = "delete from tbl_bookmark where id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getBookmarkConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1,ID);
        preparedStatement.executeUpdate();
    }

    /* ID 를 가진 데이터 리턴 */
    public BookmarkVO Find(long ID) throws Exception {
        String sql = "select * from tbl_bookmark where id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getBookmarkConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1,ID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
        {
            BookmarkVO newData = BookmarkVO.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .priority(resultSet.getInt("priority"))
                    .registerTime(resultSet.getTimestamp("registeredTime").toLocalDateTime())
                    .modifiedTime(resultSet.getTimestamp("modifiedTime").toLocalDateTime())
                    .build();

            return newData;
        }

        return null;
    }

    /* ID를 가진 데이터 업데이트 */
    public void Modify(BookmarkVO newData) throws Exception {
        String sql = "update tbl_bookmark set name =?,priority=?,modifiedTime=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getBookmarkConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,newData.getName());
        preparedStatement.setLong(2,newData.getPriority());
        preparedStatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
        preparedStatement.executeUpdate();
    }

    public ArrayList<BookmarkVO> GetBookmarkList() throws Exception {
        ArrayList<BookmarkVO> newList = new ArrayList<>();

        String sql = "select * from tbl_bookmark order by id";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getBookmarkConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            BookmarkVO newData = BookmarkVO.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .priority(resultSet.getInt("priority"))
                    .registerTime(resultSet.getTimestamp("registeredTime").toLocalDateTime())
                    .modifiedTime(resultSet.getTimestamp("modifiedTime").toLocalDateTime())
                    .build();

            newList.add(newData);
        }

        return newList;
    }
}
