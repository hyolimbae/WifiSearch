package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.BookmarkDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "BookmarkController", urlPatterns = "/bookmark")
public class BookmarkController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    /* 수정/삭제로 왔을 때 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        // 수정
        long modifyID = Long.parseLong(req.getParameter("id"));
        String modifyName = req.getParameter("name");
        long modifyPriority = Long.parseLong(req.getParameter("priority"));

        try {
            BookmarkDTO dto = wifiService.FindBookmark(modifyID);
            dto.setName(modifyName);
            dto.setPriority(modifyPriority);

            wifiService.ModifyBookmark(dto);
            resp.sendRedirect("/manage");
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    /* 버튼 클릭으로 왔을 때 - 매개인자가 없는 경우*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/WEB-INF/bookmarkAdd.jsp").forward(req,resp);
    }
}
