# Étape 1 : Utiliser une image contenant Maven et OpenJDK 17 pour la compilation
FROM maven:3.8.4-openjdk-17 AS builder

# Définir le dossier de travail dans le conteneur
WORKDIR /rest2

# Copier les fichiers Maven pour utiliser le cache des dépendances
COPY pom.xml .
COPY src ./src

# Compiler l’application et générer un fichier .jar
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image plus légère pour exécuter l’application
FROM openjdk:17

# Définir le dossier de travail
WORKDIR /rest2

# Copier le fichier JAR généré depuis l’étape de build
COPY --from=builder /rest2/target/*.jar rest2.jar

# Exposer le port (ajuster pour chaque service)
EXPOSE 8080

# Lancer l’application
ENTRYPOINT ["java", "-jar", "rest2.jar"]