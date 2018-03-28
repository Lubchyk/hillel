<!DOCTYPE html>
<html lang="en">
<head>
    <meta name ="viewport", content="width=device-width, initial-scale=1">
    <!-- Последняя компиляция и сжатый CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Дополнение к теме -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">

    <!-- Последняя компиляция и сжатый JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="#">Home</a></li>
    <li><a href="#">Library</a></li>
    <li class="active">Data</li>
</ol>



<div class="container">
    <div class="row" >
        <div class="col-md-6 col-md-offset-3" >

            <div class="panel panel-default" style="margin-top: 45px">
                <div class="panel-heading">
                    <h3 class="panel-title">Login with User Name and Password</h3>
                </div>
                <div class="panel-body">

                    <#if logout >
                         <div class="alert alert-info" role="alert">Login was successful</div>
                    </#if>

                    <#if error>
                        <div class="alert alert-danger" role="alert">Your login attempt was not successful, try again.</div>
                    </#if>

                    <form method="post" class="form-inline">
                        <input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
                        <div class="form-group">
                            <label class="username" for="username">User name</label>
                            <input type="text" class="form-control" name="username" id="username" placeholder="Login">
                        </div>
                        <div class="form-group">
                            <label class="password" for="password">Password</label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>













<!--<style>-->
<!--body {-->
<!--background: white }-->
<!--form {-->
<!--background: black;-->
<!--color: white;-->
<!--border-radius: 1em;-->
<!--padding: 1em;-->
<!--position: absolute;-->
<!--top: 50%;-->
<!--left: 50%;-->
<!--margin-right: -50%;-->
<!--transform: translate(-50%, -50%) }-->
<!--.button {-->
<!--margin-left: 99px;-->
<!--padding: 0px 12px;-->
<!--}-->
<!--</style>-->



</body>
</html>