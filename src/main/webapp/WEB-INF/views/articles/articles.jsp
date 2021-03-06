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
    <script src="<c:url value="/static/js/home.js"/> "></script>
</head>
<body>
<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header"><a href="<c:url value="/"/>" class="navbar-brand">Home</a></div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <!--            Пункти меню-->
                <li><a href="<c:url value="/users/"/>">Users</a></li>
            </ul>
            <ul class="nav navbar-nav ">
                <li><a href="#search">Search</a></li>
            </ul>
            <div id="search">
                <button type="button" class="close">×</button>
                <form action="" method="get">
                    <input type="search" value="" name="title" placeholder="type keywords here."  />
                    <button type="submit" class="btn btn-primary">Search(Articles)</button>
                </form>
            </div>
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
<div class="container">
    <div class="alert-block">

    </div>
    <c:if test="${articlesList.isEmpty()}" >
        <div class="alert alert-danger">
            <p>There are not any article, try to post a new one</p>
        </div>
    </c:if>
    <c:if test="${!articlesList.isEmpty()}">
    <c:forEach items="${articlesList}" var="articleItem">
        <div class="panel panel-info">
            <div class="row">
                <div class="col-xs-1">

                </div>
                <div class="col-xs-9">

                    <div class="article">
                        <h3><a href="/articles/${articleItem.getId()}">${articleItem.getTitle()}</a></h3>

                        <ul class="list-unstyled list-inline">
                            <li><i class="fa fa-calendar"></i> ${articleItem.getDate()}</li>
                            <li><a href="/users/${articleItem.getUser().getSsoId()}"><i
                                    class="fa fa-user"></i> ${articleItem.getUser().getSsoId()}</a></li>
                        </ul>

                        <div class="article-content">

                            <pre>${articleItem.getContent()}</pre>

                        </div>
                    </div>


                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </c:forEach>
    </c:if>
</div>
</body>
</html>
