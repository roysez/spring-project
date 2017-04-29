<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
    <link rel="stylesheet" href="css/bootstrap.css" media="screen">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> </head>

<body>
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header"> <a href="../" class="navbar-brand">Project</a> </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <!--            Пункти меню-->
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="http://builtwithbootstrap.com/" class="not-active" target="_blank">Sign Up</a></li>
                
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <fieldset>
            <legend>Login to your account</legend>
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="username" class="col-lg-2 control-label">Username</label>
                    <div class="col-lg-10">
                        <input type="text" required class="form-control" id="username" placeholder="Username"> </div>
                </div>
              
                <div class="form-group">
                    <label for="password" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                        <input type="password" required class="form-control" id="password" placeholder="Password"> </div>
                </div>
                
                
                <div class="form-group">
                    <div class="col-lg-10">
                        <button type="reset" class="btn btn-default">Create new account?</button>
                    </div>
                    <div class="col-lg-2">
                        <button type="reset" class="btn btn-danger">Cancel</button>
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>
        </fieldset>
        </form>
    </div>
</body>

</html>