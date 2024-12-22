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

        // Заполнение формы редактирования
        function fillEditModal(element) {
            const modal = document.getElementById("editModal");

            modal.querySelector("#editId").value = element.getAttribute("data-id");
            modal.querySelector("#editAddress").value = element.getAttribute("data-address");
            modal.querySelector("#editSquare").value = element.getAttribute("data-square");
            modal.querySelector("#editPrice").value = element.getAttribute("data-price");
            modal.querySelector("#editFloor").value = element.getAttribute("data-floor");
            modal.querySelector("#editNumberOfRooms").value = element.getAttribute("data-numberOfRooms");
            modal.querySelector("#editTypeOfHousing").value = element.getAttribute("data-typeOfHousing");
            modal.querySelector("#editStateOfObject").value = element.getAttribute("data-stateOfObject");
            modal.querySelector("#editDescription").value = element.getAttribute("data-description");

            showModal("editModal");
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
        <th>Адрес</th>
        <th>Площадь</th>
        <th>Цена</th>
        <th>Этаж</th>
        <th>Количество комнат</th>
        <th>Тип объекта</th>
        <th>Статус объекта</th>
        <th>Агент</th>
        <th>Продавец</th>
        <th>Описание</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <#list objects as object>
        <tr>
            <td>${object.id}</td>
            <td>${object.address}</td>
            <td>${object.square}</td>
            <td>${object.price}</td>
            <td>${object.floor}</td>
            <td>${object.numberOfRooms}</td>
            <td>${object.typeOfHousing}</td>
            <td>${object.stateOfObject}</td>
            <td>${object.agent.email}</td>
            <td>${object.developer.email}</td>
            <td>${object.description}</td>
            <td>
                <#if ((user.id == object.developer.id) && user.isDeveloper()) || ((user.id == object.agent.id) && user.isAgent()) || user.isAdmin()>
                    <a class="edit-button" href="#"
                       data-id="${object.id}"
                       data-address="${object.address}"
                       data-square="${object.square}"
                       data-price="${object.price}"
                       data-floor="${object.floor}"
                       data-numberOfRooms="${object.numberOfRooms}"
                       data-typeOfHousing="${object.typeOfHousing}"
                       data-stateOfObject="${object.stateOfObject}"
                       data-description="${object.description}"
                       onclick="fillEditModal(this)">
                        Редактировать
                    </a>
                </#if>
                <#if ((user.id == object.agent.id) && user.isAgent()) || user.isAdmin()>
                    <a class="delete-button" href="#" onclick="document.getElementById('deleteForm').submit();">Удалить</a>
                    <form id="deleteForm" action="/objects/delete/${object.id}" method="post" style="display: none;">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input class="input_exit" type="submit" value="Удалить"/>
                    </form>
                </#if>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<#if user.isNotCustomer()>
    <a class="add-button" style="margin: 0.5em 0;" href="#" onclick="showModal('addModal')">Создать новую запись</a>
    <div id="addModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal('addModal')">&times;</span>
            <form action="/objects/add" method="post">
                <h2>Добавить объект</h2>
                <label>Адрес:</label>
                <input type="text" name="address" required>
                <label>Площадь:</label>
                <input type="text" name="square" required>
                <label>Цена:</label>
                <input type="text" name="price">
                <label>Этаж:</label>
                <input type="number" name="floor" min="1" required>
                <label>Количество комнат:</label>
                <input type="text" name="numberOfRooms" required>
                <br><br>
                <#if user.isDeveloper() || user.isAdmin()>
                    <label>ID агента:</label>
                    <input type="text" name="agentText" required>
                    <br><br>
                </#if>
                <#if user.isAgent() || user.isAdmin()>
                    <label>ID продавца:</label>
                    <input type="number" name="developerText" required>
                </#if>
                <label>Описание:</label>
                <input type="text" name="description" required>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit">Добавить</button>
            </form>
        </div>
    </div>
</#if>

<div id="editModal" class="modal">
    <div class="modal-content">
        <span onclick="closeModal('editModal')" style="cursor: pointer; float: right; color: white;">&times;</span>
        <form action="/objects/edit" method="post" onsubmit="event.preventDefault(); submitEditForm();">
            <input type="hidden" id="editId" name="id">
            <label>Адрес</label>
            <input type="text" id="editAddress" name="address" required>
            <label>Площадь</label>
            <input type="text" id="editSquare" name="square" required>
            <label>Цена</label>
            <input type="text" id="editPrice" name="price" required>
            <label>Этаж</label>
            <input type="number" id="editFloor" name="floor" required>
            <label>Количество комнат</label>
            <input type="text" id="editNumberOfRooms" name="numberOfRooms" required>
            <label>Тип объекта</label>
            <input type="text" id="editTypeOfHousing" name="typeOfHousing" required>
            <label>Статус объекта</label>
            <input type="text" id="editStateOfObject" name="stateOfObject" required>
            <label>Описание</label>
            <input type="text" id="editDescription" name="description" required>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Сохранить изменения</button>
        </form>
    </div>
</div>
</body>
</html>