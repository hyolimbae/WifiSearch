package org.zerock.wifisearch.controller;

import org.zerock.wifisearch.dto.WifiDTO;
import org.zerock.wifisearch.service.WifiService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@WebServlet(name= "WifiDetailController", urlPatterns = "/detail")
public class WifiDetailController extends HttpServlet
{
    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException
    {
        // 인코딩 설정 - 설정하지 않을 시 한글 깨짐 현상 발생
        req.setCharacterEncoding("UTF-8");

        WifiDTO dto = WifiDTO.builder()
                .distance(Double.parseDouble(req.getParameter("distance")))
                .x_SWIFI_MGR_NO(req.getParameter("x_SWIFI_MGR_NO"))
                .x_SWIFI_WRDOFC(req.getParameter("x_SWIFI_WRDOFC"))
                .x_SWIFI_MAIN_NM(req.getParameter("x_SWIFI_MAIN_NM"))
                .x_SWIFI_ADRES1(req.getParameter("x_SWIFI_ADRES1"))
                .x_SWIFI_ADRES2(req.getParameter("x_SWIFI_ADRES2"))
                .x_SWIFI_INSTL_FLOOR(req.getParameter("x_SWIFI_INSTL_FLOOR"))
                .x_SWIFI_INSTL_TY(req.getParameter("x_SWIFI_INSTL_TY"))
                .x_SWIFI_INSTL_MBY(req.getParameter("x_SWIFI_INSTL_MBY"))
                .x_SWIFI_SVC_SE(req.getParameter("x_SWIFI_SVC_SE"))
                .x_SWIFI_CMCWR(req.getParameter("x_SWIFI_CMCWR"))
                .x_SWIFI_CNSTC_YEAR(req.getParameter("x_SWIFI_CNSTC_YEAR"))
                .x_SWIFI_INOUT_DOOR(req.getParameter("x_SWIFI_INOUT_DOOR"))
                .x_SWIFI_REMARS3(req.getParameter("x_SWIFI_REMARS3"))
                .lat(Double.parseDouble(req.getParameter("lat")))
                .lnt(Double.parseDouble(req.getParameter("lnt")))
                .work_DTTM(req.getParameter("work_DTTM"))
                .build();
        try {
            req.setAttribute("dto",dto);
            req.getRequestDispatcher("/WEB-INF/detail.jsp").forward(req,resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
