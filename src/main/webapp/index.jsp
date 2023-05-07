<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="UTF-8">


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

<script>

  /* 현재 유저 위치 구하기 */
  function getUserLocation()
  {
    if (navigator.geolocation)
    {
        var options = {enableHighAccuracy: false, maximumAge: 0,  timeout: 15000};
        navigator.geolocation.getCurrentPosition(success,error,options);

        function success(position)
        {
            document.getElementById("latText").value = position.coords.latitude ;
            document.getElementById("lntText").value = position.coords.longitude;
        }

        function error(e)
        {
            console.error(error);
        }
    }
  }

</script>

<h1>와이파이 정보 구하기</h1>

<a href="/" >홈</a>
<label>|</label>
<a href="/history" >위치 히스토리 목록</a>
<label>|</label>
<a href="/load" >Open API 와이파이 정보 가져오기</a>
<label>|</label>
<a href="/bookmark" >북마크 보기</a>
<label>|</label>
<a href="/manage" >북마크 그룹 관리</a>

<form action="/lookup" method="post">
  <label for="latText">LAT:</label>
  <input type="text" id="latText" name="latValue" value="${latValue}">
  <label for="lntText">, LNT:</label>
  <input type="text" id="lntText" name="lntValue" value="${lntValue}">
  <input type="button" value="내 위치 가져오기" onclick="getUserLocation()">
  <input type="submit" value="근처 WIFI 정보 보기">
</form>

<div>
  <table border = 1px>
    <thead>
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
    </thead>
<tbody>
<c:forEach items = "${dtoList}" var = "dto">
<tr>
  <td>${dto.distance}</td>
  <td>${dto.x_SWIFI_MGR_NO}</td>
  <td>${dto.x_SWIFI_WRDOFC}</td>
  <td>
    <form action="/detail" method="post">
      <input type="hidden" id="distance" name="distance" value="${dto.distance}">
      <input type="hidden" id="x_SWIFI_MGR_NO" name="x_SWIFI_MGR_NO" value="${dto.x_SWIFI_MGR_NO}">
      <input type="hidden" id="x_SWIFI_WRDOFC" name="x_SWIFI_WRDOFC" value="${dto.x_SWIFI_WRDOFC}">
      <input type="hidden" id="x_SWIFI_MAIN_NM" name="x_SWIFI_MAIN_NM" value="${dto.x_SWIFI_MAIN_NM}">
      <input type="hidden" id="x_SWIFI_ADRES1" name="x_SWIFI_ADRES1" value="${dto.x_SWIFI_ADRES1}">
      <input type="hidden" id="x_SWIFI_ADRES2" name="x_SWIFI_ADRES2" value="${dto.x_SWIFI_ADRES2}">
      <input type="hidden" id="x_SWIFI_INSTL_FLOOR" name="x_SWIFI_INSTL_FLOOR" value="${dto.x_SWIFI_INSTL_FLOOR}">
      <input type="hidden" id="x_SWIFI_INSTL_TY" name="x_SWIFI_INSTL_TY" value="${dto.x_SWIFI_INSTL_TY}">
      <input type="hidden" id="x_SWIFI_INSTL_MBY" name="x_SWIFI_INSTL_MBY" value="${dto.x_SWIFI_INSTL_MBY}">
      <input type="hidden" id="x_SWIFI_SVC_SE" name="x_SWIFI_SVC_SE" value="${dto.x_SWIFI_SVC_SE}">
      <input type="hidden" id="x_SWIFI_CMCWR" name="x_SWIFI_CMCWR" value="${dto.x_SWIFI_CMCWR}">
      <input type="hidden" id="x_SWIFI_CNSTC_YEAR" name="x_SWIFI_CNSTC_YEAR" value="${dto.x_SWIFI_CNSTC_YEAR}">
      <input type="hidden" id="x_SWIFI_INOUT_DOOR" name="x_SWIFI_INOUT_DOOR" value="${dto.x_SWIFI_INOUT_DOOR}">
      <input type="hidden" id="x_SWIFI_REMARS3" name="x_SWIFI_REMARS3" value="${dto.x_SWIFI_REMARS3}">
      <input type="hidden" id="lat" name="lat" value="${dto.lat}">
      <input type="hidden" id="lnt" name="lnt" value="${dto.lnt}">
      <input type="hidden" id="work_DTTM" name="work_DTTM" value="${dto.work_DTTM}">
      <button type="submit" name= ${dto.x_SWIFI_MAIN_NM} value=${dto.x_SWIFI_MAIN_NM} class="btn-link">${dto.x_SWIFI_MAIN_NM}</button>
    </form>
  </td>
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
</c:forEach>

  </table>
</div>
</body>
</html>
