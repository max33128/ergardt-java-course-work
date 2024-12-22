<!DOCTYPE html>
<html>
<head>
    <title>SmartEstate</title>
    <style>
        /* Основные стили */
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #1d2671, #c33764);
            background-size: 400% 400%;
            animation: gradientAnimation 10s ease infinite;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            color: #fff;
        }

        @keyframes gradientAnimation {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
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

        h1 {
            margin-top: 40px;
            font-size: 28px;
            text-align: center;
        }

        table {
            width: 80%;
            margin-top: 30px;
            border-collapse: collapse;
            text-align: left;
        }

        table th, table td {
            padding: 10px;
            border: 1px solid #fff;
        }

        table th {
            background-color: #1d2671;
        }

        table tr:nth-child(even) {
            background-color: rgba(255, 255, 255, 0.1);
        }

        .edit-button, .add-button, .delete-button, .sort-button {
            display: inline-block;
            background-color: #1d2671;
            color: #fff;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s, transform 0.2s;
        }

        .edit-button:hover, .add-button:hover, .delete-button:hover, .sort-button:hover {
            background-color: #c33764;
            transform: scale(1.05);
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.7);
        }

        .modal-content {
            background-color: #000000;
            margin: 15% auto;
            padding: 20px;
            border-radius: 10px;
            width: 80%;
            max-width: 500px;
            text-align: center;
        }

        .modal-content input {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #fff;
            background: rgba(255, 255, 255, 0.1);
            color: #fff;
        }

        .modal-content button {
            padding: 10px 20px;
            background-color: #1d2671;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .modal-content button:hover {
            background-color: #c33764;
        }
    </style>

    <script>
        // Показ модального окна
        function showModal(modalId) {
            document.getElementById(modalId).style.display = "block";
        }

        // Закрытие модального окна
        function closeModal(modalId) {
            document.getElementById(modalId).style.display = "none";
        }
    </script>
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

<h1>Список объектов</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Покупатель</th>
        <th>Продавец</th>
        <th>Объект</th>
        <th>Сумма</th>
        <th>Действия</th>
    </thead>
    <tbody>
    <#list transactions as transaction>
        <tr>
            <td>${transaction.id}</td>
            <td>${transaction.customer.email}</td>
            <td>${transaction.developer.email}</td>
            <td>${transaction.object.address}</td>
            <td>${transaction.summary}</td>
            <td>
            <#if user.isAgent() || user.isAdmin()>
                <a class="delete-button" href="#" onclick="document.getElementById('deleteForm').submit();">Удалить</a>
                <form id="deleteForm" action="/transactions/delete/${transaction.id}" method="post" style="display: none;">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input class="input_exit" type="submit" value="Удалить"/>
                </form>
            </#if>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<#if user.isAgent()>
    <a class="add-button" style="margin: 0.5em 0;" href="#" onclick="showModal('addModal')">Создать новую транзакцию</a>
    <div id="addModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal('addModal')">&times;</span>
            <form action="/transactions/add" method="post">
                <h2>Добавить транзакцию</h2>
                <label>ID полкупателя:</label>
                <input type="text" name="customerTextId" required>
                <label>ID продавца:</label>
                <input type="text" name="developerTextId" required>
                <label>ID объекта:</label>
                <input type="text" name="developerTextId">
                <label>Сумма:</label>
                <input type="text" name="summary" required>
            </form>
        </div>
    </div>
</#if>
</body>
</html>