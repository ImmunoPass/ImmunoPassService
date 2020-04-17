--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Ubuntu 12.2-2.pgdg16.04+1)
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-16 22:49:45 IST

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
    organization_id bigint,
    pathology_lab_id bigint
    status character varying(255) NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
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
    immunopass_code character varying(255) NOT NULL,
    user_name character varying(255) NOT NULL,
    user_mobile character varying(255) NOT NULL,
    user_emp_id character varying(255),
    user_government_id character varying(255),
    user_location character varying(255),
    immuno_test_result character varying(255) NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE immunopass.immunopass OWNER TO elemential;

--
-- TOC entry 219 (class 1259 OID 16655)
-- Name: voucher_order_id_seq; Type: SEQUENCE; Schema: immunopass; Owner: elemential
--

CREATE SEQUENCE immunopass.voucher_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE immunopass.voucher_order_id_seq OWNER TO elemential;

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
    alloted_vouchers integer DEFAULT 0 NOT NULL,
    redeemed_vouchers integer DEFAULT 0 NOT NULL
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
);


ALTER TABLE immunopass.organization OWNER TO elemential;

--
-- TOC entry 216 (class 1259 OID 16639)
-- Name: otp; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.otp (
    id bigint NOT NULL,
    identifier character varying(255),
    identifier_type character varying(255) NOT NULL,
    otp character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    retry_count integer NOT NULL,
    verification_attempts integer DEFAULT 0 NOT NULL
    valid_till timestamp with time zone DEFAULT (CURRENT_TIMESTAMP + '01:00:00'::interval) NOT NULL,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
);


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
-- TOC entry 2998 (class 0 OID 0)
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
    issuer_account_id bigint NOT NULL,
    issuer_organization_id bigint NOT NULL,
    user_name character varying(255) NOT NULL,
    user_mobile character varying(255) NOT NULL,
    user_emp_id character varying(255),
    user_government_id character varying(255),
    user_govt_id_type character varying(255),
    user_location character varying(255),
    status character varying(255) NOT NULL,
    order_id bigint NOT NULL,
    immunopass_id bigint,
    redeemed_account_id bigint
    redeemed_pathology_lab_id bigint,
    retry_count integer DEFAULT 0 NOT NULL,
    last_failure_reason character varying(255),
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
);


ALTER TABLE immunopass.voucher OWNER TO elemential;

--
-- TOC entry 211 (class 1259 OID 16448)
-- Name: voucher_order; Type: TABLE; Schema: immunopass; Owner: elemential
--

CREATE TABLE immunopass.voucher_order (
    id bigint DEFAULT nextval('immunopass.voucher_order_id_seq'::regclass) NOT NULL,
    voucher_count integer NOT NULL,
    uploaded_file character varying(255) NOT NULL,
    created_account_id bigint NOT NULL,
    created_organization_id bigint NOT NULL
    status character varying(255) NOT NULL,
    created_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp(0) with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
);


ALTER TABLE immunopass.voucher_order OWNER TO elemential;

--
-- TOC entry 2814 (class 2604 OID 16642)
-- Name: otp id; Type: DEFAULT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.otp ALTER COLUMN id SET DEFAULT nextval('immunopass.otp_id_seq'::regclass);


--
-- TOC entry 2820 (class 2606 OID 16439)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 2824 (class 2606 OID 16670)
-- Name: account identifier_uniq; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.account
    ADD CONSTRAINT identifier_uniq UNIQUE (identifier);


--
-- TOC entry 2829 (class 2606 OID 16672)
-- Name: immunopass immunopass_code_uniq; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.immunopass
    ADD CONSTRAINT immunopass_code_uniq UNIQUE (immunopass_code);


--
-- TOC entry 2831 (class 2606 OID 16447)
-- Name: immunopass immunopass_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.immunopass
    ADD CONSTRAINT immunopass_pkey PRIMARY KEY (id);


--
-- TOC entry 2836 (class 2606 OID 16455)
-- Name: voucher_order order_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher_order
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- TOC entry 2838 (class 2606 OID 16465)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- TOC entry 2855 (class 2606 OID 16650)
-- Name: otp otp_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.otp
    ADD CONSTRAINT otp_pkey PRIMARY KEY (id);


--
-- TOC entry 2840 (class 2606 OID 16473)
-- Name: pathology_lab pathology_lab_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.pathology_lab
    ADD CONSTRAINT pathology_lab_pkey PRIMARY KEY (id);


--
-- TOC entry 2850 (class 2606 OID 16819)
-- Name: voucher uniq_voucher_code; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT uniq_voucher_code UNIQUE (voucher_code);


--
-- TOC entry 2852 (class 2606 OID 16481)
-- Name: voucher voucher_pkey; Type: CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT voucher_pkey PRIMARY KEY (id);


--
-- TOC entry 2821 (class 1259 OID 16680)
-- Name: fki_fk_acct_organization_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_acct_organization_id ON immunopass.account USING btree (organization_id);


--
-- TOC entry 2822 (class 1259 OID 16691)
-- Name: fki_fk_acct_path_lab_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_acct_path_lab_id ON immunopass.account USING btree (pathology_lab_id);


--
-- TOC entry 2832 (class 1259 OID 16636)
-- Name: fki_fk_created_created_account_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_created_created_account_id ON immunopass.voucher_order USING btree (created_account_id);


--
-- TOC entry 2833 (class 1259 OID 16813)
-- Name: fki_fk_created_created_organization_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_created_created_organization_id ON immunopass.voucher_order USING btree (created_organization_id);


