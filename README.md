# Who Wants to Be a Millionaire Clone

<p align="center">
  <img src="https://github.com/loretor/Quiz-game/blob/main/webApp/src/main/resources/static/images/logo.png" />
</p>

This project was created to test my knowledge of Java Spring and Spring Boot.

## Project overview

The project consists of:
- A backend that handles all requests from the web application.
- A web application following the MVC paradigm of Java Spring.
All requests are processed by the web application, which then calls the necessary backend methods.

The application includes:
- AJAX calls to fetch information in JSON format.
- HTTP calls to navigate between different views.
The frontend is built using HTML, CSS, and some JavaScript for handling AJAX requests and frontend logic. Additionally, Thymeleaf is used to pass information from the web application to the frontend views.

## Game rules

As in the original game, the user must correctly answer 15 questions.

The user has access to four lifelines:
- 50-50: Removes two incorrect answers.
- Phone a Friend: Allows the user to browse the internet for 1 minute.
- Double Dip: Must be selected before answering. It grants the user a second chance to answer.
- Ask the Audience: Provides help from the audience regarding the possible answers.

## Future improvements

Currently, the game consists of 15 AI-generated questions.
A possible improvement to enhance replayability could be developing a small Python app that queries an LLM to generate questions in a specific JSON format. The app would dynamically create new questions based on the desired difficulty level.

