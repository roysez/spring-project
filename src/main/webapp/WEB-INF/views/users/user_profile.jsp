<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roysez
  Date: 04.05.2017
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User profile</title>
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.css' />" media="screen">
    <link rel="stylesheet" href="<c:url value='/static/css/styles.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/user_profile.css' />">
</head>
<body>
<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header"><a href="<c:url value="/"/>" class="navbar-brand">Project</a></div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <!--            Пункти меню-->
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <li><a href="#">Hello,<sec:authentication property="principal.username"/></a></li>
                    <li><a href="/account/logout">Logout</a></li>

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
    <c:if test="${errorUserNotFound!=null}">
    <div class="alert alert-danger">
        <p>${errorUserNotFound}</p>
    </div>
    </c:if>
    <c:if test="${errorUserNotFound==null}">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${user.getSsoId()}</h3>

        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-lg-3" align="center">
                    <div class="col-md-3 col-lg-3" align="center">
                        <img class="avatar" alt="User Pic"
                             src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ6afIs-1S3GXUawbA_UeVHFwO8niO_4O7iEll3Uh8o_FNejgiC">
                    </div>

                </div>


                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>


                        <tr>
                            <td>Username</td>
                            <td>${user.getSsoId()}</td>
                        </tr>
                        <tr>
                            <td>First name</td>
                            <td>${user.getFirstName()}</td>
                        </tr>
                        <tr>
                            <td>Last name</td>
                            <td>${user.getLastName()}</td>
                        </tr>

                        <tr>
                            <td>Email</td>
                            <td>${user.getEmail()}</td>
                        </tr>


                        </tbody>
                    </table>


                </div>
            </div>
        </div>
        <div class="panel-footer">
                        <sec:authorize access="hasRole('ADMIN')">
                            <span class="pull-right">
                                <a href="#" data-original-title="Edit this user"
                               data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>

                            </span>
                        </sec:authorize>
        </div>

        </c:if>
    </div>
</body>
</html>
