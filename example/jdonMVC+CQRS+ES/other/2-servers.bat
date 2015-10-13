@echo off
E:
CD E:\h2\bin
@start javaw -cp "h2-1.3.174.jar" org.h2.tools.Console %*
@if errorlevel 1 pause

start explorer E:\jetty\logs

start explorer http://localhost:8080/jdonmvc_cqrs_es-1.0/

CD E:\jetty
del logs\jdon.log
cls
@call jetty.bat
