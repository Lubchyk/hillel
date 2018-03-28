<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
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
        <li><a href="/admin">AdminPage</a></li>
        <li><a href="/manager">ManagerPage</a></li>
        <li class="active">UserPage</li>
    </ol>
    <div style="margin-top: 10%">
        <form action="/logout" method="get" style="background: black;color: white; border-radius: 1em; padding: 1em; position: absolute;
               margin-top: auto; left: 50%; margin-right: -50%; transform: translate(-50%, -50%)" class="form-inline" >
            Dear <strong>${user}</strong>, Welcome to User Page.
            <button style="height:35px;width:100px" id="but6" type="submit" size = "20" class="btn btn-default navbar-btn">Logout</button>
        </form>
    </div>
        <div class="conteiner">
            <div class="row" >
                <div class="col-md-6 col-md-offset-3" >
                    <div class="panel panel-info" style="margin-top: 20%">
                        <div class="panel-heading">
                            <h3 class="panel-title">Purchase</h3>
                        </div>
                        <div class="panel-body">
                            <form action = "getMyOrders" class="navbar-form navbar-left" method="get" role="search">
                                <div class="form-group">
                                    <input id="number" name="number" size="35" type="text" class="form-control" placeholder="How many purchases to search?">
                                </div>
                                <button type="submit" class="btn btn-default">Search</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>