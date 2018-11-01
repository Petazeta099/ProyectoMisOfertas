-- Generado por Oracle SQL Developer Data Modeler 18.2.0.179.0756
--   en:        2018-10-17 11:19:36 CLST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE administrador (
    run          VARCHAR2(10) NOT NULL,
    p_nombre     VARCHAR2(15) NOT NULL,
    s_nombre     VARCHAR2(15),
    apellido_p   VARCHAR2(15) NOT NULL,
    apellido_m   VARCHAR2(15) NOT NULL,
    clave        VARCHAR2(255) NOT NULL,
    activo       CHAR(1) NOT NULL
)
LOGGING;

ALTER TABLE administrador ADD CONSTRAINT administrador_pk PRIMARY KEY ( run );

CREATE TABLE categoria (
    id     INTEGER NOT NULL,
    tipo   VARCHAR2(13) NOT NULL
)
LOGGING;

ALTER TABLE categoria ADD CONSTRAINT categoria_pk PRIMARY KEY ( id );

CREATE TABLE comuna (
    id          INTEGER NOT NULL,
    nombre      VARCHAR2(50) NOT NULL,
    region_id   INTEGER NOT NULL
)
LOGGING;

ALTER TABLE comuna ADD CONSTRAINT comuna_pk PRIMARY KEY ( id );

CREATE TABLE consumidor (
    run          VARCHAR2(10) NOT NULL,
    p_nombre     VARCHAR2(15) NOT NULL,
    s_nombre     VARCHAR2(15),
    apellido_p   VARCHAR2(15) NOT NULL,
    apellido_m   VARCHAR2(15) NOT NULL,
    email        VARCHAR2(35) NOT NULL,
    clave        VARCHAR2(255) NOT NULL,
    puntaje      INTEGER NOT NULL,
    activo       CHAR(1) NOT NULL,
    suscrito     CHAR(1) NOT NULL,
    comuna_id    INTEGER NOT NULL
)
LOGGING;

ALTER TABLE consumidor ADD CONSTRAINT consumidor_pk PRIMARY KEY ( run );

