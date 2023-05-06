package org.zerock.wifisearch.service;


import org.modelmapper.ModelMapper;
import org.zerock.wifisearch.dao.HistoryDAO;
import org.zerock.wifisearch.dao.WifiDAO;
import org.zerock.wifisearch.domain.HistoryVO;
import org.zerock.wifisearch.domain.WifiVO;
import org.zerock.wifisearch.dto.HistoryDTO;
import org.zerock.wifisearch.dto.WifiDTO;
import org.zerock.wifisearch.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*  CRUD 기능을 구현했고 Enum 을 이용해 Singleton Pattern 을 사용한 서비스 객체 */
public enum WifiService
{
    INSTANCE;

    private WifiDAO wifiDAO;
    private HistoryDAO historyDAO;
    private ModelMapper modelMapper;

    WifiService()
    {
        wifiDAO = new WifiDAO();
        historyDAO = new HistoryDAO();
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
}