# TP3 — Collections & Streams

Ce module illustre les concepts avancés et les bonnes pratiques du Framework Collections de Java ainsi que l'API Streams.

## Objectifs pédagogiques

- Choisir la bonne structure de données (List, Set, Map, Queue, Deque)
- Comprendre les caractéristiques de performance et les complexités
- Maîtriser Comparable et Comparator pour l'ordre naturel et personnalisé
- Exploiter l'API Streams (création, opérations intermédiaires, opérations terminales, collectors)
- Appliquer les bonnes pratiques : immutabilité, copie défensive, sélection d'implémentation adaptée
- Éviter les pièges courants (modification pendant itération, clés mutables, mauvaise gestion des null)

## Parcours recommandé

1. `list.ListExamples` — opérations fondamentales sur les listes
2. `set.SetExamples` — unicité, ordre et opérations ensemblistes
3. `map.MapExamples` — associations clé/valeur et méthodes modernes (compute, merge...)
4. `queue.QueueExamples` — FIFO, LIFO, PriorityQueue, fenêtres glissantes
5. `comparable.ComparatorExamples` — stratégies de tri avancées
6. `streams.StreamBasicsExamples` — création et opérations intermédiaires (appliquées aux collections)
7. `streams.StreamCollectorsExamples` — opérations terminales et collectors (appliquées aux collections)
8. `bestpractices.CollectionBestPractices` — synthèse des bonnes pratiques

## Lancer les démonstrations

Compilez et exécutez une classe spécifique :

```bash
./mvnw -q -pl tp3 -am compile
./mvnw -q -pl tp3 exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.list.ListExamples
```

Ou créez un JAR ombré :

```bash
./mvnw -q -pl tp3 -am package -Dshadedjar
java -jar tp3/target/tp3-0.0.1-SNAPSHOT-withdependencies.jar
```

## Commandes rapides {#run-commands}

Voici des commandes pratiques pour exécuter le démonstrateur principal et d'autres exemples optionnels.

### Démo principale (TP3 — par défaut) {#run-demo}

- Exécution rapide via le plugin exec (sans générer le jar ombré) :

```bash
./mvnw -q -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.Demo
```

- Générer le JAR ombré (profil parent `shadedjar`) et l'exécuter :

```bash
./mvnw -pl tp3 -am -Pshadedjar -Dapp.main.class=fr.univtln.bruno.samples.java101.tp3.Demo -DskipTests=true package
java -jar tp3/target/tp3-0.0.1-SNAPSHOT-withdependencies.jar
```

### Exemple Guava (Multimap) {#run-guava}

- Avec `exec:java` :

```bash
./mvnw -q -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.guava.GuavaExamples
```

- Via le JAR ombré :

```bash
./mvnw -pl tp3 -am -Pshadedjar -Dapp.main.class=fr.univtln.bruno.samples.java101.tp3.guava.GuavaExamples -DskipTests=true package
java -jar tp3/target/tp3-0.0.1-SNAPSHOT-withdependencies.jar
```

### Exemple Eclipse Collections (primitives) {#run-eclipse}

- Avec `exec:java` :

```bash
./mvnw -q -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.eclipse.EclipseCollectionsExamples
```

- Via le JAR ombré :

```bash
./mvnw -pl tp3 -am -Pshadedjar -Dapp.main.class=fr.univtln.bruno.samples.java101.tp3.eclipse.EclipseCollectionsExamples -DskipTests=true package
java -jar tp3/target/tp3-0.0.1-SNAPSHOT-withdependencies.jar
```

### Programmation fonctionnelle appliquée aux collections (Streams) {#run-parallel}

> Note : la programmation fonctionnelle n'est ici abordée que dans le cadre des collections et de l'API Streams (pipeline, collectors, parallélisme mesuré).

- Exemples à consulter / exécuter :
  - `streams.StreamBasicsExamples` — map, filter, flatMap appliqués à des collections
  - `streams.StreamCollectorsExamples` — collectors et agrégations sur collections
  - `streams.ParallelStreamsExamples` — parallélisation des pipelines (attention aux effets de bords)