--
-- TOC entry 2841 (class 1259 OID 16499)
-- Name: fki_fk_immunopass_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_immunopass_id ON immunopass.voucher USING btree (immunopass_id);


--
-- TOC entry 2842 (class 1259 OID 16815)
-- Name: fki_fk_issuer_account_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_issuer_account_id ON immunopass.voucher USING btree (issuer_account_id);


--
-- TOC entry 2843 (class 1259 OID 16738)
-- Name: fki_fk_issuer_organization_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_issuer_organization_id ON immunopass.voucher USING btree (issuer_organization_id);


--
-- TOC entry 2844 (class 1259 OID 16498)
-- Name: fki_fk_order_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_order_id ON immunopass.voucher USING btree (order_id);


--
-- TOC entry 2845 (class 1259 OID 16611)
-- Name: fki_fk_pathology_lab_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_pathology_lab_id ON immunopass.voucher USING btree (redeemed_pathology_lab_id);


--
-- TOC entry 2846 (class 1259 OID 16816)
-- Name: fki_fk_redeemed_account_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_redeemed_account_id ON immunopass.voucher USING btree (redeemed_account_id);


--
-- TOC entry 2847 (class 1259 OID 16817)
-- Name: fki_fk_redeemed_pathology_lab_id; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX fki_fk_redeemed_pathology_lab_id ON immunopass.voucher USING btree (redeemed_pathology_lab_id);


--
-- TOC entry 2826 (class 1259 OID 16811)
-- Name: idx_immunopass_mobile; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX idx_immunopass_mobile ON immunopass.immunopass USING btree (user_mobile);


--
-- TOC entry 2853 (class 1259 OID 16812)
-- Name: idx_otp_identifier; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX idx_otp_identifier ON immunopass.otp USING btree (identifier);


--
-- TOC entry 2825 (class 1259 OID 16809)
-- Name: idx_uniq_acct_identifier; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE UNIQUE INDEX idx_uniq_acct_identifier ON immunopass.account USING btree (identifier);


--
-- TOC entry 2827 (class 1259 OID 16810)
-- Name: idx_uniq_immunopass_code; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE UNIQUE INDEX idx_uniq_immunopass_code ON immunopass.immunopass USING btree (immunopass_code);


--
-- TOC entry 2848 (class 1259 OID 16820)
-- Name: idx_uniq_voucher_code; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE UNIQUE INDEX idx_uniq_voucher_code ON immunopass.voucher USING btree (voucher_code);


--
-- TOC entry 2834 (class 1259 OID 16814)
-- Name: idx_voucher_order_status; Type: INDEX; Schema: immunopass; Owner: elemential
--

CREATE INDEX idx_voucher_order_status ON immunopass.voucher_order USING btree (status);


--
-- TOC entry 2856 (class 2606 OID 16675)
-- Name: account fk_acct_organization_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.account
    ADD CONSTRAINT fk_acct_organization_id FOREIGN KEY (organization_id) REFERENCES immunopass.organization(id);


--
-- TOC entry 2857 (class 2606 OID 16686)
-- Name: account fk_acct_path_lab_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.account
    ADD CONSTRAINT fk_acct_path_lab_id FOREIGN KEY (pathology_lab_id) REFERENCES immunopass.pathology_lab(id);


--
-- TOC entry 2858 (class 2606 OID 16631)
-- Name: voucher_order fk_created_account_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher_order
    ADD CONSTRAINT fk_created_account_id FOREIGN KEY (created_account_id) REFERENCES immunopass.account(id);


--
-- TOC entry 2859 (class 2606 OID 16733)
-- Name: voucher_order fk_created_organization_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher_order
    ADD CONSTRAINT fk_created_organization_id FOREIGN KEY (created_organization_id) REFERENCES immunopass.organization(id);


--
-- TOC entry 2861 (class 2606 OID 16492)
-- Name: voucher fk_immunopass_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_immunopass_id FOREIGN KEY (immunopass_id) REFERENCES immunopass.immunopass(id);


--
-- TOC entry 2863 (class 2606 OID 16482)
-- Name: voucher fk_issuer_account_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_issuer_account_id FOREIGN KEY (issuer_account_id) REFERENCES immunopass.account(id);


--
-- TOC entry 2865 (class 2606 OID 16739)
-- Name: voucher fk_issuer_organization_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_issuer_organization_id FOREIGN KEY (issuer_organization_id) REFERENCES immunopass.organization(id);


--
-- TOC entry 2860 (class 2606 OID 16487)
-- Name: voucher fk_order_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES immunopass.voucher_order(id);


--
-- TOC entry 2862 (class 2606 OID 16606)
-- Name: voucher fk_pathology_lab_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_pathology_lab_id FOREIGN KEY (redeemed_pathology_lab_id) REFERENCES immunopass.pathology_lab(id);


--
-- TOC entry 2866 (class 2606 OID 16756)
-- Name: voucher fk_redeemed_account_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_redeemed_account_id FOREIGN KEY (redeemed_account_id) REFERENCES immunopass.account(id);


--
-- TOC entry 2864 (class 2606 OID 16705)
-- Name: voucher fk_redeemed_pathology_lab_id; Type: FK CONSTRAINT; Schema: immunopass; Owner: elemential
--

ALTER TABLE ONLY immunopass.voucher
    ADD CONSTRAINT fk_redeemed_pathology_lab_id FOREIGN KEY (redeemed_pathology_lab_id) REFERENCES immunopass.pathology_lab(id);


-- Completed on 2020-04-16 22:49:50 IST

--
-- PostgreSQL database dump complete
--

