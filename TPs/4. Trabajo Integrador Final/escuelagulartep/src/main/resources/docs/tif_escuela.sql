-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2025 a las 20:54:56
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tif_escuela`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencias`
--

CREATE TABLE `asistencias` (
  `id_asistencia` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `tipo_asistencia` enum('AUSENTE','PRESENTE','TARDE') NOT NULL,
  `id_estudiante` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asistencias`
--

INSERT INTO `asistencias` (`id_asistencia`, `fecha`, `tipo_asistencia`, `id_estudiante`) VALUES
(1, '2025-03-03', 'PRESENTE', 119),
(2, '2025-03-04', 'PRESENTE', 119),
(3, '2025-03-05', 'PRESENTE', 119),
(4, '2025-03-06', 'PRESENTE', 119),
(5, '2025-03-07', 'TARDE', 119),
(6, '2025-03-10', 'PRESENTE', 119),
(7, '2025-03-11', 'AUSENTE', 119),
(8, '2025-03-12', 'PRESENTE', 119),
(9, '2025-03-13', 'TARDE', 119),
(10, '2025-03-14', 'PRESENTE', 119),
(11, '2025-03-03', 'PRESENTE', 120),
(12, '2025-03-04', 'TARDE', 120),
(13, '2025-03-05', 'PRESENTE', 120),
(14, '2025-03-06', 'PRESENTE', 120),
(15, '2025-03-07', 'PRESENTE', 120),
(16, '2025-03-10', 'PRESENTE', 120),
(17, '2025-03-11', 'PRESENTE', 120),
(18, '2025-03-12', 'TARDE', 120),
(19, '2025-03-13', 'AUSENTE', 120),
(20, '2025-03-14', 'PRESENTE', 120),
(21, '2025-03-03', 'PRESENTE', 121),
(22, '2025-03-04', 'PRESENTE', 121),
(23, '2025-03-05', 'AUSENTE', 121),
(24, '2025-03-06', 'PRESENTE', 121),
(25, '2025-03-07', 'TARDE', 121),
(26, '2025-03-10', 'PRESENTE', 121),
(27, '2025-03-11', 'PRESENTE', 121),
(28, '2025-03-12', 'PRESENTE', 121),
(29, '2025-03-13', 'PRESENTE', 121),
(30, '2025-03-14', 'TARDE', 121),
(31, '2025-03-03', 'TARDE', 122),
(32, '2025-03-04', 'PRESENTE', 122),
(33, '2025-03-05', 'PRESENTE', 122),
(34, '2025-03-06', 'PRESENTE', 122),
(35, '2025-03-07', 'AUSENTE', 122),
(36, '2025-03-10', 'PRESENTE', 122),
(37, '2025-03-11', 'PRESENTE', 122),
(38, '2025-03-12', 'PRESENTE', 122),
(39, '2025-03-13', 'TARDE', 122),
(40, '2025-03-14', 'PRESENTE', 122),
(41, '2025-03-03', 'PRESENTE', 123),
(42, '2025-03-04', 'PRESENTE', 123),
(43, '2025-03-05', 'PRESENTE', 123),
(44, '2025-03-06', 'TARDE', 123),
(45, '2025-03-07', 'PRESENTE', 123),
(46, '2025-03-10', 'AUSENTE', 123),
(47, '2025-03-11', 'PRESENTE', 123),
(48, '2025-03-12', 'PRESENTE', 123),
(49, '2025-03-13', 'PRESENTE', 123),
(50, '2025-03-14', 'TARDE', 123),
(51, '2025-03-03', 'PRESENTE', 124),
(52, '2025-03-04', 'PRESENTE', 124),
(53, '2025-03-05', 'TARDE', 124),
(54, '2025-03-06', 'AUSENTE', 124),
(55, '2025-03-07', 'PRESENTE', 124),
(56, '2025-03-10', 'PRESENTE', 124),
(57, '2025-03-11', 'PRESENTE', 124),
(58, '2025-03-12', 'PRESENTE', 124),
(59, '2025-03-13', 'TARDE', 124),
(60, '2025-03-14', 'PRESENTE', 124),
(61, '2025-03-03', 'TARDE', 125),
(62, '2025-03-04', 'PRESENTE', 125),
(63, '2025-03-05', 'PRESENTE', 125),
(64, '2025-03-06', 'PRESENTE', 125),
(65, '2025-03-07', 'PRESENTE', 125),
(66, '2025-03-10', 'PRESENTE', 125),
(67, '2025-03-11', 'TARDE', 125),
(68, '2025-03-12', 'AUSENTE', 125),
(69, '2025-03-13', 'PRESENTE', 125),
(70, '2025-03-14', 'PRESENTE', 125),
(71, '2025-03-03', 'PRESENTE', 126),
(72, '2025-03-04', 'PRESENTE', 126),
(73, '2025-03-05', 'TARDE', 126),
(74, '2025-03-06', 'PRESENTE', 126),
(75, '2025-03-07', 'PRESENTE', 126),
(76, '2025-03-10', 'PRESENTE', 126),
(77, '2025-03-11', 'PRESENTE', 126),
(78, '2025-03-12', 'TARDE', 126),
(79, '2025-03-13', 'PRESENTE', 126),
(80, '2025-03-14', 'AUSENTE', 126),
(81, '2025-03-03', 'PRESENTE', 127),
(82, '2025-03-04', 'TARDE', 127),
(83, '2025-03-05', 'PRESENTE', 127),
(84, '2025-03-06', 'PRESENTE', 127),
(85, '2025-03-07', 'PRESENTE', 127),
(86, '2025-03-10', 'PRESENTE', 127),
(87, '2025-03-11', 'PRESENTE', 127),
(88, '2025-03-12', 'AUSENTE', 127),
(89, '2025-03-13', 'PRESENTE', 127),
(90, '2025-03-14', 'TARDE', 127),
(91, '2025-03-03', 'TARDE', 128),
(92, '2025-03-04', 'PRESENTE', 128),
(93, '2025-03-05', 'PRESENTE', 128),
(94, '2025-03-06', 'PRESENTE', 128),
(95, '2025-03-07', 'PRESENTE', 128),
(96, '2025-03-10', 'PRESENTE', 128),
(97, '2025-03-11', 'AUSENTE', 128),
(98, '2025-03-12', 'TARDE', 128),
(99, '2025-03-13', 'PRESENTE', 128),
(100, '2025-03-14', 'PRESENTE', 128),
(101, '2025-03-03', 'PRESENTE', 129),
(102, '2025-03-04', 'PRESENTE', 129),
(103, '2025-03-05', 'PRESENTE', 129),
(104, '2025-03-06', 'PRESENTE', 129),
(105, '2025-03-07', 'TARDE', 129),
(106, '2025-03-10', 'PRESENTE', 129),
(107, '2025-03-11', 'PRESENTE', 129),
(108, '2025-03-12', 'PRESENTE', 129),
(109, '2025-03-13', 'AUSENTE', 129),
(110, '2025-03-14', 'TARDE', 129),
(111, '2025-03-03', 'PRESENTE', 130),
(112, '2025-03-04', 'AUSENTE', 130),
(113, '2025-03-05', 'PRESENTE', 130),
(114, '2025-03-06', 'PRESENTE', 130),
(115, '2025-03-07', 'PRESENTE', 130),
(116, '2025-03-10', 'TARDE', 130),
(117, '2025-03-11', 'PRESENTE', 130),
(118, '2025-03-12', 'PRESENTE', 130),
(119, '2025-03-13', 'TARDE', 130),
(120, '2025-03-14', 'PRESENTE', 130);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boletines`
--

CREATE TABLE `boletines` (
  `id_boletin` bigint(20) NOT NULL,
  `anio` int(11) NOT NULL,
  `asignatura` varchar(255) NOT NULL,
  `bimestre` int(11) NOT NULL,
  `nota` int(11) NOT NULL,
  `id_estudiante` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `boletines`
--

INSERT INTO `boletines` (`id_boletin`, `anio`, `asignatura`, `bimestre`, `nota`, `id_estudiante`) VALUES
(1, 2025, 'Lengua', 1, 10, 119),
(2, 2025, 'Matemática', 1, 9, 119),
(3, 2025, 'Ciencias Sociales', 1, 10, 119),
(4, 2025, 'Ciencias Naturales', 1, 8, 119),
(5, 2025, 'Lengua', 2, 6, 119),
(6, 2025, 'Matemática', 2, 4, 119),
(7, 2025, 'Ciencias Sociales', 2, 10, 119),
(8, 2025, 'Ciencias Naturales', 2, 8, 119),
(9, 2025, 'Lengua', 3, 8, 119),
(10, 2025, 'Matemática', 3, 4, 119),
(11, 2025, 'Ciencias Sociales', 3, 5, 119),
(12, 2025, 'Ciencias Naturales', 3, 10, 119),
(13, 2025, 'Lengua', 4, 6, 119),
(14, 2025, 'Matemática', 4, 8, 119),
(15, 2025, 'Ciencias Sociales', 4, 6, 119),
(16, 2025, 'Ciencias Naturales', 4, 10, 119),
(17, 2025, 'Lengua', 1, 6, 120),
(18, 2025, 'Matemática', 1, 10, 120),
(19, 2025, 'Ciencias Sociales', 1, 10, 120),
(20, 2025, 'Ciencias Naturales', 1, 8, 120),
(21, 2025, 'Lengua', 2, 9, 120),
(22, 2025, 'Matemática', 2, 8, 120),
(23, 2025, 'Ciencias Sociales', 2, 10, 120),
(24, 2025, 'Ciencias Naturales', 2, 4, 120),
(25, 2025, 'Lengua', 3, 4, 120),
(26, 2025, 'Matemática', 3, 7, 120),
(27, 2025, 'Ciencias Sociales', 3, 10, 120),
(28, 2025, 'Ciencias Naturales', 3, 6, 120),
(29, 2025, 'Lengua', 4, 8, 120),
(30, 2025, 'Matemática', 4, 8, 120),
(31, 2025, 'Ciencias Sociales', 4, 5, 120),
(32, 2025, 'Ciencias Naturales', 4, 9, 120),
(33, 2025, 'Lengua', 1, 6, 121),
(34, 2025, 'Matemática', 1, 7, 121),
(35, 2025, 'Ciencias Sociales', 1, 7, 121),
(36, 2025, 'Ciencias Naturales', 1, 4, 121),
(37, 2025, 'Lengua', 2, 7, 121),
(38, 2025, 'Matemática', 2, 7, 121),
(39, 2025, 'Ciencias Sociales', 2, 4, 121),
(40, 2025, 'Ciencias Naturales', 2, 9, 121),
(41, 2025, 'Lengua', 3, 8, 121),
(42, 2025, 'Matemática', 3, 9, 121),
(43, 2025, 'Ciencias Sociales', 3, 4, 121),
(44, 2025, 'Ciencias Naturales', 3, 5, 121),
(45, 2025, 'Lengua', 4, 8, 121),
(46, 2025, 'Matemática', 4, 10, 121),
(47, 2025, 'Ciencias Sociales', 4, 5, 121),
(48, 2025, 'Ciencias Naturales', 4, 7, 121),
(49, 2025, 'Lengua', 1, 8, 122),
(50, 2025, 'Matemática', 1, 9, 122),
(51, 2025, 'Ciencias Sociales', 1, 5, 122),
(52, 2025, 'Ciencias Naturales', 1, 7, 122),
(53, 2025, 'Lengua', 2, 4, 122),
(54, 2025, 'Matemática', 2, 4, 122),
(55, 2025, 'Ciencias Sociales', 2, 9, 122),
(56, 2025, 'Ciencias Naturales', 2, 7, 122),
(57, 2025, 'Lengua', 3, 8, 122),
(58, 2025, 'Matemática', 3, 7, 122),
(59, 2025, 'Ciencias Sociales', 3, 6, 122),
(60, 2025, 'Ciencias Naturales', 3, 6, 122),
(61, 2025, 'Lengua', 4, 7, 122),
(62, 2025, 'Matemática', 4, 9, 122),
(63, 2025, 'Ciencias Sociales', 4, 5, 122),
(64, 2025, 'Ciencias Naturales', 4, 8, 122),
(65, 2025, 'Lengua', 1, 10, 123),
(66, 2025, 'Matemática', 1, 8, 123),
(67, 2025, 'Ciencias Sociales', 1, 7, 123),
(68, 2025, 'Ciencias Naturales', 1, 7, 123),
(69, 2025, 'Lengua', 2, 5, 123),
(70, 2025, 'Matemática', 2, 5, 123),
(71, 2025, 'Ciencias Sociales', 2, 8, 123),
(72, 2025, 'Ciencias Naturales', 2, 5, 123),
(73, 2025, 'Lengua', 3, 7, 123),
(74, 2025, 'Matemática', 3, 9, 123),
(75, 2025, 'Ciencias Sociales', 3, 8, 123),
(76, 2025, 'Ciencias Naturales', 3, 7, 123),
(77, 2025, 'Lengua', 4, 9, 123),
(78, 2025, 'Matemática', 4, 7, 123),
(79, 2025, 'Ciencias Sociales', 4, 4, 123),
(80, 2025, 'Ciencias Naturales', 4, 10, 123),
(81, 2025, 'Lengua', 1, 6, 124),
(82, 2025, 'Matemática', 1, 4, 124),
(83, 2025, 'Ciencias Sociales', 1, 7, 124),
(84, 2025, 'Ciencias Naturales', 1, 4, 124),
(85, 2025, 'Lengua', 2, 9, 124),
(86, 2025, 'Matemática', 2, 10, 124),
(87, 2025, 'Ciencias Sociales', 2, 6, 124),
(88, 2025, 'Ciencias Naturales', 2, 8, 124),
(89, 2025, 'Lengua', 3, 4, 124),
(90, 2025, 'Matemática', 3, 7, 124),
(91, 2025, 'Ciencias Sociales', 3, 5, 124),
(92, 2025, 'Ciencias Naturales', 3, 8, 124),
(93, 2025, 'Lengua', 4, 8, 124),
(94, 2025, 'Matemática', 4, 4, 124),
(95, 2025, 'Ciencias Sociales', 4, 8, 124),
(96, 2025, 'Ciencias Naturales', 4, 8, 124),
(97, 2025, 'Lengua', 1, 8, 125),
(98, 2025, 'Matemática', 1, 5, 125),
(99, 2025, 'Ciencias Sociales', 1, 6, 125),
(100, 2025, 'Ciencias Naturales', 1, 9, 125),
(101, 2025, 'Lengua', 2, 4, 125),
(102, 2025, 'Matemática', 2, 9, 125),
(103, 2025, 'Ciencias Sociales', 2, 8, 125),
(104, 2025, 'Ciencias Naturales', 2, 4, 125),
(105, 2025, 'Lengua', 3, 8, 125),
(106, 2025, 'Matemática', 3, 8, 125),
(107, 2025, 'Ciencias Sociales', 3, 5, 125),
(108, 2025, 'Ciencias Naturales', 3, 4, 125),
(109, 2025, 'Lengua', 4, 10, 125),
(110, 2025, 'Matemática', 4, 8, 125),
(111, 2025, 'Ciencias Sociales', 4, 10, 125),
(112, 2025, 'Ciencias Naturales', 4, 4, 125),
(113, 2025, 'Lengua', 1, 5, 126),
(114, 2025, 'Matemática', 1, 10, 126),
(115, 2025, 'Ciencias Sociales', 1, 5, 126),
(116, 2025, 'Ciencias Naturales', 1, 10, 126),
(117, 2025, 'Lengua', 2, 4, 126),
(118, 2025, 'Matemática', 2, 8, 126),
(119, 2025, 'Ciencias Sociales', 2, 5, 126),
(120, 2025, 'Ciencias Naturales', 2, 9, 126),
(121, 2025, 'Lengua', 3, 8, 126),
(122, 2025, 'Matemática', 3, 9, 126),
(123, 2025, 'Ciencias Sociales', 3, 4, 126),
(124, 2025, 'Ciencias Naturales', 3, 10, 126),
(125, 2025, 'Lengua', 4, 6, 126),
(126, 2025, 'Matemática', 4, 9, 126),
(127, 2025, 'Ciencias Sociales', 4, 4, 126),
(128, 2025, 'Ciencias Naturales', 4, 10, 126),
(129, 2025, 'Lengua', 1, 9, 127),
(130, 2025, 'Matemática', 1, 8, 127),
(131, 2025, 'Ciencias Sociales', 1, 10, 127),
(132, 2025, 'Ciencias Naturales', 1, 10, 127),
(133, 2025, 'Lengua', 2, 7, 127),
(134, 2025, 'Matemática', 2, 9, 127),
(135, 2025, 'Ciencias Sociales', 2, 5, 127),
(136, 2025, 'Ciencias Naturales', 2, 5, 127),
(137, 2025, 'Lengua', 3, 4, 127),
(138, 2025, 'Matemática', 3, 4, 127),
(139, 2025, 'Ciencias Sociales', 3, 5, 127),
(140, 2025, 'Ciencias Naturales', 3, 8, 127),
(141, 2025, 'Lengua', 4, 8, 127),
(142, 2025, 'Matemática', 4, 4, 127),
(143, 2025, 'Ciencias Sociales', 4, 8, 127),
(144, 2025, 'Ciencias Naturales', 4, 9, 127),
(145, 2025, 'Lengua', 1, 5, 128),
(146, 2025, 'Matemática', 1, 6, 128),
(147, 2025, 'Ciencias Sociales', 1, 7, 128),
(148, 2025, 'Ciencias Naturales', 1, 7, 128),
(149, 2025, 'Lengua', 2, 4, 128),
(150, 2025, 'Matemática', 2, 7, 128),
(151, 2025, 'Ciencias Sociales', 2, 5, 128),
(152, 2025, 'Ciencias Naturales', 2, 8, 128),
(153, 2025, 'Lengua', 3, 6, 128),
(154, 2025, 'Matemática', 3, 4, 128),
(155, 2025, 'Ciencias Sociales', 3, 5, 128),
(156, 2025, 'Ciencias Naturales', 3, 9, 128),
(157, 2025, 'Lengua', 4, 8, 128),
(158, 2025, 'Matemática', 4, 9, 128),
(159, 2025, 'Ciencias Sociales', 4, 10, 128),
(160, 2025, 'Ciencias Naturales', 4, 10, 128),
(161, 2025, 'Lengua', 1, 6, 129),
(162, 2025, 'Matemática', 1, 8, 129),
(163, 2025, 'Ciencias Sociales', 1, 7, 129),
(164, 2025, 'Ciencias Naturales', 1, 5, 129),
(165, 2025, 'Lengua', 2, 8, 129),
(166, 2025, 'Matemática', 2, 9, 129),
(167, 2025, 'Ciencias Sociales', 2, 8, 129),
(168, 2025, 'Ciencias Naturales', 2, 4, 129),
(169, 2025, 'Lengua', 3, 6, 129),
(170, 2025, 'Matemática', 3, 8, 129),
(171, 2025, 'Ciencias Sociales', 3, 10, 129),
(172, 2025, 'Ciencias Naturales', 3, 4, 129),
(173, 2025, 'Lengua', 4, 7, 129),
(174, 2025, 'Matemática', 4, 4, 129),
(175, 2025, 'Ciencias Sociales', 4, 8, 129),
(176, 2025, 'Ciencias Naturales', 4, 6, 129),
(177, 2025, 'Lengua', 1, 8, 130),
(178, 2025, 'Matemática', 1, 5, 130),
(179, 2025, 'Ciencias Sociales', 1, 10, 130),
(180, 2025, 'Ciencias Naturales', 1, 4, 130),
(181, 2025, 'Lengua', 2, 6, 130),
(182, 2025, 'Matemática', 2, 8, 130),
(183, 2025, 'Ciencias Sociales', 2, 7, 130),
(184, 2025, 'Ciencias Naturales', 2, 6, 130),
(185, 2025, 'Lengua', 3, 6, 130),
(186, 2025, 'Matemática', 3, 7, 130),
(187, 2025, 'Ciencias Sociales', 3, 7, 130),
(188, 2025, 'Ciencias Naturales', 3, 4, 130),
(189, 2025, 'Lengua', 4, 10, 130),
(190, 2025, 'Matemática', 4, 8, 130),
(191, 2025, 'Ciencias Sociales', 4, 10, 130),
(192, 2025, 'Ciencias Naturales', 4, 10, 130);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiantes`
--

CREATE TABLE `estudiantes` (
  `id_estudiante` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni` varchar(255) NOT NULL,
  `edad` int(11) NOT NULL,
  `hno_en_escuela` bit(1) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `nombre_madre` varchar(255) DEFAULT NULL,
  `nombre_padre` varchar(255) DEFAULT NULL,
  `regular` bit(1) DEFAULT NULL,
  `id_grado` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estudiantes`
--

INSERT INTO `estudiantes` (`id_estudiante`, `apellido`, `direccion`, `dni`, `edad`, `hno_en_escuela`, `nombre`, `nombre_madre`, `nombre_padre`, `regular`, `id_grado`) VALUES
(9, 'Sosa', 'Calle 9 de Julio 900', '48000009', 6, b'0', 'Lautaro', 'Clara Sosa', 'Roberto Sosa', b'1', 1),
(10, 'Ruiz', 'Calle Colon 1000', '48000010', 6, b'0', 'Gabriel', 'Patricia Ruiz', 'Ricardo Ruiz', b'1', 1),
(11, 'Torres', 'Calle Paz 1100', '48000011', 6, b'1', 'Bautista', 'Carmen Torres', 'Hugo Torres', b'1', 1),
(12, 'Flores', 'Calle Libertad 1200', '48000012', 6, b'0', 'Felipe', 'Teresa Flores', 'Manuel Flores', b'1', 1),
(13, 'Benítez', 'Calle Roca 1300', '48000013', 6, b'0', 'Isabella', 'Adriana Benítez', 'Oscar Benítez', b'1', 1),
(14, 'Ramírez', 'Calle Salta 1400', '48000014', 6, b'1', 'Camila', 'Silvia Ramírez', 'Andrés Ramírez', b'1', 1),
(15, 'Herrera', 'Calle Jujuy 1500', '48000015', 6, b'0', 'Mía', 'Natalia Herrera', 'Guillermo Herrera', b'1', 1),
(16, 'Acosta', 'Calle Tucumán 1600', '48000016', 6, b'0', 'Julieta', 'Mónica Acosta', 'Gustavo Acosta', b'1', 1),
(17, 'Medina', 'Calle Córdoba 1700', '48000017', 6, b'1', 'Emma', 'Alicia Medina', 'Sergio Medina', b'1', 1),
(18, 'Rojas', 'Calle Mendoza 1800', '48000018', 6, b'0', 'Martina', 'Daniela Rojas', 'Alberto Rojas', b'1', 1),
(19, 'Molina', 'Calle San Juan 1900', '48000019', 6, b'0', 'Lucía', 'Lorena Molina', 'Eduardo Molina', b'1', 1),
(20, 'Castro', 'Calle Entre Ríos 2000', '48000020', 6, b'1', 'Catalina', 'Gabriela Castro', 'Mariano Castro', b'1', 1),
(21, 'Ortiz', 'Calle Santa Fe 2100', '48000021', 6, b'0', 'Victoria', 'Verónica Ortiz', 'Pablo Ortiz', b'1', 1),
(22, 'Silva', 'Calle Misiones 2200', '48000022', 6, b'0', 'Elena', 'Beatriz Silva', 'Fabián Silva', b'1', 1),
(23, 'Núñez', 'Calle Chaco 2300', '48000023', 6, b'1', 'Emilia', 'Cecilia Núñez', 'Esteban Núñez', b'1', 1),
(24, 'Luna', 'Calle La Pampa 2400', '48000024', 6, b'0', 'Zoe', 'Inés Luna', 'Lucas Luna', b'1', 1),
(25, 'Juárez', 'Calle Río Negro 2500', '48000025', 6, b'0', 'Alma', 'Carolina Juárez', 'Martín Juárez', b'1', 1),
(26, 'Cabrera', 'Calle Neuquén 2600', '48000026', 6, b'1', 'Renata', 'Flavia Cabrera', 'Sebastián Cabrera', b'1', 1),
(27, 'Díaz', 'Calle Falsa 111', '49000001', 7, b'0', 'Diego', 'Alejandra Díaz', 'Julio Díaz', b'1', 2),
(28, 'Gómez', 'Avenida Real 222', '49000002', 6, b'1', 'Marco', 'Andrea Gómez', 'Héctor Gómez', b'1', 2),
(29, 'Muñoz', 'Pasaje Central 333', '49000003', 7, b'0', 'Pedro', 'Yanina Muñoz', 'Ramiro Muñoz', b'1', 2),
(30, 'Vargas', 'Ruta 5 Km 10', '49000004', 6, b'0', 'Ian', 'Carolina Vargas', 'Marcelo Vargas', b'1', 2),
(31, 'Méndez', 'Barrio Norte 444', '49000005', 7, b'1', 'Leo', 'Erica Méndez', 'Juan Méndez', b'1', 2),
(32, 'Peralta', 'Calle 100 Nro 555', '49000006', 6, b'0', 'Alex', 'Romina Peralta', 'Sergio Peralta', b'1', 2),
(33, 'Rivas', 'Callejon Oscuro 666', '49000007', 7, b'0', 'Santino', 'Jesica Rivas', 'Matías Rivas', b'1', 2),
(34, 'Sánchez', 'Av. del Bosque 777', '49000008', 6, b'1', 'Máximo', 'Florencia Sánchez', 'Germán Sánchez', b'1', 2),
(35, 'Quinteros', 'Calle Larga 888', '49000009', 6, b'0', 'Abril', 'Cecilia Quinteros', 'Rubén Quinteros', b'1', 2),
(36, 'Ramos', 'Pasaje Chico 999', '49000010', 7, b'1', 'Olivia', 'Diana Ramos', 'Pablo Ramos', b'1', 2),
(37, 'Vega', 'Calle Principal 1010', '49000011', 6, b'0', 'Jazmín', 'Estela Vega', 'Hugo Vega', b'1', 2),
(38, 'Zarate', 'Av. Del Sol 1111', '49000012', 7, b'0', 'Pilar', 'Mabel Zarate', 'Néstor Zarate', b'1', 2),
(39, 'Herrera', 'Calle Las Flores 1212', '49000013', 6, b'1', 'Nicole', 'Susana Herrera', 'Leo Herrera', b'1', 2),
(40, 'Ibarra', 'Ruta 9 Km 20', '49000014', 7, b'0', 'Alma', 'Valeria Ibarra', 'Cristian Ibarra', b'1', 2),
(41, 'Ríos', 'Callejón Sur 1313', '49000015', 6, b'0', 'Amelia', 'Viviana Ríos', 'Enrique Ríos', b'1', 2),
(42, 'Franco', 'Av. Costanera 1414', '49000016', 7, b'1', 'Delfina', 'Ximena Franco', 'Darío Franco', b'1', 2),
(43, 'Gallo', 'Calle 50 Nro 1515', '49000017', 6, b'0', 'Bianca', 'Yolanda Gallo', 'Raúl Gallo', b'1', 2),
(44, 'Navarro', 'Pasaje Río 1616', '49000018', 7, b'0', 'Gina', 'Zulma Navarro', 'José Navarro', b'1', 2),
(45, 'Palma', 'Barrio Oeste 1717', '49000019', 6, b'1', 'Emma', 'Blanca Palma', 'César Palma', b'1', 2),
(48, 'Giménez', 'Barrio Obrero 30', '50000003', 8, b'0', 'Facundo', 'Brenda Giménez', 'Hugo Giménez', b'1', 3),
(49, 'Iriarte', 'Pasaje La Paz 40', '50000004', 7, b'1', 'Juan', 'Eliana Iriarte', 'Mauro Iriarte', b'1', 3),
(50, 'Montes', 'Ruta Vieja 50', '50000005', 8, b'0', 'Franco', 'Mónica Montes', 'Saúl Montes', b'1', 3),
(51, 'Nieto', 'Calle de las Rosas 60', '50000006', 7, b'0', 'Simón', 'Nora Nieto', 'Omar Nieto', b'1', 3),
(52, 'Paz', 'Av. Circunvalación 70', '50000007', 8, b'1', 'Ignacio', 'Ruth Paz', 'Vicente Paz', b'1', 3),
(53, 'Vera', 'Calle Pringles 80', '50000008', 7, b'0', 'Ezequiel', 'Tatiana Vera', 'Ulises Vera', b'1', 3),
(54, 'Aguirre', 'Pasaje Sol 90', '50000009', 7, b'0', 'Abigail', 'Zoe Aguirre', 'Ariel Aguirre', b'1', 3),
(55, 'Cano', 'Calle del Río 100', '50000010', 8, b'1', 'Guadalupe', 'Brenda Cano', 'Carlos Cano', b'1', 3),
(56, 'Duarte', 'Av. Del Este 110', '50000011', 7, b'0', 'Agustina', 'Cinthia Duarte', 'Damián Duarte', b'1', 3),
(57, 'Figueroa', 'Callejón Sur 120', '50000012', 8, b'0', 'Aldana', 'Emilia Figueroa', 'Fabián Figueroa', b'1', 3),
(58, 'Juárez', 'Calle 10 Nro 130', '50000013', 7, b'1', 'Candela', 'Gisela Juárez', 'Horacio Juárez', b'1', 3),
(59, 'Leiva', 'Av. Central 140', '50000014', 8, b'0', 'Daina', 'Ivana Leiva', 'Julián Leiva', b'1', 3),
(60, 'Méndez', 'Pasaje Chico 150', '50000015', 7, b'0', 'Estefanía', 'Karina Méndez', 'Lucas Méndez', b'1', 3),
(61, 'Núñez', 'Ruta Provincial 160', '50000016', 8, b'1', 'Felicitas', 'Luciana Núñez', 'Martín Núñez', b'1', 3),
(62, 'Quiroga', 'Barrio Unido 170', '50000017', 7, b'0', 'Malena', 'Natalia Quiroga', 'Octavio Quiroga', b'1', 3),
(63, 'Ramos', 'Calle Sarmiento 180', '50000018', 8, b'0', 'Sofía', 'Paola Ramos', 'Ricardo Ramos', b'1', 3),
(64, 'Salas', 'Av. Roca 190', '50000019', 7, b'1', 'Tamara', 'Sandra Salas', 'Tobías Salas', b'1', 3),
(65, 'Toledo', 'Pasaje Central 200', '50000020', 8, b'0', 'Victoria', 'Úrsula Toledo', 'Víctor Toledo', b'1', 3),
(66, 'Zarate', 'Calle Belgrano 210', '50000021', 7, b'0', 'Ximena', 'Wendy Zarate', 'Yago Zarate', b'1', 3),
(67, 'Gutiérrez', 'Calle La Rioja 300', '51000001', 9, b'0', 'Benicio', 'Laura Gutiérrez', 'Carlos Gutiérrez', b'1', 4),
(68, 'Hernández', 'Av. Corrientes 400', '51000002', 8, b'1', 'Ciro', 'Mónica Hernández', 'Daniel Hernández', b'1', 4),
(69, 'Vega', 'Pasaje Paraná 500', '51000003', 9, b'0', 'Dante', 'Natalia Vega', 'Ernesto Vega', b'1', 4),
(70, 'Ponce', 'Ruta 11 Km 15', '51000004', 8, b'0', 'Elián', 'Sofía Ponce', 'Felipe Ponce', b'1', 4),
(71, 'Coronel', 'Calle Entre Ríos 600', '51000005', 9, b'1', 'Genaro', 'Úrsula Coronel', 'Gabriel Coronel', b'1', 4),
(72, 'Ferreyra', 'Av. 25 de Mayo 700', '51000006', 8, b'0', 'Israel', 'Valeria Ferreyra', 'Horacio Ferreyra', b'1', 4),
(73, 'Nieva', 'Pasaje Tucumán 800', '51000007', 9, b'0', 'Lisandro', 'Ximena Nieva', 'Iker Nieva', b'1', 4),
(74, 'Silva', 'Calle Junín 900', '51000008', 8, b'0', 'Alma', 'Yésica Silva', 'Javier Silva', b'1', 4),
(75, 'Gallo', 'Av. Córdoba 1000', '51000009', 9, b'1', 'Alma', 'Zaira Gallo', 'Kevin Gallo', b'1', 4),
(76, 'Peralta', 'Pasaje Misiones 1100', '51000010', 8, b'0', 'Paula', 'Andrea Peralta', 'Leo Peralta', b'1', 4),
(77, 'Rojas', 'Ruta Nacional 12', '51000011', 9, b'0', 'Jazmín', 'Bárbara Rojas', 'Max Rojas', b'1', 4),
(78, 'Vázquez', 'Calle Catamarca 1300', '51000012', 8, b'1', 'Sofía', 'Carla Vázquez', 'Nico Vázquez', b'1', 4),
(79, 'Chaves', 'Av. Entre Ríos 1400', '51000013', 9, b'0', 'Milagros', 'Daniela Chaves', 'Osvaldo Chaves', b'1', 4),
(80, 'Duarte', 'Pasaje Chaco 1500', '51000014', 8, b'0', 'Renata', 'Érica Duarte', 'Pedro Duarte', b'1', 4),
(81, 'Olmos', 'Calle Formosa 1600', '51000015', 9, b'1', 'Abril', 'Florencia Olmos', 'Quique Olmos', b'1', 4),
(82, 'Riquelme', 'Av. Mendoza 1700', '51000016', 8, b'0', 'Emilia', 'Gisela Riquelme', 'Ramiro Riquelme', b'1', 4),
(83, 'Sosa', 'Calle Santiago 1800', '51000017', 9, b'0', 'Violeta', 'Hilda Sosa', 'Sebastián Sosa', b'1', 4),
(84, 'García', 'Calle de los Álamos 10', '52000001', 10, b'0', 'Julián', 'María García', 'Roberto García', b'1', 5),
(85, 'Rodríguez', 'Av. Las Palmas 20', '52000002', 9, b'1', 'Mateo', 'Ana Rodríguez', 'Felipe Rodríguez', b'1', 5),
(86, 'Pérez', 'Ruta 3 Km 5', '52000003', 10, b'0', 'Oliver', 'Elena Pérez', 'Pablo Pérez', b'1', 5),
(87, 'López', 'Pasaje del Oeste 30', '52000004', 9, b'0', 'Ramiro', 'Juana López', 'Sebastián López', b'1', 5),
(88, 'Sosa', 'Calle Mitre 40', '52000005', 10, b'1', 'Ulises', 'Carla Sosa', 'Víctor Sosa', b'1', 5),
(89, 'Gómez', 'Av. España 50', '52000006', 9, b'0', 'Valentino', 'Diana Gómez', 'Walter Gómez', b'1', 5),
(90, 'Acosta', 'Calle San Luis 60', '52000007', 9, b'0', 'Luciana', 'Florencia Acosta', 'Héctor Acosta', b'1', 5),
(91, 'Díaz', 'Pasaje La Pampa 70', '52000008', 10, b'1', 'Morena', 'Gabriela Díaz', 'Iván Díaz', b'1', 5),
(92, 'Ramos', 'Ruta 9 Km 25', '52000009', 9, b'0', 'Naomi', 'Irma Ramos', 'Julián Ramos', b'1', 5),
(93, 'Torres', 'Calle Tucumán 80', '52000010', 10, b'0', 'Zoe', 'Laura Torres', 'Marcos Torres', b'1', 5),
(94, 'Vega', 'Av. San Juan 90', '52000011', 9, b'1', 'Abril', 'Nadia Vega', 'Óscar Vega', b'1', 5),
(95, 'Luna', 'Pasaje Salta 100', '52000012', 10, b'0', 'Mia', 'Patricia Luna', 'Renzo Luna', b'1', 5),
(96, 'Núñez', 'Calle Viamonte 110', '52000013', 9, b'0', 'Erika', 'Silvina Núñez', 'Tadeo Núñez', b'1', 5),
(97, 'Ríos', 'Av. Las Heras 120', '52000014', 10, b'1', 'Martina', 'Úrsula Ríos', 'Víctor Ríos', b'1', 5),
(98, 'Franco', 'Calle Sarmiento 130', '52000015', 9, b'0', 'Julieta', 'Ximena Franco', 'Yair Franco', b'1', 5),
(99, 'Benítez', 'Pasaje Misiones 140', '52000016', 10, b'0', 'Valentina', 'Yésica Benítez', 'Zacarías Benítez', b'1', 5),
(100, 'Quiroga', 'Av. del Puerto 150', '53000001', 11, b'0', 'Lucas', 'Mariana Quiroga', 'Daniel Quiroga', b'1', 6),
(101, 'Rojas', 'Calle La Plata 160', '53000002', 10, b'1', 'Mateo', 'Ana Rojas', 'Javier Rojas', b'1', 6),
(102, 'Silva', 'Pasaje del Sol 170', '53000003', 11, b'0', 'Nicolás', 'Carolina Silva', 'Esteban Silva', b'1', 6),
(103, 'Torres', 'Ruta 7 Km 30', '53000004', 10, b'0', 'Pablo', 'Florencia Torres', 'Guido Torres', b'1', 6),
(104, 'Vega', 'Calle Chacabuco 180', '53000005', 11, b'1', 'Ricardo', 'Helena Vega', 'Iván Vega', b'1', 6),
(105, 'Méndez', 'Av. 9 de Julio 190', '53000006', 10, b'0', 'Thiago', 'Inés Méndez', 'Kike Méndez', b'1', 6),
(106, 'Ortiz', 'Pasaje Las Flores 200', '53000007', 11, b'0', 'Valentino', 'Juana Ortiz', 'Leo Ortiz', b'1', 6),
(107, 'Paz', 'Calle Misiones 210', '53000008', 10, b'1', 'Zacarías', 'Karina Paz', 'Manuel Paz', b'1', 6),
(108, 'Arias', 'Av. Libertador 220', '53000009', 10, b'0', 'Victoria', 'Lorena Arias', 'Néstor Arias', b'1', 6),
(109, 'Benítez', 'Ruta 8 Km 40', '53000010', 11, b'1', 'Zoe', 'Micaela Benítez', 'Octavio Benítez', b'1', 6),
(110, 'Castro', 'Calle Sarmiento 230', '53000011', 10, b'0', 'Catalina', 'Noemí Castro', 'Pedro Castro', b'1', 6),
(111, 'Díaz', 'Pasaje Norte 240', '53000012', 11, b'0', 'Delfina', 'Olga Díaz', 'Quique Díaz', b'1', 6),
(112, 'Franco', 'Av. San Martín 250', '53000013', 10, b'1', 'Guillermina', 'Patricia Franco', 'Raúl Franco', b'1', 6),
(113, 'Giménez', 'Calle Las Heras 260', '53000014', 11, b'0', 'Lucía', 'Rocío Giménez', 'Sergio Giménez', b'1', 6),
(114, 'Ibarra', 'Pasaje Córdoba 270', '53000015', 10, b'0', 'Martina', 'Silvia Ibarra', 'Tito Ibarra', b'1', 6),
(115, 'Juárez', 'Av. Roca 280', '53000016', 11, b'1', 'Olivia', 'Teresa Juárez', 'Ulises Juárez', b'1', 6),
(116, 'López', 'Calle Jujuy 290', '53000017', 10, b'0', 'Paz', 'Úrsula López', 'Víctor López', b'1', 6),
(117, 'Morales', 'Ruta 6 Km 18', '53000018', 11, b'0', 'Renata', 'Virginia Morales', 'Walter Morales', b'1', 6),
(118, 'Núñez', 'Av. Belgrano 300', '53000019', 10, b'1', 'Sofía', 'Ximena Núñez', 'Yago Núñez', b'1', 6),
(119, 'Chávez', 'Calle Principal 15', '54000001', 12, b'0', 'Alexander', 'Laura Chávez', 'Martín Chávez', b'1', 7),
(120, 'Escobar', 'Av. 12 de Octubre 25', '54000002', 11, b'1', 'Brian', 'Noelia Escobar', 'Pablo Escobar', b'1', 7),
(121, 'Herrera', 'Pasaje del Centro 35', '54000003', 12, b'0', 'Kevin', 'Silvia Herrera', 'Ricardo Herrera', b'1', 7),
(122, 'Juárez', 'Ruta 14 Km 50', '54000004', 11, b'0', 'Marcelo', 'Tania Juárez', 'Ulises Juárez', b'1', 7),
(123, 'Pereyra', 'Calle Los Sauces 45', '54000005', 12, b'1', 'Santiago', 'Viviana Pereyra', 'Walter Pereyra', b'1', 7),
(124, 'Ramos', 'Av. Belgrano 55', '54000006', 11, b'0', 'Sebastián', 'Ximena Ramos', 'Yago Ramos', b'1', 7),
(125, 'Acosta', 'Pasaje Sarmiento 65', '54000007', 12, b'0', 'Abril', 'Andrea Acosta', 'Beto Acosta', b'1', 7),
(126, 'Díaz', 'Calle San Juan 75', '54000008', 11, b'1', 'Brenda', 'Carla Díaz', 'Dario Díaz', b'1', 7),
(127, 'Giménez', 'Av. Rivadavia 85', '54000009', 12, b'0', 'Camila', 'Elena Giménez', 'Fede Giménez', b'1', 7),
(128, 'López', 'Ruta 38 Km 10', '54000010', 11, b'0', 'Jazmín', 'Gloria López', 'Hugo López', b'1', 7),
(129, 'Martínez', 'Calle 25 de Mayo 95', '54000011', 12, b'1', 'Laura', 'Inés Martínez', 'Juan Martínez', b'1', 7),
(130, 'Núñez', 'Pasaje La Rioja 105', '54000012', 11, b'0', 'Sofía', 'Karina Núñez', 'Leo Núñez', b'1', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grados`
--

CREATE TABLE `grados` (
  `id_grado` bigint(20) NOT NULL,
  `activo` bit(1) DEFAULT NULL,
  `docente` varchar(255) DEFAULT NULL,
  `nombre` enum('CUARTO','PRIMERO','QUINTO','SEGUNDO','SEPTIMO','SEXTO','TERCERO') NOT NULL,
  `turno` enum('JORNADA_COMPLETA','MAÑANA','TARDE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grados`
--

INSERT INTO `grados` (`id_grado`, `activo`, `docente`, `nombre`, `turno`) VALUES
(1, b'1', 'Matías Ruiz', 'PRIMERO', 'MAÑANA'),
(2, b'1', 'Vanesa Leiva', 'SEGUNDO', 'MAÑANA'),
(3, b'1', 'Julieta Pérez', 'TERCERO', 'MAÑANA'),
(4, b'1', 'Adriana Arias', 'CUARTO', 'MAÑANA'),
(5, b'1', 'Pedro González', 'QUINTO', 'MAÑANA'),
(6, b'1', 'Daiana Giménez', 'SEXTO', 'MAÑANA'),
(7, b'1', 'Roxana Costa', 'SEPTIMO', 'MAÑANA'),
(8, b'1', 'Juan Pérez', 'PRIMERO', 'TARDE'),
(9, b'1', 'María Fernández', 'SEGUNDO', 'TARDE'),
(10, b'1', 'Laura González', 'TERCERO', 'TARDE'),
(11, b'1', 'Sofía Gómez', 'CUARTO', 'TARDE'),
(12, b'1', 'Esteban Sánchez', 'QUINTO', 'TARDE'),
(13, b'1', 'Ana Ruiz', 'SEXTO', 'TARDE'),
(14, b'1', 'Diego Díaz', 'SEPTIMO', 'TARDE'),
(15, b'1', 'Andrés Benítez', 'PRIMERO', 'JORNADA_COMPLETA'),
(16, b'1', 'Marcela Morales', 'SEGUNDO', 'JORNADA_COMPLETA'),
(17, b'1', 'Sofía Mariana Gómez', 'TERCERO', 'JORNADA_COMPLETA'),
(18, b'1', 'Alejandro López', 'CUARTO', 'JORNADA_COMPLETA'),
(19, b'1', 'Valeria Torres', 'QUINTO', 'JORNADA_COMPLETA'),
(20, b'1', 'Gabriela Acosta', 'SEXTO', 'JORNADA_COMPLETA'),
(21, b'1', 'Florencia Benítez', 'SEPTIMO', 'JORNADA_COMPLETA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asistencias`
--
ALTER TABLE `asistencias`
  ADD PRIMARY KEY (`id_asistencia`),
  ADD KEY `FKi9ni3pi9ev151cy0qq6s0taw0` (`id_estudiante`);

--
-- Indices de la tabla `boletines`
--
ALTER TABLE `boletines`
  ADD PRIMARY KEY (`id_boletin`),
  ADD KEY `FKfkmx75cgkghvvk7ovgq5uyleb` (`id_estudiante`);

--
-- Indices de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD PRIMARY KEY (`id_estudiante`),
  ADD UNIQUE KEY `UKjk2tfkhim3wwtjv6me1utdnwn` (`dni`),
  ADD KEY `FKr3d3wk4ipsdl5fum0mb3dr8dr` (`id_grado`);

--
-- Indices de la tabla `grados`
--
ALTER TABLE `grados`
  ADD PRIMARY KEY (`id_grado`),
  ADD UNIQUE KEY `UKp5cbv37ov9uumctkqtvapkadk` (`nombre`,`turno`),
  ADD UNIQUE KEY `UK7d162hawkvk6qw48y1d2td0w5` (`docente`,`activo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asistencias`
--
ALTER TABLE `asistencias`
  MODIFY `id_asistencia` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

--
-- AUTO_INCREMENT de la tabla `boletines`
--
ALTER TABLE `boletines`
  MODIFY `id_boletin` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=225;

--
-- AUTO_INCREMENT de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  MODIFY `id_estudiante` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;

--
-- AUTO_INCREMENT de la tabla `grados`
--
ALTER TABLE `grados`
  MODIFY `id_grado` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistencias`
--
ALTER TABLE `asistencias`
  ADD CONSTRAINT `FKi9ni3pi9ev151cy0qq6s0taw0` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiantes` (`id_estudiante`);

--
-- Filtros para la tabla `boletines`
--
ALTER TABLE `boletines`
  ADD CONSTRAINT `FKfkmx75cgkghvvk7ovgq5uyleb` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiantes` (`id_estudiante`);

--
-- Filtros para la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD CONSTRAINT `FKr3d3wk4ipsdl5fum0mb3dr8dr` FOREIGN KEY (`id_grado`) REFERENCES `grados` (`id_grado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
