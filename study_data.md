## Web Reactive

Относительно недавно, начиная с **Spring 5.0** (выпущен в 2017 году), в Spring появился новый подход к обработке HTTP-запросов — **Web Reactive**.  
В основе этого подхода лежат не сервлеты, а такие технологии, как **Netty** или **Undertow**, предоставляющие альтернативные API, отличные от сервлетов.

Подробнее о **Web Reactive** можно прочитать в [документации Spring](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html).

## Application server

В документации **Oracle** по Java и в профессиональных материалах (например, [тут](https://www.jetbrains.com/help/idea/deploying-a-web-app-into-an-app-server-container.html)) в местах, где вы ожидаете увидеть термины *servlet container* или *web container*, вы часто встретите термин **application server** (*сервер приложений*). На самом деле, любой application server включает в себя servlet container.

Разница в том, что **servlet container** работает только с сервлетами, а **application server** — это целая экосистема: одновременно контейнер сервлетов и среда поддержки различных технологий Java EE/Jakarta EE. Если провести аналогию с реальным миром:
- **Servlet container** — это рабочий кабинет.
- **Application server** — это целый бизнес-центр с офисами, переговорными, кафе, ресепшеном и парковкой.

Подробнее о технологии **Executable Jar** можно узнать в [документации Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html).

## Работа по проекту

Ниже представлено сравнение того, что нужно настроить в случае использования **SpringPlain** против **SpringBoot**:

| SpringPlain | SpringBoot |
| ----------- | ---------- |
| + Добавлена зависимость от embedded Tomcat → создал сервер, запустил Tomcat | *Ничего не нужно, Spring Boot сам поднимает веб-сервер* |
| + Настроен `application.properties` вручную (так как нет автоконфигурации Spring Boot) | + Подключены starter'ы, которые гарантируют автоконфигурацию. `application.properties` подхватывается «из коробки» |
| + Добавлена зависимость на Jackson в pom.xml | + По умолчанию Jackson есть в Spring Boot (через `spring-boot-starter-json` и `spring-boot-starter-web`) |
| + Аннотация `@EnableWebMVC` добавлена вручную | - Не требуется, в Spring Boot присутствует автоконфигурация |
| + Добавлена зависимость для логирования, настроен `logback.xml`, настроены логгеры Tomcat | + По умолчанию есть logback, настройка логгеров через `application.properties` |
| + Добавлен в конфигурацию запуск скриптов БД `schema.sql`, сконфигурированы `datasource` | + В Spring Boot поддержка скриптов БД есть по умолчанию, нужно просто поместить `schema.sql` в `resources` |

Таким образом, Spring Boot значительно упрощает конфигурацию и избавляет разработчика от большого числа ручных настроек, необходимых при работе со Spring MVC в «чистом» виде.


