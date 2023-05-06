package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.HistoryDTO;
import org.zerock.wifisearch.dto.WifiDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name= "WifiLookupController", urlPatterns = "/lookup")
public class WifiLookupController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("LookingUp");

        double latitude = Double.parseDouble(req.getParameter("latValue"));
        double longitude = Double.parseDouble(req.getParameter("lntValue"));

        req.setAttribute("latValue",latitude);
        req.setAttribute("lntValue",longitude);

        try {
            // 현재 lookup 정보 history에 저장
            HistoryDTO newData = HistoryDTO.builder()
                    .lnt(longitude)
                    .lat(latitude)
                    .date(LocalDateTime.now())
                    .build();

            wifiService.InsertHistory(newData);

            // 조회 결과 Redirect
            List<WifiDTO> dtoList = wifiService.FindClosestWifi(latitude,longitude);
            req.setAttribute("dtoList",dtoList);
            req.getRequestDispatcher("/").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
