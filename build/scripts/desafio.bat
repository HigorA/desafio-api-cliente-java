@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  desafio startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and DESAFIO_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\desafio-0.0.1-SNAPSHOT-plain.jar;%APP_HOME%\lib\spring-boot-devtools-3.0.3.jar;%APP_HOME%\lib\spring-boot-starter-web-3.0.3.jar;%APP_HOME%\lib\spring-boot-starter-webflux-3.0.3.jar;%APP_HOME%\lib\spring-data-commons-3.0.2.jar;%APP_HOME%\lib\spring-boot-starter-json-3.0.3.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.14.2.jar;%APP_HOME%\lib\springdoc-openapi-starter-webmvc-ui-2.0.2.jar;%APP_HOME%\lib\springdoc-openapi-starter-webmvc-api-2.0.2.jar;%APP_HOME%\lib\springdoc-openapi-starter-common-2.0.2.jar;%APP_HOME%\lib\swagger-core-jakarta-2.2.7.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.14.2.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.14.2.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.14.2.jar;%APP_HOME%\lib\jackson-databind-2.14.2.jar;%APP_HOME%\lib\swagger-models-jakarta-2.2.7.jar;%APP_HOME%\lib\jackson-annotations-2.14.2.jar;%APP_HOME%\lib\jackson-core-2.14.2.jar;%APP_HOME%\lib\jackson-dataformat-csv-2.14.2.jar;%APP_HOME%\lib\json-20220320.jar;%APP_HOME%\lib\opencsv-5.7.1.jar;%APP_HOME%\lib\gson-2.10.1.jar;%APP_HOME%\lib\spring-boot-starter-3.0.3.jar;%APP_HOME%\lib\spring-boot-autoconfigure-3.0.3.jar;%APP_HOME%\lib\spring-boot-3.0.3.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-3.0.3.jar;%APP_HOME%\lib\spring-webmvc-6.0.5.jar;%APP_HOME%\lib\spring-webflux-6.0.5.jar;%APP_HOME%\lib\spring-web-6.0.5.jar;%APP_HOME%\lib\spring-boot-starter-reactor-netty-3.0.3.jar;%APP_HOME%\lib\spring-context-6.0.5.jar;%APP_HOME%\lib\spring-aop-6.0.5.jar;%APP_HOME%\lib\spring-beans-6.0.5.jar;%APP_HOME%\lib\spring-expression-6.0.5.jar;%APP_HOME%\lib\spring-core-6.0.5.jar;%APP_HOME%\lib\webjars-locator-core-0.52.jar;%APP_HOME%\lib\spring-boot-starter-logging-3.0.3.jar;%APP_HOME%\lib\logback-classic-1.4.5.jar;%APP_HOME%\lib\log4j-to-slf4j-2.19.0.jar;%APP_HOME%\lib\jul-to-slf4j-2.0.6.jar;%APP_HOME%\lib\slf4j-api-2.0.6.jar;%APP_HOME%\lib\commons-text-1.10.0.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\commons-beanutils-1.9.4.jar;%APP_HOME%\lib\commons-collections4-4.4.jar;%APP_HOME%\lib\swagger-ui-4.15.5.jar;%APP_HOME%\lib\classgraph-4.8.149.jar;%APP_HOME%\lib\jakarta.annotation-api-2.1.1.jar;%APP_HOME%\lib\snakeyaml-1.33.jar;%APP_HOME%\lib\tomcat-embed-websocket-10.1.5.jar;%APP_HOME%\lib\tomcat-embed-core-10.1.5.jar;%APP_HOME%\lib\tomcat-embed-el-10.1.5.jar;%APP_HOME%\lib\micrometer-observation-1.10.4.jar;%APP_HOME%\lib\reactor-netty-http-1.1.3.jar;%APP_HOME%\lib\reactor-netty-core-1.1.3.jar;%APP_HOME%\lib\reactor-core-3.5.3.jar;%APP_HOME%\lib\spring-jcl-6.0.5.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\micrometer-commons-1.10.4.jar;%APP_HOME%\lib\netty-codec-http2-4.1.89.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.89.Final.jar;%APP_HOME%\lib\netty-resolver-dns-native-macos-4.1.89.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-resolver-dns-classes-macos-4.1.89.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.89.Final-linux-x86_64.jar;%APP_HOME%\lib\reactive-streams-1.0.4.jar;%APP_HOME%\lib\logback-core-1.4.5.jar;%APP_HOME%\lib\log4j-api-2.19.0.jar;%APP_HOME%\lib\netty-handler-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-classes-epoll-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-4.1.89.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.89.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.89.Final.jar;%APP_HOME%\lib\netty-common-4.1.89.Final.jar;%APP_HOME%\lib\swagger-annotations-jakarta-2.2.7.jar;%APP_HOME%\lib\jakarta.xml.bind-api-4.0.0.jar;%APP_HOME%\lib\jakarta.validation-api-3.0.2.jar;%APP_HOME%\lib\jakarta.activation-api-2.1.1.jar


@rem Execute desafio
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %DESAFIO_OPTS%  -classpath "%CLASSPATH%" br.com.desafio.DesafioApplication %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable DESAFIO_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%DESAFIO_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
