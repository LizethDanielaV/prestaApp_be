-- =============================================================
-- SCRIPT 1: Reset de base de datos
-- Ejecutar ANTES de iniciar la aplicación.
-- Elimina todas las tablas EXCEPTO roles y usuario.
-- Hibernate recreará las tablas al iniciar la app.
-- =============================================================

-- Desactivar temporalmente las restricciones de FK
SET session_replication_role = replica;

DROP TABLE IF EXISTS abono CASCADE;
DROP TABLE IF EXISTS pago CASCADE;
DROP TABLE IF EXISTS cuota CASCADE;
DROP TABLE IF EXISTS credito CASCADE;
DROP TABLE IF EXISTS cliente CASCADE;
DROP TABLE IF EXISTS referencia CASCADE;
DROP TABLE IF EXISTS zona CASCADE;
DROP TABLE IF EXISTS metodo_de_pago CASCADE;
DROP TABLE IF EXISTS banco CASCADE;
DROP TABLE IF EXISTS estado CASCADE;
DROP TABLE IF EXISTS frecuencia_pago CASCADE;

-- Reactivar restricciones de FK
SET session_replication_role = DEFAULT;

-- Verificar que roles y usuario siguen intactos
SELECT 'roles' AS tabla, COUNT(*) AS filas FROM roles
UNION ALL
SELECT 'usuario', COUNT(*) FROM usuario;
