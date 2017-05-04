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
                    <!--            Пункти меню-->
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="hasRole('USER') or hasRole('ADMIN') ">
                        <li><a href="#">Hello, ${authenticatedUserName}</a></li>
                        <li><a href="/account/logout" >Logout</a></li>

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
        <c:if test="${successfulRegistration!=null}">
            <div class="alert alert-success">
                <p>${successfulRegistration}</p>
            </div>
        </c:if>

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
            <c:forEach items="${listOfAllUsers}" var="item">
            <%
                Integer counter = 0;
                counter++;
            %>
                    <td><%=counter%></td>
                    <td>${item.getSsoId()}</td>
                    <td>${item.getFirstName()}</td>
                    <td>${item.getLastName()}</td>
                    <td>${item.getEmail()}</td>
                </tr>
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