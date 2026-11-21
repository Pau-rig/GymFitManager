# GymFit Manager · Evaluación Heurística y Rediseño de Usabilidad

## Introducción

La cadena de gimnasios GymFit usa en recepción una aplicación antigua con la que tienen muchos fallos y equivocaciones y algunos borran fichas sin querer. La intención de este documento es evaluar, rediseñarla y comprobar con usuarios sus cambios. 

## Evaluación Heurística

Escala de gravedad:
- No es problema **0**
- Estético **1**
- Menor **2**
- Grave **3**
- Catastrófico **4**

| # | Problema detectado | Dónde | Pauta que incumple | Gravedad (0-4) | Propuesta de mejora |
| --- | --- | --- | --- | --- | --- |
| 1 | El sexo se escribe a mano ('H', 'M' u 'O') | Formulario de alta | Control apropiado para minimizar la carga de memoria  | 3 | Sustituir por RadioButton excluyentes. |
| 2 | Mensaje de error muy técnico y con dobles negaciones  | Cuadro de diálogo de error | Mensajes claros, positivos y ayuda a recuperarse del error.  | 4 | Reescribirlo para que se lea de forma correcta indicando el posible error y cómo solucionarlo. |
| 3 | Barra de menús desordenada con nombres ambiguos y sin atajos | Barra de menús superior | Consistencia y estándares de plataforma | 3 | Reorganizar según el estándar y añadir los atajos en el taclado. |
| 4 | Ruta de guardado oculta tras 6 niveles de submenús | Menú / Barra inferior | Eficiencia de uso y accesibilidad de acciones fracuentes | 4 | Dar acceso directo a los botones principales en el formulario, barra de herramientas y atajos. |
| 5 | Menú contextual saturado con 16 opciones y 7 submenús en cascada | Clic derecho en tabla de socios | Diseño minimalista reduciendo las opciones sin ejecuatarlo con cascada | 3 | Reducir a 5 acciones frecuentes en un único nivel sin submenús en cascada. |
| 6 | Botón destructivo gigante en rojo brillante pegado al 'OK' y sin dialogo de confirmación | Zona inferior del formulario | Prevención de errores y separar los botones destructivos | 4 | Destacar la acción principal 'Guardar Socio', separar 'Borrar Formulario' y pedir confirmación del borrado de datos. |
| 7 | Fondo amarillo chillón, banner fucsia y tipografia informal con bajo constraste | Toda la pantalla | Estetica, constraste adecuado y tipogracia legible | 3 | Eliminarl banners distractores y aplicar una paleta armonica de 3-4 colores suabes con alto constrate. |
| 8 | Tipo de cuota usa casillas por lo que permite se puedan marcar varias cuotas a la vez | Formulario de alta | Control apropiado para selccionar unica  | 3 | Usar tarjetas con RadioButton agrupados en un ToggleGroup mostrando nombres y precios. |
| 9 | Provincia pide memorizar el codigo INE y fecha acepta dos formatos distintos ('dd/mm/aaaa o mm/dd/aa') | Formulario da alta | Recocimiento antes que recuerdo y prevencion de errores | 3 | Usar desplegables con nombres de provincias y selector de calendario.|
| 10 | Tabla de socios sin encabezados de columna, mezclado formatos de fecha y mostrando códigos numéricos sin leyendas | Panel derecho | Visibilidad del estado del sistema y claridad de datos. | 3 | Añadir titulo, encabezados claros y valores legibles. |

---

## Rediseño en JavaFX

He realizado todos los cambios dichos en la anterior tabla y aquí se puede ver el resultado

### Pantalla principal
![Pantalla principal.png](img/Pantalla%20principal.png)

### Pantalla principal con la tabla de socios
![Pantalla principal con tabla desplegada.png](img/Pantalla%20principal%20con%20tabla%20desplegada.png)

### Pantalla principal con mensaje de error
![Pantalla principal con el mensaje de error.png](img/Pantalla%20principal%20con%20el%20mensaje%20de%20error.png)

---

## Mini Test 

La tarea solicitada a los usuarios que tenemos que realizar para evaluar el éxito de la prueba:

"Da de alta a Laura Gómez Ruiz, nacida el 14/05/2003, de Sevilla, con cuota Joven y la clase de Spinning"

| Usuario | ¿Completada? | Tiempo | Errores o dudas | Satisfacción (1-5) |
| --- | --- | --- | --- | --- |
| **Usuario 1** | Si | 1:00s | Se equivocó con el IBAN | 4/5 |
| **Usuario 2** | Si | 45s | Se equivocó con su fecha y la volvio a hacer | 5/5 |

## Conclusiones y mejoras futuras

Tras ver las pruebas de los usuarios se ha comprobado que es más intuitiva que la anterior, al eliminar las dudas de la versión original, permitiendo completar el alta en menos de un minuto y sin errores críticos. 

Sin embargo, detecté que ambos tuvieron problemas con el IBAN al no estar seguros del formato ni llevar la cuenta de los dígitos. Como una mejora futura, haría una máscara de formato automático cada 4 caracteres al escribir el IBAN. 