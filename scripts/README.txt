================================================================
INSTRUCCIONES DE MIGRACIÓN DE BASE DE DATOS
================================================================

Sigue estos pasos EN ORDEN después de hacer pull de los cambios:

PASO 1 - Ejecutar el reset (elimina tablas viejas, mantiene usuario y roles)
---------------------------------------------------------------------------
psql -U postgres -d prestamos_db -f scripts/1_reset-db.sql


PASO 2 - Iniciar la aplicación
---------------------------------------------------------------------------
mvnw.cmd spring-boot:run

  → Hibernate recreará automáticamente todas las tablas con el nuevo esquema.
  → Espera a que diga "Started PrestamosApplication" y luego APÁGALA (Ctrl+C).


PASO 3 - Importar los datos de migración
---------------------------------------------------------------------------
psql -U postgres -d prestamos_db -f scripts/2_data-migration.sql


PASO 4 - Volver a iniciar la aplicación normalmente
---------------------------------------------------------------------------
mvnw.cmd spring-boot:run


================================================================
NOTAS IMPORTANTES
================================================================
- Si psql no está en el PATH, usa la ruta completa:
  "C:\Program Files\PostgreSQL\<version>\bin\psql.exe"
- La contraseña por defecto de postgres es la que configuraron al instalar.
- Las tablas `roles` y `usuario` NO se tocan en ningún paso.
================================================================
