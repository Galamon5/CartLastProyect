javac -encoding UTF-8 -cp build/classes src/ImagePanel.java -d build/classes
javac -encoding UTF-8 -cp build/classes src/Product.java -d build/classes
javac -encoding UTF-8 -cp build/classes src/utils.java -d build/classes
javac -encoding UTF-8 -cp build/classes src/Server.java -d build/classes
javac -encoding UTF-8 -cp build/classes src/ventanaPrincipal.java -d build/classes
java -Dfile.encoding=UTF-8 -cp build/classes ventanaPrincipal
REM java -Dfile.encoding=UTF-8 -cp build/classes Server
