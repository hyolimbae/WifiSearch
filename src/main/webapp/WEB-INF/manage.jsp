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
            text-align: center;
            padding: 8px;
            color: white;
            background-color: #00ab6f;
        }

        .btn-link {
            border: none;
            outline: none;
            background: none;
            cursor: pointer;
            color: #0000EE;
            padding: 0;
            text-decoration: underline;
            font-family: inherit;
            font-size: inherit;
        }
    </style>
</head>
<body>
<h1>북마크 그룹</h1>

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
    <form action="/bookmark" method="get">
        <input type="submit" value="북마크 이름 추가">
    </form>

    <div>
        <table border = 1px>
            <thead>
            <tr>
                <th>ID</th>
                <th>북마크 이름</th>
                <th>순서</th>
                <th>등록일자</th>
                <th>수정일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items = "${dtoList}" var = "dto">
            <tr>
                <td>${dto.id}</td>
                <td>${dto.name}</td>
                <td>${dto.priority}</td>
                <td>${dto.registerTime}</td>
                <td>${dto.modifiedTime}</td>
                <td>
                    <form action="/bookmarkGroupModify" method="post">
                        <input type="hidden" id="id_Modification" name="id_Modification" value="${dto.id}">
                        <input type="submit" value="수정" class="btn-link">
                    </form>
                    <form action="/bookmarkGroupDelete" method="post">
                        <input type="hidden" id="id_Deletion" name="id_Deletion" value="${dto.id}">
                        <input type="submit" value="삭제" class="btn-link">
                    </form>
                </td>

            </tr>
            </c:forEach>

        </table>
    </div>
</div>
</body>
</html>
