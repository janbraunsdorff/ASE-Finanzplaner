./gradlew test --stacktrace
./gradlew build --refresh-dependencies --stacktrace

mkdir -p ./app
mkdir -p  ./app/output
cp -R ./src/main/resources/resources ./app/output/resources/
cp ./build/libs/ASE-Finanzplaner-1.0-SNAPSHOT.jar ./app/app.jar

java --enable-preview -jar ./app/app.jar

