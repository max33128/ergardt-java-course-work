<!DOCTYPE html>
<html>
<head>
    <title>SmartEstate</title>
    <link rel="stylesheet" href="/static/css/registration.css">
    <style>
        /* Основные стили страницы */
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
            color: #fff;
        }

        /* Анимация фона */
        @keyframes gradientAnimation {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        /* Заголовок */
        .centered-header {
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: bold;
        }

        /* Поля ввода */
        .auth-form input[type="text"],
        .auth-form input[type="password"],
        .auth-form input[type="email"],
        .auth-form input[type="tel"],
        .auth-form select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: none;
            border-radius: 5px;
            background: rgba(255, 255, 255, 0.2);
            color: #fff;
            font-size: 16px;
            outline: none;
            box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.3);
            transition: box-shadow 0.3s, background 0.3s;
        }

        .auth-form input:focus,
        .auth-form select:focus {
            background: rgba(255, 255, 255, 0.3);
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.5);
        }

        /* Кнопка */
        .auth-form button {
            width: 100%;
            padding: 12px;
            background: linear-gradient(90deg, #1d2671, #c33764);
            background-size: 400% 400%;
            animation: buttonAnimation 10s ease infinite;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            color: white;
            cursor: pointer;
            transition: transform 0.2s;
        }

        /* Анимация кнопки */
        @keyframes buttonAnimation {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .auth-form button:hover {
            transform: scale(1.05);
        }

        /* Ссылка */
        .auth-form a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #fff;
            text-decoration: none;
            font-size: 14px;
            opacity: 0.8;
            transition: opacity 0.3s;
        }

        .auth-form a:hover {
            opacity: 1;
        }

        /* Сообщение об ошибке */
        .error-message {
            display: block;
            width: 100%;
            text-align: center;
            font-size: 14px;
            color: #ff6b6b;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="centered-form">
    <form class="auth-form" action="/first-reg" method="post">
        <h1 class="centered-header">Регистрация</h1>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" required>
        <label for="surname">Фамилия:</label>
        <input type="text" id="surname" name="surname" required>
        <label for="email">Эл. почта:</label>
        <input type="email" id="email" name="email" required>
        <label for="phoneNumber">Номер телефона:</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" required>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password"  required>

        <label for="chosenRole">Роль:</label>
        <select name="chosenRole" required>
            <option value="" disabled selected>--Выбрать--</option>
            <option value="customer">Покупатель</option>
            <option value="developer">Продавец</option>
            <option value="agent">Агент</option>
        </select>


        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Зарегистрироваться</button>
        <a href="/login">Авторизоваться</a>
        <#if errorMessage??>
            <h2 style="color: red">${errorMessage}</h2>
        </#if>
    </form>
</div>

</body>
</html>