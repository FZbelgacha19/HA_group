<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ tag body-content="empty" %> 
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ attribute name="id"    rtexprvalue="true"  required="true" type="java.lang.String"  description="id marque" %> 
<%@ attribute name="listmarq"  rtexprvalue="true"  required="true" type="java.lang.String"  description="list marque" %> 

<c:set var="idM">${id}</c:set>
<c:forEach items="${listmarq}" var="m">

<c:if test="${m == idM}">

</c:if>


</c:forEach>