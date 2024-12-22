<!DOCTYPE html>
<html>
<head>
    <title>SmartEstate</title>
    <link rel="stylesheet" href="/static/css/registration.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #1d2671, #c33764);
            background-size: 400% 400%;
            animation: gradientAnimation 10s ease infinite;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
            color: #fff;
            text-align: center;
            overflow: hidden;
            position: relative;
        }

        @keyframes gradientAnimation {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .user-info {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            position: absolute;
            top: 20px;
            right: 20px;
            z-index: 100;
        }

        .user-info .avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background: linear-gradient(135deg, #1d2671, #c33764);
            background-size: 400% 400%;
            animation: avatarGradientAnimation 5s ease infinite;
            margin-right: 10px;
        }

        .usernametext {
            font-size: 18px;
            font-weight: bold;
            margin-right: 10px;
        }

        .user-info a {
            color: #fff;
            text-decoration: none;
            font-size: 16px;
            margin-left: 15px;
        }

        .user-info a:hover {
            opacity: 0.8;
        }

        h1 {
            font-size: 36px;
            margin-top: 80px;
            font-weight: bold;
            z-index: 50;
        }

        .main-content {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin-top: 100px;
            width: 80%;
            position: relative;
            z-index: 10;
        }

        nav {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }

        nav a, form {
            color: #fff;
            text-decoration: none;
            font-size: 18px;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        nav a:hover {
            background-color: rgba(255, 255, 255, 0.1);
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            width: 100%;
            margin-top: 50px;
        }

        .card {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            width: 300px;
            margin: 20px;
            box-shadow: 0 10px 15px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: left;
            opacity: 0;
            transform: translateY(30px);
            animation: cardSlideIn 1s forwards;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.3);
        }

        .card img {
            width: 100%;
            height: auto;
            border-radius: 15px 15px 0 0;
        }

        .card h2 {
            font-size: 22px;
            margin: 15px 0;
        }

        .card p {
            font-size: 14px;
            color: #ddd;
            line-height: 1.5;
        }

        @keyframes avatarGradientAnimation {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        @keyframes cardSlideIn {
            0% {
                opacity: 0;
                transform: translateY(30px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @media (max-width: 768px) {
            .main-content {
                width: 90%;
            }

            .card-container {
                flex-direction: column;
                align-items: center;
            }
        }
    </style>
</head>
<body>
<header>
    <nav>
        <a href="/">Главная</a>
        <#if user.isAdmin()>
            <a href="/admin">Панель администратора</a>
        </#if>
        <a href="#" onclick="document.getElementById('logoutForm').submit();">Выйти</a>
    </nav>
</header>

<form id="logoutForm" action="/logout" method="post" style="display: none;">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Logout">
</form>
<h1>SmartEstate</h1>
<h2>Добро пожаловать в систему</h2>

<div class="main-content">
<#if user.filledProfile == false>
    <h2 style="color: red">Необходимо обновить данные</h2>
    <div class="card" id="reg-card">
        <a href="/registration">
            <h2>Обновить данные</h2>
        </a>
    </div>
    <#else>
        <div class="card-container">
            <div class="card" id="objects-card">
                <a href="/real-estate-objects">
                    <h2>Квартиры</h2>
                    <p>Раздел, содержащий информацию о продающихся квартирах</p>
                </a>
            </div>
            <div class="card" id="applications-card">
                <a href="/applications">
                    <h2>Заявки</h2>
                    <p>Раздел для создания и просмотра заявок на покупку квартиры</p>
                </a>
            </div>
            <#if user.isNotCustomer()>
                <div class="card" id="clients-card">
                    <a href="/clients">
                        <h2>Клиенты</h2>
                        <p>Раздел для управления клиентами</p>
                    </a>
                </div>
                <div class="card" id="transactions">
                    <a href="/transactions">
                        <h2>Транзакции</h2>
                        <p>Раздел для просмотра осуществленных транзакций</p>
                    </a>
                </div>
            </#if>
            <#if user.isAdmin()>
                <div class="card" id="employees">
                    <a href="/admin">
                        <h2>Панель администратора</h2>
                    </a>
                </div>
            </#if>
        </div>
</#if>
</div>
</body>
</html>
