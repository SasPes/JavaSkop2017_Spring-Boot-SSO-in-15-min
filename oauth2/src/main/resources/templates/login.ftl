<html>
    <head>
        <link rel="stylesheet" href="css/wro.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        </head>
    <body>
<#if RequestParameters['error']??>
        <div class="alert alert-danger">
            There was a problem logging in. Please try again.
            </div>
</#if>
        <div class="container" style="max-width: 330px;">
            <br/>
            <img src="images/JavaSkop.png" width="300"/>
            <br/><br/>
            <form role="form" action="login" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="srsinput-text" id="username" name="username" placeholder="Enter username"/>
                    </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="srsinput-text" id="password" name="password" placeholder="Enter password"/>
                    </div>
                <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="srsbtn-primary">Login</button>
                </form>
            </div>
        <script src="js/wro.js" type="text/javascript"></script>
        </body>
    </html>