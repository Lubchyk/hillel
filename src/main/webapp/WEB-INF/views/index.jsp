<!DOCTYPE html>
<html lang="en">
    <head>
         <meta charset="UTF-8">
        <title>Title</title>
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
        <style>
            body {
                background: white }
            form {
                background: black;
                color: white;
                border-radius: 1em;
                padding: 1em;
                position: absolute;
                top: 50%;
                left: 50%;
                margin-right: -50%;
                transform: translate(-50%, -50%) }
            .pass {
                margin-left: 10px;
            }

            but1 {
                height:300px;
                width:500px;
            }
        </style>

        <ol class="breadcrumb">
            <li><a href="/admin">AdminPage</a></li>
            <li><a href="/manager">ManagerPage</a></li>
            <li><a href="/user">UserPage</a></li>
        </ol>

        <form action="login"  method="get">
            <p class="pass"> It is Index page</p>
            <button style="height:50px;width:150px" id="but1" type="submit" size = "20" class="btn btn-default navbar-btn">Login</button>
        </form>
    </body>
</html>