<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>KL kooste</title>
    <link href="http://kuvat.kauppalehti.fi/5/i/styles/klstyle.css" type="text/css" rel="stylesheet">
</head>
<body>
    <div align="center">
    <h3>Uusimmat artikkelit</h3>
    <c:if test="${not empty entries}">
        <table>
            <c:forEach var="entry" items="${entries}">
                <tr id="datetime">
                    <td class="timestamp"><fmt:formatDate value="${entry.publishedDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                </tr>
                <tr id="title">
                    <td class="bluenews left"><a href="${entry.link}">${entry.title}</a></td>
                </tr>
                <tr id="description">
                    <td class="news size11">${entry.description.value}</td>
                </tr>
                <tr />
            </c:forEach>
        </table>
    </c:if>
    </div>
</body>
</div>
</html>