

1. Убедитесь, что у вас следующая структура папок:

ImagesToChar/
├── src/
│   └── java/
│       └── edu/
│           └── school21/
│               └── printer/
│                   ├── app/
│                   └── logic/
└── target/


2. Компиляция исходного кода:
    javac -d target src/java/school21/printer/app/*.java  src/java/school21/printer/logic/*.java

3. Запуск приложения:
    Укажите символы для белых (В примере ниже используется '.') и черных пикселей(В примере ниже используется '0'), а также путь к изображению:
    java -cp target src.java.school21.printer.app.Program . 0 /Users/melanief/IdeaProjects/Java_Bootcamp.Day04-1/src/ex00/ImagesToChar/it.bmp

4. Удаление скомпилированных файлов:
    rm -rf target/src