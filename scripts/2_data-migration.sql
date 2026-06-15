--
-- PostgreSQL database dump
--

\restrict oKZHfg9pfhkThtUV8TkMBkZxettgRBBlvQKVt2onhTcB2XBB8gwTVZTthy0ZKqE

-- Dumped from database version 17.8
-- Dumped by pg_dump version 17.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: banco; Type: TABLE DATA; Schema: public; Owner: postgres
--

SET SESSION AUTHORIZATION DEFAULT;

ALTER TABLE public.banco DISABLE TRIGGER ALL;



ALTER TABLE public.banco ENABLE TRIGGER ALL;

--
-- Data for Name: referencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.referencia DISABLE TRIGGER ALL;

INSERT INTO public.referencia (cedula, celular, nombre) VALUES (987654, 3001234567, 'María López');
INSERT INTO public.referencia (cedula, celular, nombre) VALUES (56, 3156498720, 'Doris Vargas');
INSERT INTO public.referencia (cedula, celular, nombre) VALUES (987654321, 3001234567, 'Martina Gomez');
INSERT INTO public.referencia (cedula, celular, nombre) VALUES (125, 3001234567, 'Martina Gomez');


ALTER TABLE public.referencia ENABLE TRIGGER ALL;

--
-- Data for Name: zona; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.zona DISABLE TRIGGER ALL;

INSERT INTO public.zona (id_zona, nombre) VALUES (1, 'Zulia');
INSERT INTO public.zona (id_zona, nombre) VALUES (2, 'Los Patios');
INSERT INTO public.zona (id_zona, nombre) VALUES (3, 'Atalaya');


ALTER TABLE public.zona ENABLE TRIGGER ALL;

--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.cliente DISABLE TRIGGER ALL;

INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (123456, 'Calle 1 #2-3', 'Juan Pérez', NULL, 1);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (1, 'Calle 1 #2-3', 'Marlon', 987654, 1);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (2, 'San Nicolas', 'Gerson Vargas', NULL, 2);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (5, '28 de Febrero', 'Dayana Tovar', 56, 3);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (7, 'av 5 28-66', 'Nelson Rosas', NULL, 2);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (1234567890, 'Calle 10 #20-30', 'Juan Perez', 987654321, 2);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (1234, 'Calle 10 #20-30', 'Daniel Perez', 987654321, 2);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (12345, 'Calle 10 #20-30', 'Daniel Perez', NULL, 2);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (123444, 'Calle 10 #20-30', 'Daniel Perez', 125, 2);
INSERT INTO public.cliente (cedula, direccion, nombre, referencia_id, zona_id) VALUES (50505, 'Conjunto', 'Karol Gomez', NULL, 2);


ALTER TABLE public.cliente ENABLE TRIGGER ALL;

--
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.estado DISABLE TRIGGER ALL;

INSERT INTO public.estado (id_estado, nombre, aplica_a) VALUES (1, 'al_dia', 'credito');
INSERT INTO public.estado (id_estado, nombre, aplica_a) VALUES (2, 'atrasado', 'credito');
INSERT INTO public.estado (id_estado, nombre, aplica_a) VALUES (3, 'cancelado', 'credito');
INSERT INTO public.estado (id_estado, nombre, aplica_a) VALUES (4, 'pendiente', 'cuota');
INSERT INTO public.estado (id_estado, nombre, aplica_a) VALUES (5, 'parcial', 'cuota');
INSERT INTO public.estado (id_estado, nombre, aplica_a) VALUES (6, 'pagada', 'cuota');
INSERT INTO public.estado (id_estado, nombre, aplica_a) VALUES (7, 'vencida', 'cuota');


ALTER TABLE public.estado ENABLE TRIGGER ALL;

--
-- Data for Name: frecuencia_pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.frecuencia_pago DISABLE TRIGGER ALL;

