const socket = new WebSocket("ws://localhost:8080/chat");

// Обработка события открытия соединения
socket.onopen = () => {
    console.log("Connected to WebSocket");
};

// Обработка входящих сообщений
socket.onmessage = (event) => {
    console.log("Received message: ", event.data);

    const messagesElement = document.getElementById('messages');
    if (messagesElement) {
        if (event.data.startsWith('USER_LIST:')) {
            // Обработка сообщения со списком пользователей
            const userList = event.data.substring('USER_LIST:'.length).split(',');
            updateUsers(userList);
        } else {
            // Добавляем сообщение в элемент с ID 'messages'
            const messageElement = document.createElement('p');
            messageElement.textContent = event.data;
            messagesElement.appendChild(messageElement);
        }
    } else {
        console.log("Element with ID 'messages' not found");
    }
};

// Обработка закрытия соединения
socket.onclose = () => {
    console.log("WebSocket connection closed");
};

// Обработка ошибок WebSocket
socket.onerror = (error) => {
    console.log("WebSocket error: ", error);
};

// Обновление списка пользователей
function updateUsers(userList) {
    const userListElement = document.getElementById('userList');
    userListElement.innerHTML = ''; // Очистка предыдущего списка

    userList.forEach(userId => {
        const userItem = document.createElement('li');
        userItem.textContent = userId;
        userItem.onclick = () => selectUser(userId); // Устанавливаем обработчик выбора
        userListElement.appendChild(userItem);
    });
}

// Выбор пользователя для отправки сообщения
function selectUser(userId) {
    const messageInput = document.getElementById('messageInput');
    messageInput.placeholder = `Message to ${userId}`;
    messageInput.dataset.recipientId = userId; // Устанавливаем recipientId
    console.log("Selected user ID: ", userId); // Логирование для отладки
}

// Отправка сообщения
function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const recipientId = messageInput.dataset.recipientId;
    const message = messageInput.value.trim();

    console.log("Recipient ID: ", recipientId);
    console.log("Message: ", message);

    if (!recipientId || !message) {
        console.error("Recipient ID or message is missing");
        return; // Выход из функции, если recipientId или message отсутствуют
    }

    const formattedMessage = `${recipientId}:${message}`;
    console.log("Sending message: ", formattedMessage);
    socket.send(formattedMessage);
    messageInput.value = ''; // Очистить поле ввода после отправки
}
