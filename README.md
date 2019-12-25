# ChatRoom Application
by Priyadharsini

# Description
A Spring boot chat room application implementation using WebSocket.

# Background
WebSocket is a communication protocol that makes it possible to establish a two-way communication channel between a server and a client.

# Features
1. Users will be able to Enter, Chat and Leave the chat room.
2. Users are notified when a new user enters the chatroom
3. Users are notified when an existing user leaves the chatroom
4. Users can post chat to all of the users in the chatroom

# Getting started
Have Java 1.8 installed
Have maven package installed
Clone the repository first
git clone https://github.com/priya-dharsinij/chatroom-websocket.git
Make sure http://localhost:8080 is available
Run using command
mvn spring-boot:run

# Testing code coverage (Selenium ChromeDriver)

The following tests are covered using Selenium ChromeDriver:
* Login: Title of the page (Chat Room Login) has been checked.
* Enter: Checked whether the user has joined the chat.  “Connected” message-content is verified.
* Chat: it is checked that a given user sends a message to the server and then receives it back from the server.
* Leave: Return to the login page when user leave the chat has been verified.