INSERT INTO public.frecuencia_pago (id_referencia_pago, dias, nombre) VALUES (1, 7, 'Semanal');
INSERT INTO public.frecuencia_pago (id_referencia_pago, dias, nombre) VALUES (2, 1, 'Diaria');
INSERT INTO public.frecuencia_pago (id_referencia_pago, dias, nombre) VALUES (3, 15, 'Quincenal');
INSERT INTO public.frecuencia_pago (id_referencia_pago, dias, nombre) VALUES (4, 30, 'mensual');
INSERT INTO public.frecuencia_pago (id_referencia_pago, dias, nombre) VALUES (5, 3, 'tres_dias');


ALTER TABLE public.frecuencia_pago ENABLE TRIGGER ALL;

--
-- Data for Name: credito; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.credito DISABLE TRIGGER ALL;

INSERT INTO public.credito (id_credito, fecha_limite, fecha_prestamo, interes, monto_prestamo, numero_de_coutas, cliente_id, frecuencia_pago_id, estado_id, saldo_capital) VALUES (9, '2026-07-14', '2026-06-14', 20, 100000, 4, 2, 1, 1, 100000);
INSERT INTO public.credito (id_credito, fecha_limite, fecha_prestamo, interes, monto_prestamo, numero_de_coutas, cliente_id, frecuencia_pago_id, estado_id, saldo_capital) VALUES (10, '2026-07-14', '2026-06-14', 20, 100000, 4, 50505, 1, 1, 75000);
INSERT INTO public.credito (id_credito, fecha_limite, fecha_prestamo, interes, monto_prestamo, numero_de_coutas, cliente_id, frecuencia_pago_id, estado_id, saldo_capital) VALUES (11, '2026-07-14', '2026-06-14', 20, 100000, 4, 2, 1, 1, 50000);


ALTER TABLE public.credito ENABLE TRIGGER ALL;

--
-- Data for Name: cuota; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.cuota DISABLE TRIGGER ALL;

INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (2, '2026-06-21', NULL, 25000, 1, 9, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (3, '2026-06-28', NULL, 25000, 2, 9, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (4, '2026-07-05', NULL, 25000, 3, 9, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (5, '2026-07-12', NULL, 25000, 4, 9, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (7, '2026-06-28', NULL, 25000, 2, 10, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (8, '2026-07-05', NULL, 25000, 3, 10, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (9, '2026-07-12', NULL, 25000, 4, 10, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (13, '2026-07-12', NULL, 25000, 4, 11, 4, 0, 0);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (6, '2026-06-21', '2026-06-15 09:22:00', 25000, 1, 10, 6, 25000, 20000);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (10, '2026-06-21', '2026-06-15 09:22:00', 25000, 1, 11, 6, 25000, 20000);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (12, '2026-07-05', NULL, 25000, 3, 11, 5, 0, 5000);
INSERT INTO public.cuota (id_cuota, fecha_esperada, fecha_pago_real, monto_capital, numero_de_cuota, credito_id, estado_id, capital_pagado, interes_pagado) VALUES (11, '2026-06-28', '2026-06-15 09:22:00', 25000, 2, 11, 6, 25000, 15000);


ALTER TABLE public.cuota ENABLE TRIGGER ALL;

--
-- Data for Name: metodo_de_pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.metodo_de_pago DISABLE TRIGGER ALL;

INSERT INTO public.metodo_de_pago (id_metodo_de_pago, nombre, banco_id) VALUES (1, 'Efectivo', NULL);


ALTER TABLE public.metodo_de_pago ENABLE TRIGGER ALL;

--
-- Data for Name: pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.pago DISABLE TRIGGER ALL;

INSERT INTO public.pago (id_pago, fecha_pago, monto_total, notas, tipo, cliente_id, metodo_de_pago_id) VALUES (5, '2026-06-15 09:22:00', 45000, NULL, 'UN_CREDITO', 50505, 1);
INSERT INTO public.pago (id_pago, fecha_pago, monto_total, notas, tipo, cliente_id, metodo_de_pago_id) VALUES (6, '2026-06-15 09:22:00', 10000, NULL, 'UN_CREDITO', 2, 1);
INSERT INTO public.pago (id_pago, fecha_pago, monto_total, notas, tipo, cliente_id, metodo_de_pago_id) VALUES (7, '2026-06-15 09:22:00', 40000, NULL, 'UN_CREDITO', 2, 1);
INSERT INTO public.pago (id_pago, fecha_pago, monto_total, notas, tipo, cliente_id, metodo_de_pago_id) VALUES (8, '2026-06-15 09:22:00', 40000, NULL, 'UN_CREDITO', 2, 1);


ALTER TABLE public.pago ENABLE TRIGGER ALL;

--
-- Data for Name: abono; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.abono DISABLE TRIGGER ALL;

INSERT INTO public.abono (id_abono, fecha_pago, monto_total, numero_de_cuota, cuota_id, metodo_de_pago_id, capital_abonado, interes_abonado, total_abonado, pago_id) VALUES (5, NULL, NULL, NULL, 6, NULL, 25000, 20000, 45000, 5);
INSERT INTO public.abono (id_abono, fecha_pago, monto_total, numero_de_cuota, cuota_id, metodo_de_pago_id, capital_abonado, interes_abonado, total_abonado, pago_id) VALUES (6, NULL, NULL, NULL, 10, NULL, 0, 10000, 10000, 6);
INSERT INTO public.abono (id_abono, fecha_pago, monto_total, numero_de_cuota, cuota_id, metodo_de_pago_id, capital_abonado, interes_abonado, total_abonado, pago_id) VALUES (7, NULL, NULL, NULL, 10, NULL, 25000, 10000, 35000, 7);
INSERT INTO public.abono (id_abono, fecha_pago, monto_total, numero_de_cuota, cuota_id, metodo_de_pago_id, capital_abonado, interes_abonado, total_abonado, pago_id) VALUES (8, NULL, NULL, NULL, 11, NULL, 0, 5000, 5000, 7);
INSERT INTO public.abono (id_abono, fecha_pago, monto_total, numero_de_cuota, cuota_id, metodo_de_pago_id, capital_abonado, interes_abonado, total_abonado, pago_id) VALUES (9, NULL, NULL, NULL, 11, NULL, 25000, 10000, 35000, 8);
INSERT INTO public.abono (id_abono, fecha_pago, monto_total, numero_de_cuota, cuota_id, metodo_de_pago_id, capital_abonado, interes_abonado, total_abonado, pago_id) VALUES (10, NULL, NULL, NULL, 12, NULL, 0, 5000, 5000, 8);


ALTER TABLE public.abono ENABLE TRIGGER ALL;

--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

ALTER TABLE public.usuarios DISABLE TRIGGER ALL;

INSERT INTO public.usuarios (uuid, correo, nombre, rol_id) VALUES ('2c8f905b-287a-4334-846a-1ddbfcbb58fa', 'lizethdanielavb@gmail.com', 'Lizeth', 1);


ALTER TABLE public.usuarios ENABLE TRIGGER ALL;

--
-- Name: abono_id_abono_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abono_id_abono_seq', 10, true);


--
-- Name: banco_id_banco_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.banco_id_banco_seq', 1, false);


--
-- Name: credito_id_credito_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credito_id_credito_seq', 11, true);


--
-- Name: cuota_id_cuota_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuota_id_cuota_seq', 13, true);


--
-- Name: estado_id_estado_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_id_estado_seq', 7, true);


--
-- Name: frecuencia_pago_id_referencia_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.frecuencia_pago_id_referencia_pago_seq', 5, true);


--
-- Name: metodo_de_pago_id_metodo_de_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.metodo_de_pago_id_metodo_de_pago_seq', 1, false);


--
-- Name: pago_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pago_id_pago_seq', 8, true);


--
-- Name: zona_id_zona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.zona_id_zona_seq', 4, true);


--
-- PostgreSQL database dump complete
--

\unrestrict oKZHfg9pfhkThtUV8TkMBkZxettgRBBlvQKVt2onhTcB2XBB8gwTVZTthy0ZKqE

