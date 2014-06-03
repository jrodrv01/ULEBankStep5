<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head><title><fmt:message key="sharefees"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
   <p><fmt:message key="greeting"/> </p>
    <h3>Fees applies on Client's Investment Account <c:out value="${model.id}"/></h3>
    <% int i = 0; %>
    <c:forEach items="${model.feeArray}" var="bfee">
    <% i+=1; %>
      <%=i+""%> <c:out value="${bfee.description}"/> <c:out value="${bfee.fee}"/><br>
    </c:forEach>
    
    <br>
    <a href="<c:url value="changefee.htm"/>">Decrease Fees</a>
    <br>
  </body>
</html>