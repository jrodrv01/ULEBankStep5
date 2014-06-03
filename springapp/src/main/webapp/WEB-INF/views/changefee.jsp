<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body>
<h1>Fees applied on Buy and Sell Shares</h1>
<form:form method="post" commandName="changeFee">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%">Decrease Fees on: (%):</td>
        <td width="20%">
          <form:input path="percentage"/>
        </td>
        <td width="60%">
          <form:errors path="percentage" cssClass="error"/>
        </td>
    </tr>
    
    <tr>
      <td align="right" width="20%">Decrease Fees on BuySell Stock Fee number: (%):</td>
        <td width="20%">
          <form:input path="index"/>
        </td>
        <td width="60%">
          <form:errors path="index" cssClass="error"/>
        </td>
    </tr>
  </table>
  <br>
  <input type="submit" value="Change Fee">
</form:form>
<a href="<c:url value="ShareBuySellView.htm"/>">Home</a>
</body>
</html>