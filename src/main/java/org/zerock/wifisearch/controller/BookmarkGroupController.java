package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.BookmarkDTO;
import org.zerock.wifisearch.dto.HistoryDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name= "BookmarkGroupAddController", urlPatterns = "/manage")
public class BookmarkGroupController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    /* 북마크 그룹 추가 버튼 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String bookmarkName = req.getParameter("name");
        long bookmarkPriority = Long.parseLong(req.getParameter("priority"));

        BookmarkDTO dto = BookmarkDTO.builder()
                .name(bookmarkName)
                .priority(bookmarkPriority)
                .build();

        try {
            wifiService.InsertBookmark(dto);
            resp.sendRedirect("/manage");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            List<BookmarkDTO> dtoList = wifiService.GetBookmarkList();
            req.setAttribute("dtoList",dtoList);
            req.getRequestDispatcher("/WEB-INF/manage.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}