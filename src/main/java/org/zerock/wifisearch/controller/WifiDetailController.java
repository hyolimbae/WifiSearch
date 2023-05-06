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

@WebServlet(name= "WifiDetailController", urlPatterns = "/detail")
public class WifiDetailController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            // req.setAttribute("dto",dto);
            req.getRequestDispatcher("/WEB-INF/detail.jsp").forward(req,resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
