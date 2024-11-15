# Guía de Lombok en Java con Maven

Este proyecto sirve como un apunte personal y guía de referencia sobre el uso de Lombok en Java. Incluye pruebas y ejemplos de distintas anotaciones que facilita la biblioteca de Lombok para mejorar la productividad y reducir código repetitivo.

## ¿Qué es Lombok?

Project Lombok es una biblioteca de Java que se conecta automáticamente a tu editor y herramientas de construcción, mejorando tu código Java. Nunca vuelvas a escribir otro método getter o equals, con una anotación tu clase tiene un constructor completo, automatiza tus variables de registro y mucho más.

### Beneficios principales:
- Reduce el código repetitivo
- Aumenta la legibilidad del código
- Disminuye el tiempo de desarrollo
- Reduce la probabilidad de errores
- Facilita el mantenimiento del código

### Implementación en proyecto Maven

Para implementar Lombok en un proyecto Java con Maven, sigue estos pasos:

1. **Agregar la dependencia en el archivo `pom.xml`**
2. **Instalar el plugin de Lombok en tu IDE**
   - Para IntelliJ IDEA: File → Settings → Plugins → Buscar "Lombok" → Instalar
   - Para Eclipse: Ejecutar el jar de Lombok y seguir el asistente de instalación
3. **Habilitar el procesamiento de anotaciones en tu IDE**
4. **Comenzar a usar las anotaciones de Lombok en tus clases**

## Dependencias Utilizadas

```xml
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.36</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.5.12</version>
    </dependency>
</dependencies>
```

## Aclaración

**En el mismo codigo del proyecto, encontrará comentarios a modo de apuntes, que refuerzan la guia en el uso de Lombok en caso de requerirlo, recomendamos igualmente revisarlo**.

## Tabla de Contenidos
- [Getter y Setter](#getter-y-setter)
- [Setter en Nivel de Clase o Atributo](#setter-en-nivel-de-clase-o-atributo)
- [Constructores](#constructores)
- [FieldDefaults](#fielddefaults)
- [ToString](#tostring)
- [EqualsAndHashCode](#equalsandhashcode)
- [Data](#data)
- [Builder](#builder)
- [Value](#value)
- [RequiredArgsConstructor](#requiredargsconstructor)
- [Logs](#logs)
- [Cleanup](#cleanup)
- [SneakyThrows](#sneakythrows)

## Getter y Setter

**Descripción**: Generan automáticamente los métodos *getter* y *setter* para los atributos de una clase.

**Configuración**:
* Por defecto, los métodos son públicos.
* Para modificar el nivel de acceso, usa `@Getter(value = AccessLevel.PROTECTED)`.

**Ejemplo**:
```java
@Getter(AccessLevel.PROTECTED)
private String nombre;
```

## Setter en Nivel de Clase o Atributo

* Si se coloca en la clase, aplica a todos los atributos.
* Si se coloca en un atributo específico, solo ese tendrá el *setter*.

**Ejemplo**:
```java
@Setter // Aplica a toda la clase
public class Persona {
    private String nombre;
}
```

## Constructores

* **@AllArgsConstructor**: Genera un constructor con todos los atributos.
* **@NoArgsConstructor**: Genera un constructor vacío.

**Configuración**:
* Ambos tienen la propiedad `access` para definir su nivel de acceso.

**Ejemplo**:
```java
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Persona {
    // ...
}
```

## FieldDefaults

**Descripción**: Define los modificadores de acceso para todos los atributos de la clase en una sola línea.

**Configuración**:
* `level = AccessLevel.PRIVATE` establece los atributos como privados.
* `makeFinal = true` convierte los atributos en constantes.

**Ejemplo**:
```java
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Persona {
    // ...
}
```

## ToString

**Descripción**: Genera el método `toString` con los atributos de la clase.

**Configuración**:
* `exclude = {"nombre"}` excluye ciertos atributos.
* `includeFieldNames = false` oculta los nombres de los atributos.
* `of = {"nombre", "edad"}` solo incluye ciertos atributos.

**Ejemplo**:
```java
@ToString(exclude = {"password"})
public class Persona {
    // ...
}
```

## EqualsAndHashCode

**Descripción**: Genera los métodos `equals` y `hashCode`.

**Configuración**:
* `exclude` y `of` permiten incluir o excluir atributos específicos.
* `doNotUseGetters = true` omite el uso de *getters*.

**Ejemplo**:
```java
@EqualsAndHashCode(of = {"nombre", "edad"})
public class Persona {
    // ...
}
```

## Data

**Descripción**: Combina `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode` y `@RequiredArgsConstructor`.

**Nota**: No incluye `@FieldDefaults`, que puede agregarse para definir el nivel de acceso de los atributos.

**Ejemplo**:
```java
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Persona {
    // ...
}
```

## Builder

**Descripción**: Implementa el patrón de diseño *Builder*.

**Configuración**:
* `builderMethodName = "crearPersona"` cambia el nombre del método *builder*.
* `builderClassName = "PersonaBuilder"` cambia el nombre de la clase generada.

**Ejemplo**:
```java
@Builder(builderMethodName = "crearPersona", builderClassName = "PersonaBuilder")
public class Persona {
    // ...
}
```

## Value

**Descripción**: Define clases inmutables, sin *setters* y con todos los atributos *final*.

**Ejemplo**:
```java
@Value
public class DtoInmutable {
    // ...
}
```

## RequiredArgsConstructor

**Descripción**: Genera un constructor con los atributos marcados como `final` o `@NonNull`.

**Ejemplo**:
```java
@RequiredArgsConstructor
public class Persona {
    // ...
}
```

## Logs

* **@Log**: Utiliza el sistema de `java.util.logging`.
* Para otros sistemas de log (Log4j, SLF4J, etc.), se pueden usar `@Log4j`, `@Slf4j`, etc.

**Ejemplo**:
```java
@Slf4j
public class Persona {
    void registrar() {
        log.info("Registro completado.");
    }
}
```

## Cleanup

**Descripción**: Cierra automáticamente recursos abiertos como `InputStream` o `Scanner`.

**Ejemplo**:
```java
@Cleanup
InputStream input = new FileInputStream("archivo.txt");
```

## SneakyThrows

**Descripción**: Permite lanzar excepciones sin declararlas en la firma del método.

**Ejemplo**:
```java
@SneakyThrows
public void metodo() {
    // ...
}
```

## Configuración del IDE

Para que Lombok funcione correctamente en tu IDE:
1. Instala el plugin de Lombok
2. Habilita el procesamiento de anotaciones
3. Asegúrate de que la versión del plugin sea compatible con la versión de Lombok en tu proyecto

---

Esta guía proporciona una referencia rápida de las anotaciones más comunes de Lombok. Para más detalles y características avanzadas, consulta la [documentación oficial de Lombok](https://projectlombok.org/features/).
