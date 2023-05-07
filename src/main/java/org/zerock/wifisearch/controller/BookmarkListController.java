package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.BookmarkDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name= "BookmarkListController", urlPatterns = "/bookmarkList")
public class BookmarkListController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");

        try {
            List<BookmarkDTO> dtoList = wifiService.GetBookmarkList();
            List<BookmarkDTO> filteredList = new ArrayList<>();
            for (BookmarkDTO dto : dtoList)
            {
                if (dto.getWifiName() != null)
                    filteredList.add(dto);
            }

            req.setAttribute("dtoList",filteredList);
            req.getRequestDispatcher("/WEB-INF/bookmarkList.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}