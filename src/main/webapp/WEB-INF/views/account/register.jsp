<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
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
    
                    <li><a href="<c:url value="/account/login"/>" >Already have an account?</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <fieldset>
            <legend>Create your account</legend>

            <form:form method="POST" modelAttribute="user" class="form-horizontal">
                <c:if test="${param.error == 'duplicate'}">
                    <div class="alert alert-danger">
                        <p>User with this username already exists.</p>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="ssoId" class="col-lg-2 control-label">Username</label>
                    <div class="col-lg-10">
                        <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"/>
                        <%--<input type="text" required class="form-control" id="username" placeholder="Username"> --%>
                        <div class="has-error">
                            <form:errors path="ssoId" class="help-inline"/>
                        </div>
                    </div>
                </div>
              
                <div class="form-group">
                    <label for="password" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                        <form:input type="password" path="password" id="password" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="password" class="help-inline"/>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="firstName" class="col-lg-2 control-label">First Name</label>
                    <div class="col-lg-10">
                        <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="firstName" class="help-inline"/>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="lastName" class="col-lg-2 control-label">Last Name</label>
                    <div class="col-lg-10">
                        <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="lastName" class="help-inline"/>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="email" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                        <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="email" class="help-inline"/>
                        </div>
                    </div>
                </div>
                
                
                
                <div class="form-group">
                    <div class="col-lg-10">
                        <button type="reset" href="<c:url value="/account/login"/>" class="btn btn-default">You already have an account?</button>
                    </div>
                    <div class="col-lg-2">
                        <button type="reset" class="btn btn-danger">Cancel</button>
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>
            </form:form>
        </fieldset>
    </div>

    <footer class="modal-footer" style="position: absolute;bottom: 0px;">
        <div class="container">
            <p class="text-muted">Project by Sergiy Balukh (Roysez)</p>
        </div>
    </footer>
</body>

</html>