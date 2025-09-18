-- Creo y selecciono la base de datos tp2_editoriales
Create Database tp2_editoriales;
Use tp2_editoriales;

-- Creo las tablas
-- Editoriales
Create table editoriales (
	id_editorial int not null,
    nombre_editorial varchar(50)
);

Alter table editoriales
Add primary key (id_editorial);

-- Empleados
Create table empleados (
	id_empleado int not null,
    nombre_empleado varchar(50),
    id_editorial int
);

Alter table empleados
Add primary key (id_empleado),
Add Constraint fk_empleados_editorial
foreign key (id_editorial)
references editoriales(id_editorial)
on delete cascade on update cascade;

-- Libros
Create table libros (
	id_libro int not null auto_increment primary key,
    titulo_libro varchar(255),
    id_editorial int
);

Alter table libros
Add constraint fk_libros_editorial
foreign key (id_editorial)
references editoriales(id_editorial)
on delete cascade on update cascade;

-- Ingreso los datos
INSERT INTO editoriales (id_editorial, nombre_editorial) 
VALUES 
    (1, 'Editorial Planeta'), 
    (2, 'Editorial Santillana'), 
    (3, 'Editorial Anaya'), 
    (4, 'Editorial Alfaguara'), 
    (5, 'Editorial SM'), 
    (6, 'Editorial Fondo de Cultura Económica'), 
    (7, 'Editorial Siglo XXI'), 
    (8, 'Editorial Cátedra'), 
    (9, 'Editorial Tecnos'), 
    (10, 'Editorial Ariel'); 
 
 
INSERT INTO empleados (id_empleado, nombre_empleado, id_editorial) 
VALUES 
    (1, 'Juan Pérez', 1), 
    (2, 'María Rodríguez', 1), 
    (3, 'Pedro López', 2), 
    (4, 'Ana Martínez', 2), 
    (5, 'Carlos García', 3), 
    (6, 'Laura González', 3), 
    (7, 'Luis Fernández', 4), 
    (8, 'Elena Sánchez', 4), 
    (9, 'Javier Ruiz', 5), 
    (10, 'Sofía Torres', 5); 
 
 
INSERT INTO libros (id_libro, titulo_libro, id_editorial) 
VALUES 
    (1, 'Cien años de soledad', 1), 
    (2, 'Don Quijote de la Mancha', 1), 
    (3, 'La sombra del viento', 2), 
    (4, 'Rayuela', 2), 
    (5, 'Crónica de una muerte anunciada', 3), 
    (6, 'Los detectives salvajes', 3), 
    (7, 'Ficciones', 4), 
    (8, 'La casa de los espíritus', 4), 
    (9, 'La ciudad y los perros', 5), 
    (10, 'Cien años de soledad', 5);


-- Ejercicios sobre integridad referencial: 
--  1. Eliminar  una  editorial:  Si  se  elimina  una  editorial  de  la  tabla  editoriales,  ¿qué  sucede  con  los  libros 
-- asociados? Escriba una consulta SQL que elimine una editorial y sus libros relacionados. 
-- Se elimina la editorial "SM" (id_editorial = 5) lo que debería eliminar en cascada el libro "La ciudad y los perros" (id_libro = 9) y el libro "Cien años de soledad" (id_libro = 10)
Delete from editoriales where id_editorial = 5;
-- Verifico que no esté más la editorial ni los libros asociados
Select * from editoriales;
Select * from libros;
 
-- 2.  Actualizar el nombre de una editorial: Si se actualiza el nombre de una editorial en la tabla editoriales, 
-- ¿qué sucede con los libros relacionados? 
-- Se modifica el nombre de la editorial y se verifica que no cambian los libros relcionados
Select * from libros where id_editorial = 1;
Update editoriales set nombre_editorial = 'EDITORIAL PLANETA' where id_editorial = 1;
Select * from libros where id_editorial = 1;

-- 3. Eliminar un empleado:  Si se elimina un empleado de la tabla empleados, ¿qué sucede con los libros 
-- relacionados con esa editorial? 
-- Se elimina el empleado de id_empleado = 1 de la tabla empleados para verificar que los libros relacionados con esa editorial no se modifican
Select * from empleados where id_empleado = 1;
Select * from libros where id_editorial = 1;
Delete from empleados where id_empleado = 1;
Select * from empleados where id_empleado = 1;
Select * from libros where id_editorial = 1;
 
