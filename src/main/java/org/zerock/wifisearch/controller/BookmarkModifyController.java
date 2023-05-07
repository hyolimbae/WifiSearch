package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.BookmarkDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "BookmarkModifyController", urlPatterns = "/bookmarkModify")
public class BookmarkModifyController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    /* 수정/삭제로 왔을 때 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            long modifyID = Long.parseLong(req.getParameter("id_Modification"));
            BookmarkDTO dto = wifiService.FindBookmark(modifyID);
            req.setAttribute("id",dto.getId());
            req.setAttribute("name",dto.getName());
            req.setAttribute("priority",dto.getPriority());
            req.getRequestDispatcher("/WEB-INF/bookmarkModify.jsp").forward(req,resp);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

    }
}
