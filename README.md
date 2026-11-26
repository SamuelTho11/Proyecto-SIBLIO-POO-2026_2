Proyecto SIBLIO (POO 2026-2)

Concurrencia

Qué proceso escogimos:
Escogimos cuando se registran préstamos sobre un mismo ejemplar (Bibliotecario.registrarPrestamo). Lo elegimos porque en una biblioteca puede pasar que dos usuarios pidan el mismo libro o revista al mismo tiempo. Si no controlamos eso, el programa podría dejar que los dos lo presten a la vez, cuando en realidad solo hay un ejemplar físico.

Qué usamos:
Para simular varias solicitudes al mismo tiempo usamos ExecutorService con 3 hilos (Executors.newFixedThreadPool(3)). Lo escogimos en lugar de usar Thread directamente porque maneja los hilos por nosotros y no nos daña la herencia que ya teníamos en Usuario y Bibliotecario (que heredan de Persona).

Para evitar que dos hilos presten el mismo ejemplar al tiempo, usamos synchronized sobre el ejemplar que se está prestando (synchronized (solicitud.getEjemplar())). Así, cuando llegan varios hilos por el mismo ejemplar, solo pasa uno a la vez: el primero lo presta y lo marca como PRESTADO, y a los demás les sale el aviso de que ya no está disponible.

Cómo probarlo:
Al final de SIBLIO.java pusimos tres pruebas con ExecutorService:3 hilos intentando prestar el mismo ejemplar (REV-101-A) al tiempo, 3 hilos intentando devolver el mismo préstamo al tiempo, en donde solo uno logra devolverlo y los demás son rechazados y 3 usuarios reservando un libro en paralelo.
