# Étape 1 : Utiliser une image contenant Maven et OpenJDK 17 pour la compilation
FROM maven:3.8.4-openjdk-17 AS builder

# Définir le dossier de travail dans le conteneur
WORKDIR /rest1

# Copier les fichiers Maven pour utiliser le cache des dépendances
COPY pom.xml .
COPY src ./src

# Compiler l’application et générer un fichier .jar
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image plus légère pour exécuter l’application
FROM openjdk:17

# Définir le dossier de travail
WORKDIR /rest1

# Copier le fichier JAR généré depuis l’étape de build
COPY --from=builder /rest1/target/*.jar rest1.jar

# Exposer le port (ajuster pour chaque service)
EXPOSE 8081

# Lancer l’application
ENTRYPOINT ["java", "-jar", "rest1.jar"]
