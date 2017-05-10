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
            <div class="navbar-header"> <a href="<c:url value="/"/>" class="navbar-brand">Project</a> </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/users/"/>">Users</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="isAuthenticated()">

                        <li><a href="/users/<sec:authentication property="principal.username"/>">Hello,<sec:authentication property="principal.username"/></a></li>
                        <li><a href="<c:url value="/account/logout"/>">Logout</a></li>

                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <li><a href="<c:url value="/account/signup"/>" >Sign Up</a></li>
                        <li><a href="<c:url value="/account/login"/>" >Already have an account?</a></li>
                    </sec:authorize>



                </ul>
            </div>
        </div>
    </div>

    <div class="container">

       <legend>Welcome to our website</legend>

    </div>

    <footer class="modal-footer" style="position: absolute;bottom: 0px;">
        <div class="container">
            <p class="text-muted">Project by Sergiy Balukh (Roysez)</p>
        </div>
    </footer>
</body>

</html>