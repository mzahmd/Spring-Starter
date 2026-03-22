# Zusammenfassung: Spring Boot 3 & Spring Framework 6

## 1. Kernkonzept: Der Spring-Container & Beans
Das Herzstück jeder Spring-Anwendung ist der Spring-Container. Er ist für das Erzeugen, Verwalten und Verknüpfen von Objekten zuständig, die in Spring als Beans bezeichnet werden.

### Wichtige Annotationen für Beans:
- `@Component`: Eine generische Komponente
- `@Service`: Markiert die Business-Logik
- `@Repository`: Markiert den Datenzugriff (Persistence Layer)
- `@Controller`: Wird für die Webschicht verwendet

### Beispiel für eine Bean:
```java
@Service
public class PhotoService {
    public void upload(byte[] bytes) {
        // Logik zum Hochladen von Fotos
    }
}
```

---

## 2. Dependency Injection (DI)
Dependency Injection ist das Muster, bei dem der Container die Abhängigkeiten eines Objekts zur Laufzeit bereitstellt.

### Arten der Injection:
- **Constructor Injection (empfohlen)**: Die Abhängigkeiten werden über den Konstruktor übergeben
- **Setter Injection**: Über Setter-Methoden
- **Field Injection**: Direkt über das Feld mit `@Autowired` (oft in Tests genutzt, aber im Code eher vermieden)

### Beispiel Constructor Injection:
```java
@Component
public class FsCommands {
    private final FileSystem fs;

    // @Autowired ist hier optional, wenn es nur einen Konstruktor gibt
    public FsCommands(FileSystem fs) {
        this.fs = fs;
    }
}
```

---

## 3. Konfiguration und Environment
Spring Boot ermöglicht die externe Konfiguration über Dateien wie `application.properties` oder `application.yml`.

### Zugriff auf Werte:
- `@Value`: Für einzelne Werte
- `@ConfigurationProperties`: Zum Mapping ganzer Hierarchien auf Java-Objekte (Typ-sicher)

### Beispiel application.properties:
```properties
com.tutego.homepage=http://tutego.com
com.tutego.timeout=5000
```

### Beispiel Zugriff mit @Value:
```java
@Service
class MyService {
    @Value("${com.tutego.homepage}")
    private String url;
}
```

---

## 4. Spring Data JPA
Spring Data abstrahiert den Datenbankzugriff durch Repositories. Man definiert oft nur ein Interface, und Spring generiert die Implementierung zur Laufzeit.

### Wichtige Repository-Typen:
- `CrudRepository`: Basis-Operationen (Create, Read, Update, Delete)
- `JpaRepository`: Bietet zusätzlich JPA-spezifische Funktionen wie Paging und Sortierung

### Abgeleitete Abfragemethoden (Derived Query Methods):
Spring kann SQL-Abfragen direkt aus dem Methodennamen generieren.

### Beispiel Repository:
```java
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // Generiert automatisch: SELECT * FROM Profile WHERE nickname = ?
    Optional<Profile> findByNickname(String nickname);

    // Generiert automatisch: SELECT * FROM Profile WHERE manelength > ?
    List<Profile> findByManelengthGreaterThan(short min);
}
```

---

## 5. Transaktionsmanagement
Mit der Annotation `@Transactional` stellt Spring sicher, dass eine Methode innerhalb einer Datenbanktransaktion ausgeführt wird. Tritt eine ungeprüfte Exception auf, wird ein Rollback durchgeführt.

### Beispiel:
```java
@Transactional
public void updateManeLength(long id, int newLength) {
    Profile profile = em.find(Profile.class, id);
    profile.setManelength((short) newLength);
    // Änderungen werden am Ende der Methode automatisch gespeichert (Dirty Checking)
}
```

---

## 6. Testing
Spring Boot bietet umfassende Unterstützung für Tests, insbesondere mit JUnit 5, AssertJ und Mockito.

### Wichtige Annotationen:
- `@SpringBootTest`: Lädt den vollständigen Application Context für Integrationstests
- `@Mock`: Erstellt ein Mock-Objekt mit Mockito

### Beispiel Test mit Mockito:
```java
@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {
    @Mock
    private FileSystem fileSystem; // Simuliertes Dateisystem

    @Test
    void testUpload() {
        // Verhalten definieren
        given(fileSystem.getFreeDiskSpace()).willReturn(1000L);
        // ... Test ausführen
    }
}
```

---

## 7. Validierung
Über Jakarta Bean Validation können Felder von Beans direkt validiert werden (z. B. `@NotNull`, `@Min`, `@Pattern`).

### Beispiel Validierung:
```java
public class Photo {
    @NotNull
    @Pattern(regexp = "[\\w_-]{1,200}")
    public String name;

    @Min(1)
    public long profileId;
}
```