-- 4. Actualizar  el  nombre  de  un  empleado:  Si  se  actualiza  el  nombre  de  un  empleado  en  la  tabla 
-- empleados, ¿qué sucede con los libros relacionados con esa editorial? 
-- Se modifica el nombre del empleado de id_empleado = 2 de la tabla empleados para verificar que los libros relacionados con esa editorial no se modifican
Select * from empleados where id_empleado = 2;
Select * from libros where id_editorial = (Select id_editorial from empleados where id_empleado = 2);
Update empleados set nombre_empleado = 'Marcela Pérez' where id_empleado = 2;
Select * from empleados where id_empleado = 2;
Select * from libros where id_editorial = (Select id_editorial from empleados where id_empleado = 2);
 
-- 5. Eliminar un libro: Si se elimina un libro de la tabla libros, ¿qué sucede con la relación con la editorial? 
-- Se elimina el libro de id_libro = 8 de la tabla libros para verificar que la editorial relacionada no se modifica
Select * from libros where id_libro = 8;
Select * from editoriales where id_editorial = (Select id_editorial from libros where id_libro = 8);
Delete from libros where id_libro = 8;
Select * from libros where id_libro = 8;
Select * from editoriales where id_editorial = 4;

-- 6. Cambiar  la  editorial  de  un  libro:  Si  se  cambia  la  editorial  a  la  que  está  asociado  un  libro  en  la  tabla 
-- libros, ¿qué sucede con la relación con la editorial anterior? 
-- Se modifica el id_editorial del libro de id_libro = 1 de la tabla libros desde el valor actual al valor id_editorial = 10 para verificar que se pierde la relación con la editorial anterior
-- y se asigna a la editorial de id_editorial = 10;
Select * from libros where id_libro = 1;
Select * from editoriales where id_editorial = (Select id_editorial from libros where id_libro = 1);
Update libros set id_editorial = 10 where id_libro = 1;
Select * from libros where id_libro = 1;
Select * from editoriales where id_editorial = (Select id_editorial from libros where id_libro = 1);

-- 7. Eliminar  una  editorial  con  empleados:  Si  se  intenta  eliminar  una  editorial  que  tiene  empleados 
-- asociados, ¿qué sucede? 
-- Se elimina la editorial de id_editorial = 2 de la tabla editoriales para verificar que los empleados asociados en la tabla empleados se eliminan en cascada
Select * from editoriales where id_editorial = 2;
Select * from empleados where id_editorial = 2;
Delete from editoriales where id_editorial = 2;
Select * from editoriales where id_editorial = 2;
Select * from empleados where id_editorial = 2;
 
-- 8. Eliminar un empleado con libros: Si se intenta eliminar un empleado que tiene libros asociados, ¿qué 
-- sucede? 
-- Se elimina el empleado de id_empleado = 2 de la tabla empleados para verificar que los libros no se modifican
Select * from empleados where id_empleado = 2;
select * from libros;
Delete from empleados where id_empleado = 2;
Select * from empleados where id_empleado = 2;
select * from libros;
 
-- 9. Eliminar una editorial y sus empleados: ¿Cómo se eliminaría una editorial y todos sus empleados? 
-- Se elimina la editorial de id_editorial = 4 de la tabla editoriales y se verifica que los empleados asociados se eliminan en cascada
Select * from editoriales where id_editorial = 4;
Select * from empleados where id_editorial = 4;
Delete from editoriales where id_editorial = 4;
Select * from editoriales where id_editorial = 4;
Select * from empleados where id_editorial = 4;
 
-- 10. Eliminar  una  editorial  y  transferir  sus  empleados  a  otra  editorial:  ¿Cómo  se  eliminaría  una  editorial  y 
-- reasignaría a sus empleados a otra editorial?
-- Se modifica el id_editorial = 3 por id_editorial = 10 en la tabla empleados para transferir los empleados de una editorial a otra y luego eliminar la editorial
-- de id_editorial = 3 de la tabla editoriales
Select * from empleados where id_editorial = 3;
Update empleados set id_editorial = 10 where id_editorial = 3;
Delete from editoriales where id_editorial = 3;
Select * from editoriales where id_editorial = 3;
Select * from empleados where id_editorial = 3;
Select * from empleados where id_editorial = 10;