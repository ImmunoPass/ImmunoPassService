--
-- TOC entry 8 (class 2615 OID 16431)
-- Name: immunopass; Type: SCHEMA; Schema: -; Owner: elemential
--

CREATE SCHEMA immunopass;


ALTER SCHEMA immunopass OWNER TO elemential;

--
-- TOC entry 217 (class 1259 OID 16651)
-- Name: account_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.account_id_seq OWNER TO elemential;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16432)
-- Name: account; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.account (
    id bigint DEFAULT nextval('immunopass.account_id_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    identifier character varying(255) NOT NULL,
    identifier_type character varying(255) NOT NULL,
    password_hash character varying(255),
    organization_id bigint NOT NULL,
    organization_type character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE immunopass.account OWNER TO elemential;

--
-- TOC entry 218 (class 1259 OID 16653)
-- Name: immunopass_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.immunopass_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.immunopass_id_seq OWNER TO elemential;

--
-- TOC entry 210 (class 1259 OID 16440)
-- Name: immunopass; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.immunopass (
    id bigint DEFAULT nextval('immunopass.immunopass_id_seq'::regclass) NOT NULL,
    user_name character varying(255) NOT NULL,
    user_mobile character varying(255) NOT NULL,
    user_emp_id character varying(255),
    user_government_id character varying(255),
    user_location character varying(255),
    immuno_test_result character varying(255) NOT NULL,
    immunopass_code character varying(255) NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE immunopass.immunopass OWNER TO elemential;

--
-- TOC entry 219 (class 1259 OID 16655)
-- Name: order_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.order_id_seq OWNER TO elemential;

--
-- TOC entry 211 (class 1259 OID 16448)
-- Name: order; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass."order" (
    id bigint DEFAULT nextval('immunopass.order_id_seq'::regclass) NOT NULL,
    voucher_count integer NOT NULL,
    uploaded_file character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by bigint
);


ALTER TABLE immunopass."order" OWNER TO elemential;

--
-- TOC entry 220 (class 1259 OID 16657)
-- Name: organization_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.organization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.organization_id_seq OWNER TO elemential;

--
-- TOC entry 212 (class 1259 OID 16456)
-- Name: organization; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.organization (
    id bigint DEFAULT nextval('immunopass.organization_id_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    total_vouchers integer DEFAULT 0 NOT NULL,
    used_vouchers integer DEFAULT 0 NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE immunopass.organization OWNER TO elemential;

--
-- TOC entry 216 (class 1259 OID 16639)
-- Name: otp; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.otp
(
    id bigint NOT NULL DEFAULT nextval('immunopass.otp_id_seq'::regclass),
    identifier character varying(255) COLLATE pg_catalog."default",
    identifier_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    otp character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    retry_count integer NOT NULL,
    verification_attempts integer NOT NULL DEFAULT 0,
    valid_till timestamp with time zone NOT NULL DEFAULT (CURRENT_TIMESTAMP + '01:00:00'::interval),
    created_at timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT otp_pkey PRIMARY KEY (id)
)

ALTER TABLE immunopass.otp OWNER TO elemential;

--
-- TOC entry 215 (class 1259 OID 16637)
-- Name: otp_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.otp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.otp_id_seq OWNER TO elemential;

--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 215
-- Name: otp_id_seq; Type: SEQUENCE OWNED BY; Schema: immunopass; Owner: elemential
--

ALTER SEQUENCE immunopass.otp_id_seq OWNED BY immunopass.otp.id;


--
-- TOC entry 221 (class 1259 OID 16659)
-- Name: pathology_lab_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.pathology_lab_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.pathology_lab_id_seq OWNER TO elemential;

--
-- TOC entry 213 (class 1259 OID 16466)
-- Name: pathology_lab; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.pathology_lab (
    id bigint DEFAULT nextval('immunopass.pathology_lab_id_seq'::regclass) NOT NULL,
    name character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE immunopass.pathology_lab OWNER TO elemential;

--
-- TOC entry 222 (class 1259 OID 16661)
-- Name: voucher_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.voucher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.voucher_id_seq OWNER TO elemential;

--
-- TOC entry 214 (class 1259 OID 16474)
-- Name: voucher; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.voucher (
    id bigint DEFAULT nextval('immunopass.voucher_id_seq'::regclass) NOT NULL,
    voucher_code character varying(255) NOT NULL,
    issuer_id bigint NOT NULL,
    user_name character varying(255) NOT NULL,
    user_mobile character varying(255) NOT NULL,
    user_emp_id character varying(255),
    user_government_id character varying(255),
    user_location character varying(255),
    status character varying(255) NOT NULL,
    order_id bigint NOT NULL,
    immunopass_id bigint,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    pathology_lab_id bigint
);


ALTER TABLE immunopass.voucher OWNER TO elemential;

--
-- TOC entry 2812 (class 2604 OID 16642)
-- Name: otp id; Type: DEFAULT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.otp ALTER COLUMN id SET DEFAULT nextval('immunopass.otp_id_seq'::regclass);


--
-- TOC entry 2817 (class 2606 OID 16439)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 2819 (class 2606 OID 16670)
-- Name: account identifier_uniq; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.account
    ADD CONSTRAINT identifier_uniq UNIQUE (identifier);


--
-- TOC entry 2821 (class 2606 OID 16672)
-- Name: immunopass immunopass_code_uniq; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.immunopass
    ADD CONSTRAINT immunopass_code_uniq UNIQUE (immunopass_code);


--
-- TOC entry 2823 (class 2606 OID 16447)
-- Name: immunopass immunopass_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.immunopass
    ADD CONSTRAINT immunopass_pkey PRIMARY KEY (id);


--
-- TOC entry 2826 (class 2606 OID 16455)
-- Name: order order_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- TOC entry 2828 (class 2606 OID 16465)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- TOC entry 2838 (class 2606 OID 16650)
-- Name: otp otp_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.otp
    ADD CONSTRAINT otp_pkey PRIMARY KEY (id);


--
-- TOC entry 2830 (class 2606 OID 16473)
-- Name: pathology_lab pathology_lab_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.pathology_lab
    ADD CONSTRAINT pathology_lab_pkey PRIMARY KEY (id);


--
-- TOC entry 2836 (class 2606 OID 16481)
-- Name: voucher voucher_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT voucher_pkey PRIMARY KEY (id);


--
-- TOC entry 2831 (class 1259 OID 16499)
-- Name: fk_immunopass_id_idx; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fk_immunopass_id_idx ON immunopass.voucher USING btree (immunopass_id);


--
-- TOC entry 2832 (class 1259 OID 16497)
-- Name: fk_issuer_id_idx; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fk_issuer_id_idx ON immunopass.voucher USING btree (issuer_id);


--
-- TOC entry 2833 (class 1259 OID 16498)
-- Name: fk_order_id_idx; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fk_order_id_idx ON immunopass.voucher USING btree (order_id);


--
-- TOC entry 2824 (class 1259 OID 16636)
-- Name: fki_fk_created_by; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_created_by ON immunopass."order" USING btree (created_by);


--
-- TOC entry 2834 (class 1259 OID 16611)
-- Name: fki_fk_pathology_lab_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_pathology_lab_id ON immunopass.voucher USING btree (pathology_lab_id);


--
-- TOC entry 2839 (class 2606 OID 16631)
-- Name: order fk_created_by; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass."order"
    ADD CONSTRAINT fk_created_by FOREIGN KEY (created_by) REFERENCES immunopass.account(id);


--
-- TOC entry 2842 (class 2606 OID 16492)
-- Name: voucher fk_immunopass_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_immunopass_id FOREIGN KEY (immunopass_id) REFERENCES immunopass.immunopass(id);


--
-- TOC entry 2840 (class 2606 OID 16482)
-- Name: voucher fk_issuer_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_issuer_id FOREIGN KEY (issuer_id) REFERENCES immunopass.account(id);


--
-- TOC entry 2841 (class 2606 OID 16487)
-- Name: voucher fk_order_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES immunopass."order"(id);


--
-- TOC entry 2843 (class 2606 OID 16606)
-- Name: voucher fk_pathology_lab_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_pathology_lab_id FOREIGN KEY (pathology_lab_id) REFERENCES immunopass.pathology_lab(id);


-- Completed on 2020-04-05 01:52:48 IST

--
-- PostgreSQL database dump complete
--

alter table voucher add column user_govt_id_type text;
alter table voucher add column retry_count bigint default 0;
alter table voucher add column last_failure_reason text;
