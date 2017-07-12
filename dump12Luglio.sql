--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
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


SET search_path = public, pg_catalog;

--
-- Name: tipi_pagamento; Type: DOMAIN; Schema: public; Owner: hanzo
--

CREATE DOMAIN tipi_pagamento AS character varying(255)
	CONSTRAINT tipi_pagamento_check CHECK (((VALUE)::text = ANY ((ARRAY['PAYPAL'::character varying, 'BONIFICO'::character varying, 'CARTA_CREDITO'::character varying])::text[])));


ALTER DOMAIN tipi_pagamento OWNER TO hanzo;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: brano; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE brano (
    nome character varying(255) NOT NULL,
    ordine integer NOT NULL,
    cd_id integer NOT NULL
);


ALTER TABLE brano OWNER TO hanzo;

--
-- Name: cd; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE cd (
    titolo character varying(255) NOT NULL,
    prezzo numeric(10,2) NOT NULL,
    descrizione text,
    pezzi_venduti integer DEFAULT 0,
    pezzi_magazzino integer DEFAULT 0,
    genere_id integer,
    id integer NOT NULL,
    data_inserimento date DEFAULT now(),
    CONSTRAINT checkprice CHECK ((prezzo > (0)::numeric))
);


ALTER TABLE cd OWNER TO hanzo;

--
-- Name: cd_id_seq; Type: SEQUENCE; Schema: public; Owner: hanzo
--

CREATE SEQUENCE cd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cd_id_seq OWNER TO hanzo;

--
-- Name: cd_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hanzo
--

