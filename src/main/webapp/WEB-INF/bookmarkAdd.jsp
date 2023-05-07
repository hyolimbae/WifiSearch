<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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

<h1>북마크 그룹 추가</h1>

<a href="/" >홈</a>
<label>|</label>
<a href="/history" >위치 히스토리 목록</a>
<label>|</label>
<a href="/load" >Open API 와이파이 정보 가져오기</a>
<label>|</label>
<a href="/bookmark" >북마크 보기</a>
<label>|</label>
<a href="/manage" >북마크 그룹 관리</a>

<div>
    <form action="/manage" method="post">
    <table border = 1px>
        <tr>
            <th>북마크 이름</th>
            <th>순서</th>
        </tr>
        <tr>
            <td>
                <input type="text" id="name" name="name">
            </td>
            <td>
                <input type="text" id="priority" name="priority">
            </td>
            <td>
                <input type="submit" value="추가" onclick="alert('북마크가 추가되었습니다')">
            </td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>
