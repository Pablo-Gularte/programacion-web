-- Script para limpiar referencias huérfanas en la base de datos
-- Ejecutar este script directamente en MySQL para resolver el problema actual

-- 1. Mostrar referencias huérfanas antes de limpiar
SELECT 
    ec.fk_estudiante, 
    ec.fk_curso, 
    e.nombre as nombre_estudiante,
    'CURSO ELIMINADO' as curso_status
FROM estudiante_curso ec
LEFT JOIN curso c ON ec.fk_curso = c.id_curso
LEFT JOIN estudiante e ON ec.fk_estudiante = e.id_estudiante
WHERE c.id_curso IS NULL;

-- 2. Eliminar referencias huérfanas de la tabla intermedia
DELETE FROM estudiante_curso 
WHERE fk_curso NOT IN (SELECT id_curso FROM curso);

-- 3. Verificar que se limpiaron correctamente
SELECT COUNT(*) as referencias_huerfanas_restantes
FROM estudiante_curso ec
LEFT JOIN curso c ON ec.fk_curso = c.id_curso
WHERE c.id_curso IS NULL;

-- 4. Mostrar el estado actual de las relaciones
SELECT 
    e.id_estudiante,
    e.nombre as estudiante_nombre,
    c.id_curso,
    c.nombre as curso_nombre
FROM estudiante_curso ec
JOIN estudiante e ON ec.fk_estudiante = e.id_estudiante
JOIN curso c ON ec.fk_curso = c.id_curso
ORDER BY e.nombre, c.nombre;