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

<script src="<c:url value="/static/js/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/static/js/home.js"/> "></script>
<body>
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header"> <a href="<c:url value="/"/>" class="navbar-brand">Home</a> </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/articles/"/>">Articles</a></li>

                </ul>
                <ul class="nav navbar-nav ">
                    <li><a href="#search">Search</a></li>
                </ul>
                <div id="search">
                    <button type="button" class="close">×</button>
                    <form action="" method="get">
                        <input type="search" value="" name="ssoId" placeholder="type part of username here."  />
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="isAuthenticated()">

                        <li><a href="/users/<sec:authentication property="principal.username"/>">Hello,<sec:authentication property="principal.username"/></a></li>
                        <li><a href="<c:url value="/account/logout"/>" >Logout</a></li>

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

       <legend>Welcome to our website, here you can see list of all users</legend>
        <table class="table table-striped table-hover ">
            <thead>
                <tr>
                    <th>#id</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
            <%
                Integer counter = 0;
                counter++;
            %>
            <c:forEach items="${listOfAllUsers}" var="item">
                <tr class='clickable-row' data-href='<c:url value="/users/${item.getSsoId()}"/>' >
                    <td><%=counter%></td>
                    <td>
                        <a href="<c:url value="/users/${item.getSsoId()}"/>" >
                        ${item.getSsoId()}
                        </a>

                    </td>
                    <td>${item.getFirstName()}</td>
                    <td>${item.getLastName()}</td>
                    <td>${item.getEmail()}</td>
                </tr>

                <%counter++;%>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <footer class="modal-footer" style="position: absolute;bottom: 0px;">
        <div class="container">
            <p class="text-muted">Project by Sergiy Balukh (Roysez)</p>
        </div>
    </footer>
</body>

</html>