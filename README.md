 SimpleList
 ======
 Простой список заметок с загрузкой изображений из сети интернет.
 
 Описание
 -------
 Приложение направленно на создание заметок с использованием RecyclerView для построения списка. Каждый объект списка представляет из себя - наименование, краткое описание,
изображение и приоритет объекта в списке, описываемый числом от 1 до 10. Список отсортирован по приоритету.
 При добавлении нового объекта в список, требуется добавить URL ссылку на изображение из сети интернет, она будет загружена при помощи библиотеки Glide, в формате Bitmap,
и конвертирована в ByteArray при помощи TypeConverter, после помещена в Room, таким образом в дальнейшем не потребуется подключение к сети интернет, т.к. картинка будет
храниться в БД и загружаться из локального хранилища.
 
 Реализация
 -------
 Данное приложение реализовано на языке Kotlin, при помощи стандартых библиотек Android, паттернов MVVM, Clean Architecture и Repository, архитектурных компонентов -
Navigation, ViewBinding, Room. Так же в проекте были использованы RecyclerView и библиотека Glide.
