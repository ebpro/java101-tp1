# TP3 — Collections & Streams

Ce module présente des exemples didactiques pour :

- List, Set, Map, Queue, Deque
- Comparaison (Comparable / Comparator)
- API Streams (création, transformations, collectors)
- Bonnes pratiques et pièges à éviter

## Structure
```
list/          Exemples sur List
set/           Exemples sur Set
map/           Exemples sur Map
queue/         Exemples sur Queue & Deque & PriorityQueue
comparable/    Tri avancé et comparateurs
streams/       API Streams (bases + collectors)
bestpractices/ Bonnes pratiques
Person.java    Modèle immutable
Book.java      Modèle pour tri/groupement
Demo.java      Lancement condensé
```

## Compilation & Exécution

```bash
./mvnw -q -pl tp3 -am test
./mvnw -q -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.Demo
```

JAR ombré :
```bash
./mvnw -q -pl tp3 -am package -Dshadedjar
java -jar tp3/target/tp3-0.0.1-SNAPSHOT-withdependencies.jar
```

## Points clés
- Préférez `List.of`, `Set.of`, `Map.of` pour immutabilité.
- `ArrayDeque` pour pile/queue (remplace `Stack`).
- Évitez les clés mutables dans les maps/sets.
- Utilisez `Comparator.comparing` + `thenComparing` pour tri multi-niveaux.
- Streams: chaîne claire d'opérations, éviter abus de `peek`.
- Collectors: `groupingBy`, `partitioningBy`, `joining`, `summarizingInt`.

## Ressources
- Cours: https://bruno.univ-tln.fr/notebooks/notebook-java-java101/06_java_101_L_Collections.html
- TP: https://bruno.univ-tln.fr/notebooks/notebook-java-java101/06_java_101_P_Collections.html

Bon travail !

