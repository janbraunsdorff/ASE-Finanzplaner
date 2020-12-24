clear
./gradlew test
./gradlew customFatJar --refresh-dependencies
clear
java -jar build/libs/ASE-Finanzplaner-1.0-SNAPSHOT.jar

