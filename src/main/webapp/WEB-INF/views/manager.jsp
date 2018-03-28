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

        <ol class="breadcrumb">
            <li><a href="/admin">AdminPage</a></li>
            <li class="active">ManagerPage</li>
            <li><a href="/user">UserPage</a></li>
        </ol>

        <form action = "/topOrders" style="margin-top: 7%" name="form3"  method="get" >
            <div class="panel panel-warning">
                <div class="panel-heading">
                    <h3 class="panel-title">Top Order</h3>
                </div>
                <div class="panel-body">
                    <button id="but4" type="submit" size = "20" class="btn btn-default navbar-btn">TOP THREE ORDERS </button>
                </div>
            </div>
        </form>

        <div class="conteiner">
            <div class="row" >
                <div class="col-md-6 col-md-offset-3" >
                    <div class="panel panel-info" >
                        <div class="panel-heading">
                            <h3 class="panel-title">Search Orders</h3>
                        </div>
                        <div class="panel-body">
                            <form action = "/findOne" class="navbar-form navbar-left" method="get" >
                                <div class="form-group">
                                    <input name="id" size="35" type="text" class="form-control" placeholder="Enter the number of order">
                                </div>
                                <button type="submit" class="btn btn-default">Search</button>
                            </form>
                            <form action = "/findAll" class="navbar-form navbar-left" method="get" >
                                <button style="margin-left: 150px" type="submit" class="btn btn-default">Get All Orders</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form action="/logout" method="get" style="background: black;color: white; border-radius: 1em; padding: 1em; position: absolute;
                top: 11%; left: 50%; margin-right: -50%; transform: translate(-50%, -50%) " class="form-inline">
            Dear <strong>${user}</strong>, Welcome to Manager Page.
            <button style="height:35px;width:100px" id="but6" type="submit" size = "20" class="btn btn-default navbar-btn">Logout</button>
        </form>

    </body>
</html>