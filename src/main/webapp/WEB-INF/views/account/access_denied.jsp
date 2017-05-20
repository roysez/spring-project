<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Home page</title>
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.css' />" media="screen">
    <link rel="stylesheet" href="<c:url value='/static/css/styles.css' />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> </head>

<body>
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header"> <a href="<c:url value="/"/>" class="navbar-brand">Home</a> </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <!--            Пункти меню-->
                    <li><a href="<c:url value="/users/"/>">Users</a></li>
                    <li><a href="<c:url value="/articles/"/>">Users</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">

                </ul>
            </div>
        </div>
    </div>

    <div class="container">
       <legend>Dear
           <sec:authorize access="isAuthenticated()">
           <sec:authentication property="principal.username"/>
           </sec:authorize>
           <sec:authorize access="isAnonymous()">
               user
           </sec:authorize>
           , access denied for you</legend>
        <br/>
        <a href="<c:url value="/" />">Go to home</a>
        <sec:authorize access="hasRole('USER') or hasRole('ADMIN') ">
            OR  <a href="<c:url value="/account/logout" />">Logout</a>
        </sec:authorize>


    </div>

    <footer class="modal-footer" style="position: absolute;bottom: 0px;">
        <div class="container">
            <p class="text-muted">Project by Sergiy Balukh (Roysez)</p>
        </div>
    </footer>
</body>

</html>