cd /d "%~dp0"
if exist mvnw.cmd (
    call mvnw.cmd clean spring-boot:run
) else (
    call mvn clean spring-boot:run
)