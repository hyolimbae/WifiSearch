<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            border-color: whitesmoke;
        }

        th {
            text-align: left;
            padding: 8px;
            color: white;
            background-color: #00ab6f;

        }

        tr:nth-child(even) {
            background-color: whitesmoke;
        }

        tr:hover { background-color: lightyellow; }

    </style>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<a href="/" >홈</a>
<label>|</label>
<a href="/history" >위치 히스토리 목록</a>
<label>|</label>
<a href="/load" >Open API 와이파이 정보 가져오기</a>
<label>|</label>
<a href="/bookmarkList" >북마크 보기</a>
<label>|</label>
<a href="/manage" >북마크 그룹 관리</a>

<div>
    <table border = 1px>
        <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items = "${dtoList}" var = "dto">
            <tr>
                <td>${dto.id}</td>
                <td>${dto.lat}</td>
                <td>${dto.lnt}</td>
                <td>${dto.date}</td>
                <td align = "center">
                    <form action="/history" method="post">
                         <input type="hidden" id="id" name="id" value="${dto.id}">
                         <input type="submit" value="삭제">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
