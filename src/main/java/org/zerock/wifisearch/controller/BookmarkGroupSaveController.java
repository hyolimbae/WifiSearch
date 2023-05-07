package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.BookmarkDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name= "BookmarkGroupSaveController", urlPatterns = "/bookmarkSave")
public class BookmarkGroupSaveController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    /* 북마크 그룹 추가 버튼 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");

        String wifiName = req.getParameter("wifiName");
        String bookmarkName =  req.getParameter("bookmarkName");

        try
        {
            List<BookmarkDTO> bookmarkDTOList = wifiService.GetBookmarkList();
            for (BookmarkDTO bookmark : bookmarkDTOList)
            {
                if (bookmarkName.equals(bookmark.getName()))
                {
                    bookmark.setWifiName(wifiName);
                    wifiService.ModifyBookmarkWifiName(bookmark);
                    break;
                }
            }

            resp.sendRedirect("/bookmarkList");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}