CREATE TABLE encargado (
    run          VARCHAR2(10) NOT NULL,
    p_nombre     VARCHAR2(15) NOT NULL,
    s_nombre     VARCHAR2(15),
    apellido_p   VARCHAR2(15) NOT NULL,
    apellido_m   VARCHAR2(15) NOT NULL,
    clave        VARCHAR2(255) NOT NULL,
    activo       CHAR(1) NOT NULL,
    retail_rut   VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE encargado ADD CONSTRAINT encargado_pk PRIMARY KEY ( run );

CREATE TABLE evaluacion (
    id             INTEGER NOT NULL,
    fecha_compra   DATE NOT NULL,
    imagen         BLOB NOT NULL,
    comentario     VARCHAR2(200)
)
LOGGING;

ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_pk PRIMARY KEY ( id );

CREATE TABLE gerente (
    run          VARCHAR2(10) NOT NULL,
    p_nombre     VARCHAR2(15) NOT NULL,
    s_nombre     VARCHAR2(15),
    apellido_p   VARCHAR2(15) NOT NULL,
    apellido_m   VARCHAR2(15) NOT NULL,
    clave        VARCHAR2(255) NOT NULL,
    activo       CHAR(1) NOT NULL
)
LOGGING;

ALTER TABLE gerente ADD CONSTRAINT gerente_pk PRIMARY KEY ( run );

CREATE TABLE marca (
    id       INTEGER NOT NULL,
    nombre   VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE marca ADD CONSTRAINT marca_pk PRIMARY KEY ( id );

CREATE TABLE oferta (
    id              INTEGER NOT NULL,
    titulo          VARCHAR2(30) NOT NULL,
    descripcion     VARCHAR2(100) NOT NULL,
    fecha_inicio    DATE NOT NULL,
    fecha_termino   DATE NOT NULL,
    precio_normal   INTEGER NOT NULL,
    precio_oferta   INTEGER NOT NULL,
    compra_min      INTEGER NOT NULL,
    compra_max      INTEGER NOT NULL,
    activa          CHAR(1) NOT NULL,
    encargado_run   VARCHAR2(10) NOT NULL
)
LOGGING;

ALTER TABLE oferta ADD CONSTRAINT oferta_pk PRIMARY KEY ( id );

CREATE TABLE oferta_producto (
    producto_sku   VARCHAR2(15) NOT NULL,
    oferta_id      INTEGER NOT NULL
)
LOGGING;

ALTER TABLE oferta_producto ADD CONSTRAINT oferta_producto_pk PRIMARY KEY ( producto_sku,
                                                                            oferta_id );

CREATE TABLE producto (
    sku            VARCHAR2(15) NOT NULL,
    nombre         VARCHAR2(30) NOT NULL,
    descripcion    VARCHAR2(100) NOT NULL,
    categoria_id   INTEGER NOT NULL,
    marca_id       INTEGER NOT NULL
)
LOGGING;

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( sku );

CREATE TABLE region (
    id       INTEGER NOT NULL,
    nombre   VARCHAR2(100) NOT NULL
)
LOGGING;

ALTER TABLE region ADD CONSTRAINT region_pk PRIMARY KEY ( id );

CREATE TABLE retail (
    rut            VARCHAR2(30) NOT NULL,
    razon_social   VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE retail ADD CONSTRAINT retail_pk PRIMARY KEY ( rut );

CREATE TABLE sucursal (
    id           INTEGER NOT NULL,
    nombre       VARCHAR2(30) NOT NULL,
    direccion    VARCHAR2(100) NOT NULL,
    comuna_id    INTEGER NOT NULL,
    retail_rut   VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE sucursal ADD CONSTRAINT sucursal_pk PRIMARY KEY ( id );

CREATE TABLE valoracion (
    id               INTEGER NOT NULL,
    escala           VARCHAR2(10) NOT NULL,
    evaluacion_id    INTEGER NOT NULL,
    consumidor_run   VARCHAR2(10) NOT NULL,
    oferta_id        INTEGER NOT NULL
)
LOGGING;

ALTER TABLE valoracion ADD CONSTRAINT valoracion_pk PRIMARY KEY ( id );

ALTER TABLE comuna
    ADD CONSTRAINT comuna_region_fk FOREIGN KEY ( region_id )
        REFERENCES region ( id )
    NOT DEFERRABLE;

ALTER TABLE consumidor
    ADD CONSTRAINT consumidor_comuna_fk FOREIGN KEY ( comuna_id )
        REFERENCES comuna ( id )
    NOT DEFERRABLE;

ALTER TABLE encargado
    ADD CONSTRAINT encargado_retail_fk FOREIGN KEY ( retail_rut )
        REFERENCES retail ( rut )
    NOT DEFERRABLE;

ALTER TABLE oferta
    ADD CONSTRAINT oferta_encargado_fk FOREIGN KEY ( encargado_run )
        REFERENCES encargado ( run )
    NOT DEFERRABLE;

ALTER TABLE oferta_producto
    ADD CONSTRAINT oferta_producto_oferta_fk FOREIGN KEY ( oferta_id )
        REFERENCES oferta ( id )
    NOT DEFERRABLE;

ALTER TABLE oferta_producto
    ADD CONSTRAINT oferta_producto_producto_fk FOREIGN KEY ( producto_sku )
        REFERENCES producto ( sku )
    NOT DEFERRABLE;

ALTER TABLE producto
    ADD CONSTRAINT producto_categoria_fk FOREIGN KEY ( categoria_id )
        REFERENCES categoria ( id )
    NOT DEFERRABLE;

ALTER TABLE producto
    ADD CONSTRAINT producto_marca_fk FOREIGN KEY ( marca_id )
        REFERENCES marca ( id )
    NOT DEFERRABLE;

ALTER TABLE sucursal
    ADD CONSTRAINT sucursal_comuna_fk FOREIGN KEY ( comuna_id )
        REFERENCES comuna ( id )
    NOT DEFERRABLE;

ALTER TABLE sucursal
    ADD CONSTRAINT sucursal_retail_fk FOREIGN KEY ( retail_rut )
        REFERENCES retail ( rut )
    NOT DEFERRABLE;

ALTER TABLE valoracion
    ADD CONSTRAINT valoracion_consumidor_fk FOREIGN KEY ( consumidor_run )
        REFERENCES consumidor ( run )
    NOT DEFERRABLE;

ALTER TABLE valoracion
    ADD CONSTRAINT valoracion_evaluacion_fk FOREIGN KEY ( evaluacion_id )
        REFERENCES evaluacion ( id )
    NOT DEFERRABLE;

ALTER TABLE valoracion
    ADD CONSTRAINT valoracion_oferta_fk FOREIGN KEY ( oferta_id )
        REFERENCES oferta ( id )
    NOT DEFERRABLE;



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            15
-- CREATE INDEX                             0
-- ALTER TABLE                             28
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
