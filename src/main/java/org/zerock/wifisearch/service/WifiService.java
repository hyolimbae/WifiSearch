package org.zerock.wifisearch.service;


import org.modelmapper.ModelMapper;
import org.zerock.wifisearch.dao.BookmarkDAO;
import org.zerock.wifisearch.dao.HistoryDAO;
import org.zerock.wifisearch.dao.WifiDAO;
import org.zerock.wifisearch.domain.BookmarkVO;
import org.zerock.wifisearch.domain.HistoryVO;
import org.zerock.wifisearch.domain.WifiVO;
import org.zerock.wifisearch.dto.BookmarkDTO;
import org.zerock.wifisearch.dto.HistoryDTO;
import org.zerock.wifisearch.dto.WifiDTO;
import org.zerock.wifisearch.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

/*  CRUD 기능을 구현했고 Enum 을 이용해 Singleton Pattern 을 사용한 서비스 객체 */
public enum WifiService
{
    INSTANCE;

    private WifiDAO wifiDAO;
    private HistoryDAO historyDAO;
    private BookmarkDAO bookmarkDAO;

    private ModelMapper modelMapper;

    WifiService()
    {
        wifiDAO = new WifiDAO();
        historyDAO = new HistoryDAO();
        bookmarkDAO = new BookmarkDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void Create() throws Exception {
        wifiDAO.create();
    }

    public List<WifiDTO> FindClosestWifi(double latitude, double longitude) throws Exception
    {
        List<WifiVO> voList = wifiDAO.FindClosestWifi(latitude,longitude);

        return voList.stream()
                .map(vo -> modelMapper.map(vo,WifiDTO.class))
                .collect(Collectors.toList());
    }

    public List<HistoryDTO> GetHistoryList() throws Exception
    {
        List<HistoryVO> voList = historyDAO.GetHistoryList();

        return voList.stream()
                .map(vo -> modelMapper.map(vo,HistoryDTO.class))
                .collect(Collectors.toList());
    }

    public void InsertHistory(HistoryDTO newHistory) throws Exception
    {
        historyDAO.Insert(modelMapper.map(newHistory,HistoryVO.class));
    }

    public void DeleteHistory(long ID) throws Exception
    {
        historyDAO.Delete(ID);
    }

    public List<BookmarkDTO> GetBookmarkList() throws Exception
    {
        List<BookmarkVO> voList = bookmarkDAO.GetBookmarkList();

        return voList.stream()
                .map(vo -> modelMapper.map(vo,BookmarkDTO.class))
                .collect(Collectors.toList());
    }

    public void InsertBookmark(BookmarkDTO newBookmark) throws Exception
    {
        bookmarkDAO.Insert(modelMapper.map(newBookmark,BookmarkVO.class));
    }

    public void DeleteBookmark(long ID) throws Exception
    {
        bookmarkDAO.Delete(ID);
    }

    public BookmarkDTO FindBookmark(long ID) throws Exception
    {
        return modelMapper.map(bookmarkDAO.Find(ID),BookmarkDTO.class);
    }

    public void ModifyBookmark(BookmarkDTO dto) throws Exception {
        bookmarkDAO.Modify(modelMapper.map(dto,BookmarkVO.class));
    }

    public void ModifyBookmarkWifiName(BookmarkDTO dto) throws Exception {
        bookmarkDAO.ModifyBookmarkWifiName(modelMapper.map(dto,BookmarkVO.class));
    }
}
