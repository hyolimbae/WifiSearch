package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.service.WifiService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "BookmarkGroupDeleteController", urlPatterns = "/bookmarkGroupDelete")
public class BookmarkGroupDeleteController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        long deleteID = Long.parseLong(req.getParameter("id_Deletion"));
        try
        {
            wifiService.DeleteBookmark(deleteID);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/manage");
    }
}
