--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Debian 12.2-2.pgdg100+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

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
-- Name: fakultas id; Type: DEFAULT; Schema: public; Owner: postgresadmin
--

ALTER TABLE ONLY public.fakultas ALTER COLUMN id SET DEFAULT nextval('public.fakultas_id_seq'::regclass);


--
-- Data for Name: fakultas; Type: TABLE DATA; Schema: public; Owner: postgresadmin
--

COPY public.fakultas (id, nama) FROM stdin;
1       test
\.


--
-- Name: fakultas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresadmin
--

SELECT pg_catalog.setval('public.fakultas_id_seq', 1, true);


--
-- PostgreSQL database dump complete
--