<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article</title>
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.css' />" media="screen">
    <link rel="stylesheet" href="<c:url value='/static/css/styles.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/article.css' />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="<c:url value="/static/js/jquery-3.2.1.min.js"/>"></script>
</head>
<body>
<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header"><a href="<c:url value="/"/>" class="navbar-brand">Home</a></div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <!--            Пункти меню-->
                <li><a href="<c:url value="/users/"/>">Users</a></li>
                <li><a href="<c:url value="/articles/"/>">Articles</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/users/<sec:authentication property="principal.username"/>">Hello,<sec:authentication
                            property="principal.username"/></a></li>
                    <li><a href="<c:url value="/account/logout"/> ">Logout</a></li>

                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li><a href="<c:url value="/account/signup"/>">Sign Up</a></li>
                    <li><a href="<c:url value="/account/login"/>">Already have an account?</a></li>
                </sec:authorize>


            </ul>
        </div>
    </div>
</div>

<div class="container main">
    <c:if test="${articleNotFound!=null}">
    <div class="alert alert-danger">
        <p>${articleNotFound}</p>
    </div>
    </c:if>
    <c:if test="${articleNotFound==null}">
    <div class="alert-block">

    </div>
    <div class="panel panel-info">
        <div class="row">
            <div class="col-xs-1">

            </div>
            <div class="col-xs-9">

                <div class="article">
                    <h3>${article.getTitle()}</h3>

                    <ul class="list-unstyled list-inline">
                        <li><i class="fa fa-calendar"></i> ${article.getDate()}</li>
                        <li><a href="/users/${article.getUser().getSsoId()}"><i
                                class="fa fa-user"></i> ${article.getUser().getSsoId()}</a></li>
                    </ul>

                    <div class="article-content">

                            ${article.getContent()}

                    </div>
                </div>


            </div>
            <div class="col-md-2"></div>
        </div>
    </div>

        </c:if>
</body>
</html>
