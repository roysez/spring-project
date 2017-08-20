<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Home page</title>
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.css' />" media="screen">
    <link rel="stylesheet" href="<c:url value='/static/css/styles.css' />">
    <link rel="stylesheet" href="<c:url value='/static/css/home.css' />">
    <script src="<c:url value="/static/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/static/js/home.js"/> "></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> </head>

<body>
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header"> <a href="<c:url value="/"/>" class="navbar-brand">Home</a> </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/users"/>">Users</a></li>
                    <li><a href="<c:url value="/articles/"/>">Articles</a></li>
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

            <a onclick="showArticleForm()" class="btn btn-primary btn-md" id="btn-post-article" >Post article</a>
            <div class="col-md-8 col-md-offset-2">



                <form:form action="articles/"  modelAttribute="article" method="POST" class="article-form">
                    <h1>Create post</h1>


                    <div class="form-group">
                        <label for="title">Title <span class="require">*</span></label>
                        <form:input placeholder="Title of article"
                                    path="title" id="title" type="text"
                                    class="form-control" name="title"  />
                    </div>

                    <div class="form-group">
                        <label for="content">Content</label>
                        <form:textarea placeholder="Content of article"
                                       path="content"
                                       id="content" rows="5" class="form-control" name="content" ></form:textarea>
                    </div>


                    <div class="form-group">
                        <p><span class="require">*</span> - required fields</p>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">
                            Create
                        </button>
                        <button type="reset" class="btn btn-default">
                            Cancel
                        </button>
                    </div>

                </form:form>
            </div>


    </div>


    <footer class="modal-footer" style="position: absolute;bottom: 0px;">
        <div class="container">
            <p class="text-muted">Project by Sergiy Balukh (Roysez)</p>
        </div>
    </footer>
</body>

</html>