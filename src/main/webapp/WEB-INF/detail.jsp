<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">

    <style>
        table {
            border-collapse: collapse;
            width: 100% ;
            border-color: whitesmoke;

        }

        th {
            display : block;
            text-align: left;
            padding: 8px;
            color: white;
            background-color: #00ab6f;
            height : 22px;
        }

        td
        {
            display : block;
            text-align: left;
            padding: 8px;
            color: black;
            height : 22px;
        }

        tr {
            display : block;
            float: left;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<a href="/" >홈</a>
<label>|</label>
<a href="/history" >위치 히스토리 목록</a>
<label>|</label>
<a href="/load" >Open API 와이파이 정보 가져오기</a>
<label>|</label>
<a href="/bookmarkList" >북마크 보기</a>
<label>|</label>
<a href="/manage" >북마크 그룹 관리</a>

<form action="/bookmarkSave" method="post">
    <input type="hidden" id="id" name="id" value="${bookmarkdto.id}">
    <input type="hidden" id="wifiName" name="wifiName" value="${dto.x_SWIFI_MAIN_NM}">
    <select name="bookmarkName" id="bookmark">
    <c:forEach items = "${bookmarkList}" var = "bookmarkdto">
    <option> ${bookmarkdto.name} </option>
    </c:forEach>
    </select>

    <input type="submit" value="북마크 추가하기" onclick="alert('북마크가 수정되었습니다')"/>
</form>

<div>
    <table border = 1px>
            <tr>
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
    <tr>
        <td>${dto.distance}</td>
        <td>${dto.x_SWIFI_MGR_NO}</td>
        <td>${dto.x_SWIFI_WRDOFC}</td>
        <td>${dto.x_SWIFI_MAIN_NM}</td>
        <td>${dto.x_SWIFI_ADRES1}</td>
        <td>${dto.x_SWIFI_ADRES2}</td>
        <td>${dto.x_SWIFI_INSTL_FLOOR}</td>
        <td>${dto.x_SWIFI_INSTL_TY}</td>
        <td>${dto.x_SWIFI_INSTL_MBY}</td>
        <td>${dto.x_SWIFI_SVC_SE}</td>
        <td>${dto.x_SWIFI_CMCWR}</td>
        <td>${dto.x_SWIFI_CNSTC_YEAR}</td>
        <td>${dto.x_SWIFI_INOUT_DOOR}</td>
        <td>${dto.x_SWIFI_REMARS3}</td>
        <td>${dto.lat}</td>
        <td>${dto.lnt}</td>
        <td>${dto.work_DTTM}</td>
    </tr>

    </table>
</div>

</body>
</html>
