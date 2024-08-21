@echo off
echo Starting 'chatty' application...

REM Переход в папку с проектом 'chatty'
cd /d "%~dp0\thirdOption"

REM Запуск Spring Boot приложения
mvn spring-boot:run

pause
