<%@ page import="sda.model.User" %>
<!DOCTYPE html>
<html lang="en">
<%@ page session="true" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<%@ include file="header.jsp" %>
<div class="container" style="width: 400px">
    <div class="container" style="width: 400px">
        <h1 class="form-signin-heading">Witaj ${user.firstName} ${user.lastName}</h1>
        <h1 class="form-signin-heading">Account number: ${user.account.accountNumber}</h1>
        <h2 class="form-signin-heading">Balance account:</h2>
        <h3 class="form-signin-heading">${user.account.balance}${user.account.currency}</h3>
        <h2 class="form-signin-heading">Available to withdraw:</h2>
        <h3 class="form-signin-heading">${user.account.availableMoney}${user.account.currency}</h3>
    </div>
    <form class="form-signin" action="logout" method="post">
        <button class="btn btn-lg btn-primary btn-block" type="submit">log out</button>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
