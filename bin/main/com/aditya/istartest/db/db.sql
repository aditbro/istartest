--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12 (Ubuntu 10.12-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: fakultas; Type: TABLE; Schema: public; Owner: postgresadmin
--

CREATE TABLE public.fakultas (
    id integer NOT NULL,
    nama character varying(100)
);


ALTER TABLE public.fakultas OWNER TO postgresadmin;

--
-- Name: fakultas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresadmin
--

CREATE SEQUENCE public.fakultas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fakultas_id_seq OWNER TO postgresadmin;

--
-- Name: fakultas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresadmin
--

ALTER SEQUENCE public.fakultas_id_seq OWNED BY public.fakultas.id;


--
-- Name: jurusan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jurusan (
    id integer NOT NULL,
    nama character varying(100),
    id_fakultas integer
);


ALTER TABLE public.jurusan OWNER TO postgres;

--
-- Name: jurusan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jurusan_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.jurusan_id_seq OWNER TO postgres;

--
-- Name: jurusan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jurusan_id_seq OWNED BY public.jurusan.id;


--
-- Name: mahasiswa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mahasiswa (
    id integer NOT NULL,
    nama character varying(100),
    id_jurusan integer
);


ALTER TABLE public.mahasiswa OWNER TO postgres;

--
-- Name: mahasiswa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mahasiswa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mahasiswa_id_seq OWNER TO postgres;

--
-- Name: mahasiswa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.mahasiswa_id_seq OWNED BY public.mahasiswa.id;


--
-- Name: matakuliah; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matakuliah (
    id integer NOT NULL,
    nama character varying(100)
);


ALTER TABLE public.matakuliah OWNER TO postgres;

--
-- Name: matakuliah_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.matakuliah_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.matakuliah_id_seq OWNER TO postgres;

--
-- Name: matakuliah_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.matakuliah_id_seq OWNED BY public.matakuliah.id;


--
-- Name: matkul_mahasiswa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matkul_mahasiswa (
    id integer NOT NULL,
    id_mahasiswa integer,
    id_matakuliah integer
);


ALTER TABLE public.matkul_mahasiswa OWNER TO postgres;

--
-- Name: matkul_mahasiswa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.matkul_mahasiswa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.matkul_mahasiswa_id_seq OWNER TO postgres;

--
-- Name: matkul_mahasiswa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.matkul_mahasiswa_id_seq OWNED BY public.matkul_mahasiswa.id;


--
-- Name: fakultas id; Type: DEFAULT; Schema: public; Owner: postgresadmin
--

ALTER TABLE ONLY public.fakultas ALTER COLUMN id SET DEFAULT nextval('public.fakultas_id_seq'::regclass);


--
-- Name: jurusan id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jurusan ALTER COLUMN id SET DEFAULT nextval('public.jurusan_id_seq'::regclass);


--
-- Name: mahasiswa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mahasiswa ALTER COLUMN id SET DEFAULT nextval('public.mahasiswa_id_seq'::regclass);


--
-- Name: matakuliah id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matakuliah ALTER COLUMN id SET DEFAULT nextval('public.matakuliah_id_seq'::regclass);


--
-- Name: matkul_mahasiswa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matkul_mahasiswa ALTER COLUMN id SET DEFAULT nextval('public.matkul_mahasiswa_id_seq'::regclass);


--
-- Data for Name: fakultas; Type: TABLE DATA; Schema: public; Owner: postgresadmin
--

COPY public.fakultas (id, nama) FROM stdin;
1	test
\.


--
-- Data for Name: jurusan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jurusan (id, nama, id_fakultas) FROM stdin;
1	test	1
2	nope	1
\.


--
-- Data for Name: mahasiswa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mahasiswa (id, nama, id_jurusan) FROM stdin;
5	psadpasd	1
6	s dasmdksamda1	1
7	adit	1
3	testsadsad	1
9	asdasdas	1
10	sadlsamdlsad	2
11	mmkmkm	1
12	osakdpoaskdopas	1
13	sadjsadkasmdk	1
14	213123	1
4	adit	2
15	mmmm	2
\.


--
-- Data for Name: matakuliah; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.matakuliah (id, nama) FROM stdin;
\.


--
-- Data for Name: matkul_mahasiswa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.matkul_mahasiswa (id, id_mahasiswa, id_matakuliah) FROM stdin;
\.


--
-- Name: fakultas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresadmin
--

SELECT pg_catalog.setval('public.fakultas_id_seq', 1, true);


--
-- Name: jurusan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jurusan_id_seq', 2, true);


--
-- Name: mahasiswa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mahasiswa_id_seq', 15, true);


--
-- Name: matakuliah_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.matakuliah_id_seq', 1, false);


--
-- Name: matkul_mahasiswa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.matkul_mahasiswa_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

