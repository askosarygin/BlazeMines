## Описание
Игра для тренировки памяти, где нужно запоминать расположение огоньков, и потом на память их открывать, стараясь не нажать на ячейку с бомбочкой. Присутствуют настройки благодаря которым можно поменять фон, внешний вид бомбочек и огоньков.

## О проекте
Архитектура проекта старается следовать принципам Clean architecture, и делится на модули:

* app - модуль, который знает обо всех модулях в проекте и в котором создается основной DI компонент (Dagger2)
* common - модуль для хранения общей на проект информации: расширений классов, сущностей для обмена данными между слоями ui, domain, data, настроек темы, и ресурсов
* data - модуль для работы с внутренней БД
* *-ui - модули для хранений экранов и вью моделей для них, также хранят локальные (действующие в рамках модуля) DI компоненты
* *-domain - модули для выполнения бизнес логики и подтягивания данных из слоя data в слой ui

## Скриншоты

<img src="https://github.com/askosarygin/BlazeMines/assets/77168356/d21f41ec-4dd4-4657-97e0-bb1ddd574c27" alt="drawing" width="200"/>
<img src="https://github.com/askosarygin/BlazeMines/assets/77168356/06e29b34-2fc8-4949-ad96-91760eec8b5b" alt="drawing" width="200"/>
<img src="https://github.com/askosarygin/BlazeMines/assets/77168356/1dbf3368-2b50-4632-bcbf-92c00c8c6e90" alt="drawing" width="200"/>
<img src="https://github.com/askosarygin/BlazeMines/assets/77168356/e45fb9bf-f2a2-468d-9d4a-019f57ebeca2" alt="drawing" width="200"/>
<img src="https://github.com/askosarygin/BlazeMines/assets/77168356/b7264576-1037-43a9-94a0-f3177ed95d1c" alt="drawing" width="200"/>
<img src="https://github.com/askosarygin/BlazeMines/assets/77168356/1f345f4b-ae3b-477f-99b1-1958a628abc9" alt="drawing" width="200"/>
