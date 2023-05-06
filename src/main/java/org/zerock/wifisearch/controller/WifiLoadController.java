package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.api.WifiApi;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "WifiLoadController", urlPatterns = "/load")
public class WifiLoadController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            wifiService.Create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("count", WifiApi.totalWifiDataCount);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/load.jsp");
        requestDispatcher.forward(req,resp);
    }
}