- Commandes :

```bash
# stream basics
./mvnw -q -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.functionnal.StreamBasicsExamples

# collectors
./mvnw -q -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.functionnal.StreamCollectorsExamples

# parallel streams (à mesurer avant usage)
./mvnw -q -pl tp3 -am exec:java -Dexec.mainClass=fr.univtln.bruno.samples.java101.tp3.functionnal.ParallelStreamsExamples
```

## Liens vers le cours

- Cours : https://bruno.univ-tln.fr/notebooks/notebook-java-java101/06_java_101_L_Collections.html
- Pratique : https://bruno.univ-tln.fr/notebooks/notebook-java-java101/06_java_101_P_Collections.html

## Tests

Les tests valident les comportements essentiels (ex: `PersonTest`). Lancez :

```bash
./mvnw -q test -pl tp3
```

## Points d'attention

- Utilisez `List.of`, `Set.of`, `Map.of` pour créer des collections immuables.
- Préférez `ArrayDeque` à `Stack` (legacy) pour LIFO.
- N'utilisez pas de clés mutables dans HashMap / HashSet.
- Préparez la capacité initiale quand la taille est connue pour réduire les reallocations (`new ArrayList<>(expectedSize)`).
- Évitez `Arrays.asList()` si vous devez modifier la taille (liste de taille fixe).

---

## Librairies alternatives : Guava & Eclipse Collections (rappel et exemples)

Cette section compare rapidement deux bibliothèques de collections tierces souvent utiles en production et propose des mini-exemples pour se faire une idée.

Pourquoi les envisager :

- Certaines API historiques (Guava) ou orientées performance/prise en charge mémoire (Eclipse Collections) offrent des structures et utilitaires absents de la JDK ou plus optimisés.
- Elles peuvent simplifier le code (primitives collections, Multimap, Bags, Fluent APIs) ou améliorer la performance sur des workloads spécifiques.

Guava (Google) — points clés :

- Fournit `ImmutableList`, `ImmutableSet`, `ImmutableMap`, `Multimap`, `BiMap`, `Table`, `Hashes`.
- API ergonomique et bien documentée.
- Exemple : Multimap pour associer plusieurs valeurs à une seule clé.

```java
// Exemple Guava Multimap
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

Multimap<String, String> mm = ArrayListMultimap.create();
mm.put("key1", "v1");
mm.put("key1", "v2");
mm.put("key2", "v3");

for (String k : mm.keySet()) {
  System.out.println(k + " -> " + mm.get(k));
}
```

Eclipse Collections — points clés :

- Collections optimisées et API riche (primitive lists/maps, Bags, multimaps).
- Très performant pour les opérations sur collections primitives (int/long) grâce aux collections spécialisées.
- Exemple : IntArrayList et RichIterable operations.

```java
// Exemple Eclipse Collections (int primitive list)
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.api.list.primitive.IntList;

IntList list = IntArrayList.newListWith(1,2,3,4,5);
int sum = list.sum();
System.out.println("sum=" + sum);
```

Critères de choix (pratique) :

- Besoin d'API (Multimap, BiMap, Table) → Guava si vous voulez une bibliothèque légère et bien intégrée.
- Performance sur primitives / gros volumes → Eclipse Collections pour ses collections primitives spécialisées.
- Immutabilité & sécurité thread-safe → JDK `List.of` / `Collections.unmodifiable*` ou `Immutable*` de Guava selon préférences.
- Dépendances & maintenance → Guava est largement utilisée, Eclipse Collections est très active pour cas de performance. Choisissez selon la maturité et la contrainte de dépendances du projet.

Conseil : commencez par la JDK ; n'ajoutez Guava/Eclipse Collections que si vous avez un besoin concret (API manquante, profil de performance mesuré).

---

Bon apprentissage !