ALTER SEQUENCE cd_id_seq OWNED BY cd.id;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE cliente (
    username character varying(255) NOT NULL,
    codice_fiscale character varying(16),
    nome character varying(255) NOT NULL,
    cognome character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    indirizzo character varying(255) NOT NULL,
    telefono character varying(255) NOT NULL,
    cellulare character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE cliente OWNER TO hanzo;

--
-- Name: genere; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE genere (
    id integer NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE genere OWNER TO hanzo;

--
-- Name: genere_id_seq; Type: SEQUENCE; Schema: public; Owner: hanzo
--

CREATE SEQUENCE genere_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE genere_id_seq OWNER TO hanzo;

--
-- Name: genere_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hanzo
--

ALTER SEQUENCE genere_id_seq OWNED BY genere.id;


--
-- Name: images; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE images (
    url character varying(255) DEFAULT NULL::character varying NOT NULL,
    cd_id integer
);


ALTER TABLE images OWNER TO hanzo;

--
-- Name: musicista; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE musicista (
    id integer NOT NULL,
    nome_arte character varying(255) NOT NULL,
    genere_id integer,
    anno_nascita integer,
    isband boolean DEFAULT false
);


ALTER TABLE musicista OWNER TO hanzo;

--
-- Name: musicista_id_seq; Type: SEQUENCE; Schema: public; Owner: hanzo
--

CREATE SEQUENCE musicista_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE musicista_id_seq OWNER TO hanzo;

--
-- Name: musicista_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hanzo
--

ALTER SEQUENCE musicista_id_seq OWNED BY musicista.id;


--
-- Name: ordine; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE ordine (
    cliente character varying(255),
    prezzo_complessivo numeric(10,2),
    modalita_acquisto tipi_pagamento,
    modalita_consegna character varying(255),
    ip character varying(255),
    data date DEFAULT now(),
    ora time without time zone DEFAULT now(),
    id integer NOT NULL
);


ALTER TABLE ordine OWNER TO hanzo;

--
-- Name: ordine_id_seq; Type: SEQUENCE; Schema: public; Owner: hanzo
--

CREATE SEQUENCE ordine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ordine_id_seq OWNER TO hanzo;

--
-- Name: ordine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hanzo
--

ALTER SEQUENCE ordine_id_seq OWNED BY ordine.id;


--
-- Name: partecipazione; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE partecipazione (
    musicista_id integer NOT NULL,
    is_titolare boolean DEFAULT false,
    cd_id integer NOT NULL
);


ALTER TABLE partecipazione OWNER TO hanzo;

--
-- Name: personale; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE personale (
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    nome character varying(20) DEFAULT NULL::character varying,
    cognome character varying(20) DEFAULT NULL::character varying
);


ALTER TABLE personale OWNER TO hanzo;

--
-- Name: rigaordine; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE rigaordine (
    ordine_id integer,
    cd_id integer,
    qta integer,
    prezzo numeric(10,2),
    CONSTRAINT rigaordine_prezzo_check CHECK ((prezzo > (0)::numeric)),
    CONSTRAINT rigaordine_qta_check CHECK ((qta > 0))
);


ALTER TABLE rigaordine OWNER TO hanzo;

--
-- Name: strumento; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE strumento (
    id integer NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE strumento OWNER TO hanzo;

--
-- Name: strumento_id_seq; Type: SEQUENCE; Schema: public; Owner: hanzo
--

CREATE SEQUENCE strumento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE strumento_id_seq OWNER TO hanzo;

--
-- Name: strumento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hanzo
--

ALTER SEQUENCE strumento_id_seq OWNED BY strumento.id;


--
-- Name: utilizzo; Type: TABLE; Schema: public; Owner: hanzo
--

CREATE TABLE utilizzo (
    musicista_id integer NOT NULL,
    strumento_id integer NOT NULL
);


ALTER TABLE utilizzo OWNER TO hanzo;

--
-- Name: cd id; Type: DEFAULT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY cd ALTER COLUMN id SET DEFAULT nextval('cd_id_seq'::regclass);


--
-- Name: genere id; Type: DEFAULT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY genere ALTER COLUMN id SET DEFAULT nextval('genere_id_seq'::regclass);


--
-- Name: musicista id; Type: DEFAULT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY musicista ALTER COLUMN id SET DEFAULT nextval('musicista_id_seq'::regclass);


--
-- Name: ordine id; Type: DEFAULT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY ordine ALTER COLUMN id SET DEFAULT nextval('ordine_id_seq'::regclass);


--
-- Name: strumento id; Type: DEFAULT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY strumento ALTER COLUMN id SET DEFAULT nextval('strumento_id_seq'::regclass);


--
-- Data for Name: brano; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY brano (nome, ordine, cd_id) FROM stdin;
Around the World 	0	23
Parallel Universe 	1	23
Scar Tissue	2	23
Otherside 	3	23
Californication 	4	23
I Like Dirt	5	23
This Velvet Glove	6	23
Road Trippin' 	7	23
Battery	0	24
Master of Puppets	1	24
The Thing That Should Not Be	2	24
Welcome Home	3	24
Lonely Boy	0	14
Dead and Gone	1	14
Gold on the Ceiling	2	14
Little Black Submarines	3	14
Money Maker	4	14
Run Right Back	5	14
Sister	6	14
Hell of a Season	7	14
Stop Stop	8	14
Nova Baby	9	14
Mind Eraser	10	14
Disposable Heroes	4	24
Leper Messiah	5	24
Orion	6	24
Damage	7	24
Funky Stuff	0	28
More Funky Stuff	1	28
Jungle Boogie	2	28
Heaven at Once	3	28
Hollywood Swinging	4	28
This Is You, This Is Me	5	28
Life Is What You Make It	6	28
Wild and Peaceful	7	28
brano2	0	33
Prélude	0	29
Menuet	1	29
Clair de Lune	2	29
Passepied	3	29
Reflets dans l'eau	4	29
Voiles	5	29
Jardins sous la pluie	6	29
Cortège	7	29
Allegro vivo	8	29
Interlude	9	29
Natural Mystic	0	31
So Much Things to Say	1	31
Guiltiness	2	31
The Heaten	3	31
Exodus	4	31
Jammin' 	5	31
Waiting in Vain	6	31
Turn Your Lights Down Low	7	31
Three Little Birds	8	31
One Love	9	31
Megalovania	0	21
Bonestrouse	1	21
Save the word	2	21
Determination	3	21
Spider Dance	4	21
Hopes & Dreams	5	21
Dummy!	6	21
NyaNyaNya!	7	21
Megalo strikes back	8	21
Weight of Love	0	32
In Time	1	32
Turn Blue	2	32
Given the Dog a Bone	0	11
Hells Bells	1	11
Let Me Put My Love Into You	2	11
Shoot to Thrill	3	11
You Shook Me All Night Long	4	11
Fever	3	32
Year in Review	4	32
Bullet in the Brain	5	32
It's Up to You Now	6	32
Waiting on Words	7	32
10 Lovers	8	32
In Our Prime	9	32
Gotta Get Away	10	32
Spicks and Specks	0	30
To Love Somebody	1	30
Massachusetts	2	30
You Should Be Dancing	3	30
How Deep Is Your Love	4	30
Stayin' Alive	5	30
Night Fever	6	30
More Than a Woman 	7	30
Too Much Heaven	8	30
Love You Inside Out	9	30
School Days	0	27
Deep Feeling	1	27
Too Much Monkey Business	2	27
Wee Wee Hours	3	27
Roly Poly	4	27
Maybellene	0	26
Thirty Days	1	26
Too Much Monkey Business	2	26
Roll Over Beethoven	3	26
Havana Moon	4	26
Rock & Roll Music	5	26
Johnny B. Goode	6	26
Back In the U.S.A.	7	26
Let It Rock	8	26
You Never Can Tell	9	26
No Particular Place to Go	10	26
I Want to Be Your Driver	11	26
No Money Down	5	27
Brown-eyed Handsome Man	6	27
Berry Pickin'	7	27
Together (We'll Always Be)	8	27
Havana Moon	9	27
Downbound Train	10	27
Drifting Heart	11	27
\.


--
-- Data for Name: cd; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY cd (titolo, prezzo, descrizione, pezzi_venduti, pezzi_magazzino, genere_id, id, data_inserimento) FROM stdin;
After School Session	15.00	After School Session è il primo album in studio del cantante rock statunitense Chuck Berry pubblicato il 1º maggio 1957 sotto l'etichetta Chess.	0	0	79	27	2017-07-06
Californication	11.48	Californication è il settimo album del gruppo musicale statunitense Red Hot Chili Peppers, pubblicato l'8 giugno 1999 dalla Warner Bros.	0	10	41	23	2017-06-30
Debussy: The Essentials	12.00	These 25 tracks demonstrate Debussy’s incomparable ability to create atmosphere, drama and beautiful melodies. \nFrom the much-loved piano music, including Clair de Lune and La Cathédrale Engloutie, to orchestral and chamber music masterpieces – La Mer, Prélude à l’après-midi d’un faune, Sonata for Violin & Piano – all are featured here in landmark performances.	0	19	33	29	2017-07-06
El Camino	19.99	El Camino è il settimo album del duo blues rock The Black Keys, pubblicato nel 2011.	0	49	41	14	2017-06-29
Chuck Berry - The Definitive Collection	19.99		0	50	79	26	2017-07-06
Timeless - The All-Time Greatest Hits	29.99	Timeless: The All-Time Greatest Hits è un album di raccolta del gruppo musicale Bee Gees, pubblicato nel 2017.\n\n	0	78	14	30	2017-07-06
Master of Puppets	15.00	Grazie alle doti compositive dei singoli componenti del gruppo, l'album riesce a ritagliarsi un posto di prima grandezza nel mondo del thrash metal; dalla maggior parte della critica specializzata viene considerato come il miglior album del gruppo, nonché uno dei più riusciti dischi heavy metal di sempre. Da notare la buonissima produzione per l'epoca (1986): come riportò il frontman James Hetfield in varie interviste, venne data una grande attenzione allo spessore e alla potenza del suono delle chitarre, caratteristiche che con il tempo divennero uno dei punti di riferimento per il genere.	0	1	144	24	2017-07-04
Back in Black	25.00	Back in Black è il settimo album in studio degli AC/DC, pubblicato il 25 luglio 1980 dalla Atco Records.	0	20	80	11	2017-06-28
Exodus	22.00	Pubblicato il 3 giugno 1977. La rivista Rolling Stone l'ha inserito al 169º posto della sua lista dei 500 migliori album. Nel 1999 la rivista TIME elegge Exodus migliore album del XX secolo.\n\n	0	15	17	31	2017-07-06
Wild And Peaceful	22.00	Wild and Peaceful è il sesto album dei Kool & the Gang, uscito nel 1973.\nA partire da questo album, il nome del gruppo abbandona la grafia iniziale Kool and the Gang, ed adotta la grafia Kool & the Gang tuttora utilizzata.	0	36	6	28	2017-07-06
Titoloprova	12.00	Breve descrizione...	0	22	78	33	2017-07-11
Undertale OST	10.00	Undertale Ost	0	100	53	21	2017-06-30
Turn Blue	15.00	Turn Blue è l'ottavo album discografico del duo blues rock The Black Keys, pubblicato il 13 maggio 2014.	0	26	183	32	2017-07-08
\.


--
-- Name: cd_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hanzo
--

SELECT pg_catalog.setval('cd_id_seq', 33, true);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY cliente (username, codice_fiscale, nome, cognome, password, indirizzo, telefono, cellulare) FROM stdin;
P	P	P	P	83878c91171338902e0fe0fb97a8c47a	via	+393406168667	+390445473880
aleZil	Alessandro	Zilotti	ZLTLSN93C31C890R	7d91dd9a1e09df2dde8b456488bae5a4	Via Roma 88	+390442649678	+393400905032
Trevizanzan	Andrea	Trevisan	TRVNDR91E12I7775E	3aa81d7065b1c627cf195aa5ad806115	via A. Gramsci, 9	+390457640415	+393465803994
\.


--
-- Data for Name: genere; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY genere (id, nome) FROM stdin;
1	Blues
2	ClassicRock
3	Country
4	Dance
5	Disco
6	Funk
7	Grunge
8	Hip-Hop
9	Jazz
10	Metal
11	NewAge
12	Oldies
13	Other
14	Pop
15	R&B
16	Rap
17	Reggae
18	Rock
19	Techno
20	Industrial
21	Alternative
22	Ska
23	Deathmetal
24	Pranks
25	Soundtrack
26	Euro-Techno
27	Ambient
28	Trip-hop
29	Vocal
30	Jazz&Funk
31	Fusion
32	Trance
33	Classical
34	Instrumental
35	Acid
36	House
37	Game
38	SoundClip
39	Gospel
40	Noise
41	Alternativerock
42	Bass
43	Soul
44	Punk
45	Space
46	Meditative
47	Instrumentalpop
48	Instrumentalrock
49	Ethnic
50	Gothic
51	Darkwave
52	Techno-Industrial
53	Electronica
54	Pop-Folk
55	Eurodance
56	Dream
57	Southernrock
58	Comedy
59	Cult
60	Gangsta
61	Top40
62	ChristianRap
63	Pop/Funk
64	Jungle
65	NativeUS
66	Cabaret
67	NewWave
68	Psychedelic
69	Rave
70	Showtunes
71	Trailer
72	Lo-Fi
73	Tribal
74	Acidpunk
75	Acidjazz
76	Polka
77	Retro
78	Musical
79	Rockandroll
80	Hardrock
81	Folk
82	Folk-rock
83	Nationalfolk
84	Swing
85	FastFusion
86	Bebop
87	Latin
88	Revival
89	Celtic
90	Bluegrass
91	Avantgarde
92	Gothicrock
93	Progressiverock
94	Psychedelicrock
95	Symphonicrock
96	SlowRock
97	BigBand
98	Chorus
99	EasyListening
100	Acoustic
101	Humour
102	Speech
103	Chanson
104	Opera
105	Chambermusic
106	Sonata
107	Symphony
108	BootyBass
109	Primus
110	Porngroove
111	Satire
112	SlowJam
113	Club
114	Tango
115	Samba
116	Folklore
117	Ballad
118	Powerballad
119	RhytmicSoul
120	Freestyle
121	Duet
122	Punkrock
123	DrumSolo
124	Acapella
125	Euro-house
126	DanceHall
127	Goa
128	Club-House
129	Hardcore
130	Terror
131	Indie
132	Britpop
133	Negerpunk
134	Polskpunk
135	Beat
136	ChristianGangsta
137	Heavymetal
138	Blackmetal
139	Crossover
140	ContemporaryChristian
141	Christianrock
142	Merengue
143	Salsa
144	Thrashmetal
145	Anime
146	JPop
147	Synthpop
148	LagMusic
149	ArtRock
150	DeepHouse
151	TechHouse
152	Deepstep
153	Dubstep
154	FutureHouse
155	Subground
156	Trap
157	EBM
158	ProgressiveHouse
159	MelbourneBounce
160	DutchHouse
161	ElectroHouse
162	BoilerRoom
163	HardTekno
164	MinimalHouse
165	AmbientHouse
166	ChillOut
167	Dub
168	Hardstyle
170	Drumstep
183	Indierock
\.


--
-- Name: genere_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hanzo
--

SELECT pg_catalog.setval('genere_id_seq', 191, true);


--
-- Data for Name: images; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY images (url, cd_id) FROM stdin;
\.


--
-- Data for Name: musicista; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY musicista (id, nome_arte, genere_id, anno_nascita, isband) FROM stdin;
3	ACDC	18	1973	t
4	Pearl Jam	7	1990	t
6	Gorillaz	41	1998	t
24	David Bowie	67	1962	t
25	Red Hot Chili Peppers	41	1983	t
26	Anthony Kiedis	41	1962	f
28	Metallica	137	1981	t
39	Brian Johnson	80	1947	f
27	Toby Fox	53	1993	f
5	Paul Gilbert	18	1966	f
40	The Black Keys	183	2001	t
50	James Hetfield	137	1963	f
51	Kirk Hammett	10	1962	f
52	Cliff Burton	10	1962	f
53	Lars Ulrich	10	1962	f
54	Chuck Berry	79	1951	t
55	Kool & The Gang	6	1964	t
56	Robert Bell	6	1950	f
57	Ronald Bell	6	1951	f
58	George Brown	6	1949	f
59	Claude Debussy	33	1862	t
60	Alexis Weissenberg	33	1929	f
61	Bee Gees	14	1960	t
63	Bob Marley	17	1961	t
64	Skrillex	153	2002	t
65	Darren Korb	53	1983	f
\.


--
-- Name: musicista_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hanzo
--

SELECT pg_catalog.setval('musicista_id_seq', 65, true);


--
-- Data for Name: ordine; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY ordine (cliente, prezzo_complessivo, modalita_acquisto, modalita_consegna, ip, data, ora, id) FROM stdin;
aleZil	9.99	CARTA_CREDITO	CORRIERE	157.27.142.50	2017-07-03	16:34:21.804336	4
aleZil	9.99	CARTA_CREDITO	CORRIERE	92.223.246.31	2017-07-03	20:50:03.508081	5
aleZil	21.47	CARTA_CREDITO	CORRIERE	92.223.246.31	2017-07-03	20:50:48.941029	6
aleZil	50.00	BONIFICO	CORRIERE	92.223.246.31	2017-07-03	21:02:55.814063	7
P	199.80	CARTA_CREDITO	CORRIERE	92.223.246.31	2017-07-03	21:11:27.644755	8
aleZil	39.98	CARTA_CREDITO	CORRIERE	92.223.246.31	2017-07-03	21:15:55.955617	9
P	75.00	PAYPAL	CORRIERE	92.223.246.31	2017-07-03	21:28:32.077267	10
P	99.90	PAYPAL	POSTA	92.223.246.31	2017-07-03	21:30:30.250399	11
P	22.96	CARTA_CREDITO	POSTA	92.223.246.31	2017-07-03	21:35:31.313457	12
aleZil	11.48	PAYPAL	CORRIERE	92.223.246.31	2017-07-03	21:38:34.827758	13
aleZil	34.44	PAYPAL	CORRIERE	92.223.246.31	2017-07-03	21:41:11.705819	14
aleZil	25.00	BONIFICO	CORRIERE	92.223.246.31	2017-07-03	21:41:34.782748	15
aleZil	29.97	BONIFICO	CORRIERE	157.27.142.116	2017-07-04	09:55:05.242707	16
aleZil	54.42	CARTA_CREDITO	CORRIERE	157.27.142.50	2017-07-04	10:02:30.298852	17
aleZil	11.48	PAYPAL	POSTA	157.27.142.116	2017-07-04	10:38:41.195427	18
aleZil	11.48	PAYPAL	POSTA	157.27.142.116	2017-07-04	16:53:14.774424	19
aleZil	9.99	PAYPAL	CORRIERE	157.27.142.116	2017-07-05	09:54:43.53754	20
aleZil	0.00	BONIFICO	POSTA	157.27.129.98	2017-07-05	16:35:44.360337	21
aleZil	15.00	BONIFICO	POSTA	157.27.129.98	2017-07-05	16:35:55.247188	22
aleZil	26.48	BONIFICO	CORRIERE	157.27.129.98	2017-07-05	16:37:48.221104	23
aleZil	26.48	BONIFICO	CORRIERE	157.27.129.98	2017-07-05	16:37:58.733237	24
aleZil	22.96	PAYPAL	POSTA	157.27.129.98	2017-07-05	16:41:31.970444	25
aleZil	64.98	PAYPAL	CORRIERE	157.27.129.98	2017-07-05	16:44:33.269393	26
Trevizanzan	79.96	BONIFICO	CORRIERE	157.27.142.116	2017-07-06	11:47:17.243561	27
P	29.97	CARTA_CREDITO	CORRIERE	157.27.129.98	2017-07-06	16:13:09.716973	28
aleZil	44.00	PAYPAL	CORRIERE	82.58.183.238	2017-07-07	18:29:42.428902	29
P	15.00	PAYPAL	CORRIERE	82.90.78.120	2017-07-09	11:29:20.627038	30
P	110.00	CARTA_CREDITO	CORRIERE	82.90.78.120	2017-07-09	14:16:07.587246	31
Trevizanzan	59.98	PAYPAL	CORRIERE	157.27.131.204	2017-07-10	09:38:31.812961	32
Trevizanzan	27.00	PAYPAL	CORRIERE	157.27.131.204	2017-07-11	11:19:25.672576	33
Trevizanzan	50.00	BONIFICO	POSTA	157.27.131.204	2017-07-11	15:16:16.592891	34
\.


--
-- Name: ordine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hanzo
--

SELECT pg_catalog.setval('ordine_id_seq', 34, true);


--
-- Data for Name: partecipazione; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY partecipazione (musicista_id, is_titolare, cd_id) FROM stdin;
55	t	28
56	f	28
57	f	28
27	t	21
40	t	32
54	t	26
59	t	29
60	f	29
63	t	31
40	t	14
54	t	27
28	t	24
50	f	24
51	f	24
52	f	24
3	t	11
39	f	11
25	t	23
26	f	23
53	f	24
28	t	33
51	f	33
61	t	30
\.


--
-- Data for Name: personale; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY personale (username, password, nome, cognome) FROM stdin;
zil	1729fb43e51f8aa732df59ff596899b0	Alessandro	Zilotti
Pete	7815696ecbf1c96e6894b779456d330e	Pietro	Prebianca
Trevizanzan	3aa81d7065b1c627cf195aa5ad806115	Andrea	Trevisan
\.


--
-- Data for Name: rigaordine; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY rigaordine (ordine_id, cd_id, qta, prezzo) FROM stdin;
4	21	1	9.99
5	21	1	9.99
6	21	1	9.99
6	23	1	11.48
7	11	2	25.00
8	21	20	9.99
10	11	3	25.00
11	21	10	9.99
12	23	2	11.48
13	23	1	11.48
14	23	3	11.48
15	11	1	25.00
16	21	3	9.99
17	23	3	11.48
17	21	2	9.99
18	23	1	11.48
19	23	1	11.48
20	21	1	9.99
22	24	1	15.00
23	24	1	15.00
24	24	1	15.00
25	23	2	11.48
26	11	1	25.00
26	14	2	19.99
27	14	4	19.99
28	21	3	9.99
29	31	2	22.00
30	24	1	15.00
31	31	5	22.00
32	30	2	29.99
33	27	1	15.00
33	29	1	12.00
34	11	2	25.00
\.


--
-- Data for Name: strumento; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY strumento (id, nome) FROM stdin;
1	Agogo
2	Arciliuto
3	Armonica a bicchieri
4	Armonica a bocca
5	Armonica a doppia tastiera
6	Arpa
7	Arpa celtica
8	Arpa tripla gallese
9	Arpa liuto
10	Arpa paraguaiana
11	Arpicordo
12	Aulos
13	Baglamas
14	Bagpipe
15	Balafon
16	Balalaika
17	Bandoneon
18	Bandourka (chitarra russa)
19	Bandura (chitarra ukraina)
20	Bandurria
21	Banjo
22	Benjo
23	Basso
24	Basso acustico
25	Basso elettrico
26	Basso Tuba
27	Batteria
28	Batteria elettronica
29	Berimbao
30	Bidofono
31	Biniou
32	Bodhrán
33	Bombarda
34	Bombardino
35	Bongo
36	Bouzouki
37	C[modifica wikitesto]
38	Cabasa
39	Cajon
40	Campane
41	Campane tubolari
42	Campionatore
43	Celesta
44	Cembalo
45	Cembalo ad arco
46	Cennamella
47	Cetra
48	Chapman stick
49	Charango
50	Chimta
51	Chitarra
52	Chitarra con doppio manico
53	Chitarra con tre manici
54	Chitarra con quattro manici
55	Chitarra jazz
56	Chitarra folk
57	Chitarra battente
58	Chitarra classica
59	Chitarra elettrica
60	Chitarra a 12 corde
61	Chitarra Warr
62	Chitarrone
63	Cigar box guitar
64	Cimbasso
65	Clarinetto
66	Clarinetto basso
67	Clave
68	Clavicembalo
69	Clavicordo
70	Clavinet
71	Claviorgano
72	Cobza
73	Concertina
74	Congas
75	Contrabbasso
76	Contrabbasso elettrico
77	Contraforte
78	Controfagotto
79	Cornamusa
80	Corneta China
81	Cornetto
82	Corno inglese
83	Corno francese
84	Corno naturale
85	Cromorno
86	Cuatro
87	Cuica
88	Cupa cupa
89	Dabakan
90	Dan-Tranh
91	Darabouka
92	Def Arbani
93	Didgeridoo
94	Dilruba
95	Djembé
96	Dolcimer
97	Dombura
98	Domra
99	Drum circle
100	Drum machine
101	Duduk
102	Dulciana
103	Dulcimer
104	Dun dun
105	Diamonica
106	Ektara
107	Jurgen (Eufonio)
108	Expander
109	Fagotto
110	Fisarmonica
111	Fisarmonica diatonica
112	Fiddle
113	Flagioletto
114	Flauto di pan
115	Flauto dolce
116	Flauto soprano
117	Flauto traverso
118	Flicorno
119	Fortepiano
120	Flauto
121	Flexatone
122	Gaida
123	Gaita galiziana
124	Galoubet
125	Genis
126	Ghatam
127	Ghironda
128	Ghungroo
129	Glassarmonica
130	Glockenspiel
131	Gong
132	Grancassa
133	Guembri
134	Guitar synth
135	Gusli
136	Harmonium
137	Hichiriki
138	Hosho
139	Hagstrom
140	Igil
141	Israj
142	Jembe
143	Kacapi
144	Kantele
145	Kayamba
146	Kalimba
147	Kazoo
148	Khol
149	Kilofono
150	Kora
151	Koto
152	Keytar
153	Laud
154	Launeddas
155	Legnetti
156	Legnetti bitonali
157	Lira da braccio
158	Liuto
159	Lyra organizzata
160	Mandola
161	M'bira
162	Mandora
163	Mandolino
164	Mandoloncello
165	Mandolone
166	Maracas
167	Marimba
168	Marranzano
169	Mellotron
170	Metallofono
171	Mijwiz
172	Mixer
173	Mizmar
174	Moodswinger
175	Moonlander
176	Müsa
177	Nacchere
178	Niatiti
179	Oboe
180	Oboe d'amore
181	Ocarina
182	Ottobasso
183	Onde Martenot
184	Oficleide
185	Organetto
186	Organetto diatonico
187	Organo
188	Organo a rullo
189	Organo Hammond
190	Orutu
191	Ottoni
192	Ottavino
193	Oud
194	P[modifica wikitesto]
195	Palitos
196	Pandeiro
197	Pandura
198	Pan Flute
199	Pakhawaj
200	Pianet
201	Pianoforte
202	Pianoforte digitale
203	Piano elettrico
204	Piatti
205	Piffero
206	Pianola
207	Pipa
208	Pikasso Guitar
209	Pungi
210	Quena
211	Raganella
212	Ramsinga
213	Rauschpfeife
215	Rebab
216	Regale
217	Ribeca
218	Ronroco
219	Rubab
220	Rullante
221	Salmoè
222	Salterio
223	Santoor
224	Sarangi
225	Sarinda
226	Sarod
227	Sassofono
228	Sassofono basso
229	Sarrusofono
230	Scacciapensieri
231	Serpentone
232	Shehnai
233	Shruti Box
234	Sintetizzatore
235	Sintir
236	Siringa
237	Sistro
238	Sitar
239	Shamisen
240	Shekere
241	Shofar
242	Spinetta
243	Steel Drum
244	Strumstick
245	Synclavier
246	Synthaxe
247	Sax
248	Saz
249	Tabla
250	Taiko
251	Tamburino
252	Tamburo
253	Tamburo a cornice
254	Tamburo parlante
255	Tamburello
256	Tamburello basco
257	Tastiera
258	Teponaztli
259	Theremin
260	Timbales
261	Timpano
262	Timple
263	Tin whistle
264	Tiorba
265	Toaca
266	Tom-Tom
267	Tres
268	Trocca
269	Triangolo
270	Tromba
271	Tromba da tirarsi
272	Trombino
273	Trombone
274	Trombone basso
275	Tromboon
276	Tuba
277	Tubax
278	Txalaparta
279	Txistu
281	Ukulele
282	Uilleann pipes
283	Valiha
284	Veena
285	Vibrafono
286	Vibrandoneon
287	Viella
288	Vihuela
289	Viola
290	Viola d'amore
291	Viola d'amore a chiavi
292	Viola da gamba
293	Viola di bordone
294	Violino
295	Violino elettrico
296	Violoncello
297	Violoncello barocco
298	Violoncello elettrico
299	Violoncello piccolo
300	Violoncello tenore
301	Violone
302	Vina
303	Virginale
304	Vuvuzela
305	Wood-block
306	Xilofono
307	Zampogna
308	Zufolo
309	Zucutefù
310	Zurna
311	Voce
\.


--
-- Name: strumento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hanzo
--

SELECT pg_catalog.setval('strumento_id_seq', 311, true);


--
-- Data for Name: utilizzo; Type: TABLE DATA; Schema: public; Owner: hanzo
--

COPY utilizzo (musicista_id, strumento_id) FROM stdin;
24	311
24	51
24	201
25	311
25	23
25	51
25	27
26	311
27	202
27	206
28	51
28	59
28	23
28	27
39	311
50	311
50	59
51	59
52	25
53	27
54	51
54	311
56	23
57	227
58	27
60	201
64	234
65	234
\.


--
-- Name: brano brano_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY brano
    ADD CONSTRAINT brano_pkey PRIMARY KEY (nome, ordine, cd_id);


--
-- Name: cd cd_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY cd
    ADD CONSTRAINT cd_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (username);


--
-- Name: musicista constraintname; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY musicista
    ADD CONSTRAINT constraintname UNIQUE (nome_arte);


--
-- Name: genere genere_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY genere
    ADD CONSTRAINT genere_pkey PRIMARY KEY (id);


--
-- Name: musicista musicista_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY musicista
    ADD CONSTRAINT musicista_pkey PRIMARY KEY (id);


--
-- Name: genere nome_unico; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY genere
    ADD CONSTRAINT nome_unico UNIQUE (nome);


--
-- Name: ordine ordine_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT ordine_pkey PRIMARY KEY (id);


--
-- Name: partecipazione partecipazione_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY partecipazione
    ADD CONSTRAINT partecipazione_pkey PRIMARY KEY (musicista_id, cd_id);


--
-- Name: personale personale_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY personale
    ADD CONSTRAINT personale_pkey PRIMARY KEY (username);


--
-- Name: strumento strumento_nome_key; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY strumento
    ADD CONSTRAINT strumento_nome_key UNIQUE (nome);


--
-- Name: strumento strumento_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY strumento
    ADD CONSTRAINT strumento_pkey PRIMARY KEY (id);


--
-- Name: utilizzo utilizzo_pkey; Type: CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY utilizzo
    ADD CONSTRAINT utilizzo_pkey PRIMARY KEY (musicista_id, strumento_id);


--
-- Name: brano brano_cd_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY brano
    ADD CONSTRAINT brano_cd_id_fkey FOREIGN KEY (cd_id) REFERENCES cd(id) ON DELETE CASCADE;


--
-- Name: cd cd_genere_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY cd
    ADD CONSTRAINT cd_genere_id_fkey FOREIGN KEY (genere_id) REFERENCES genere(id);


--
-- Name: images images_cd_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY images
    ADD CONSTRAINT images_cd_id_fkey FOREIGN KEY (cd_id) REFERENCES cd(id);


--
-- Name: musicista musicista_genere_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY musicista
    ADD CONSTRAINT musicista_genere_id_fkey FOREIGN KEY (genere_id) REFERENCES genere(id);


--
-- Name: ordine ordine_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY ordine
    ADD CONSTRAINT ordine_cliente_fkey FOREIGN KEY (cliente) REFERENCES cliente(username);


--
-- Name: partecipazione partecipazione_cd_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY partecipazione
    ADD CONSTRAINT partecipazione_cd_id_fkey FOREIGN KEY (cd_id) REFERENCES cd(id) ON DELETE CASCADE;


--
-- Name: partecipazione partecipazione_musicista_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY partecipazione
    ADD CONSTRAINT partecipazione_musicista_id_fkey FOREIGN KEY (musicista_id) REFERENCES musicista(id);


--
-- Name: rigaordine rigaordine_cd_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY rigaordine
    ADD CONSTRAINT rigaordine_cd_id_fkey FOREIGN KEY (cd_id) REFERENCES cd(id);


--
-- Name: rigaordine rigaordine_ordine_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY rigaordine
    ADD CONSTRAINT rigaordine_ordine_id_fkey FOREIGN KEY (ordine_id) REFERENCES ordine(id) ON DELETE CASCADE;


--
-- Name: utilizzo utilizzo_musicista_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY utilizzo
    ADD CONSTRAINT utilizzo_musicista_id_fkey FOREIGN KEY (musicista_id) REFERENCES musicista(id);


--
-- Name: utilizzo utilizzo_strumento_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: hanzo
--

ALTER TABLE ONLY utilizzo
    ADD CONSTRAINT utilizzo_strumento_id_fkey FOREIGN KEY (strumento_id) REFERENCES strumento(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: hanzo
--

REVOKE ALL ON SCHEMA public FROM rdsadmin;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO hanzo;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

