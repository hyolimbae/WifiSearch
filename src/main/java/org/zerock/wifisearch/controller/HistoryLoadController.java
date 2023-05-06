package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.HistoryDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name= "HistoryLoadController", urlPatterns = "/history")
public class HistoryLoadController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            long deleteID = Long.parseLong(req.getParameter("id"));
            wifiService.DeleteHistory(deleteID);
            resp.sendRedirect("/history");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            List<HistoryDTO> dtoList = wifiService.GetHistoryList();

            req.setAttribute("dtoList",dtoList);
            req.getRequestDispatcher("/WEB-INF/history.jsp").forward(req,resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
