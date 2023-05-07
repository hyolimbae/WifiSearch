<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<h1>북마크 그룹 수정</h1>

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
  <form action="/bookmark" method="post">
  <table border = 1px>
    <tr>
      <th>북마크 이름</th>
      <th>순서</th>
    </tr>
    <tr>
      <td>
        <input type="text" id="name" name= "name" value=${name}>
      </td>
      <td>
        <input type="text" id="priority" name = "priority" value=${priority}>
      </td>
      <td>
        <a href="/manage" >돌아가기</a>
        <label>|</label>
        <input type="hidden" id="id" name="id" value="${id}">
        <input type="submit" value="수정" onclick="alert('북마크가 수정되었습니다')">
      </td>
    </tr>
  </table>
  </form>
</div>
</body>
</html>
