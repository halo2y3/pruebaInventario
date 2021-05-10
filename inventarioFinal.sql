/*
Navicat PGSQL Data Transfer

Source Server         : ADMINTC DEMO
Source Server Version : 90224
Source Host           : 172.26.123.100:5432
Source Database       : inventario
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90224
File Encoding         : 65001

Date: 2021-05-09 23:13:14
*/


-- ----------------------------
-- Sequence structure for cargos_idcargo_seq
-- ----------------------------
DROP SEQUENCE "cargos_idcargo_seq";
CREATE SEQUENCE "cargos_idcargo_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 5
 CACHE 1;
SELECT setval('"public"."cargos_idcargo_seq"', 5, true);

-- ----------------------------
-- Sequence structure for productos_idproducto_seq
-- ----------------------------
DROP SEQUENCE "productos_idproducto_seq";
CREATE SEQUENCE "productos_idproducto_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;
SELECT setval('"public"."productos_idproducto_seq"', 4, true);

-- ----------------------------
-- Sequence structure for resgistromercancia_idregistro_seq
-- ----------------------------
DROP SEQUENCE "resgistromercancia_idregistro_seq";
CREATE SEQUENCE "resgistromercancia_idregistro_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 5
 CACHE 1;
SELECT setval('"public"."resgistromercancia_idregistro_seq"', 5, true);

-- ----------------------------
-- Sequence structure for usuarios_idusuario_seq
-- ----------------------------
DROP SEQUENCE "usuarios_idusuario_seq";
CREATE SEQUENCE "usuarios_idusuario_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 5
 CACHE 1;
SELECT setval('"public"."usuarios_idusuario_seq"', 5, true);

-- ----------------------------
-- Table structure for cargos
-- ----------------------------
DROP TABLE IF EXISTS "cargos";
CREATE TABLE "cargos" (
"idcargo" int4 DEFAULT nextval('cargos_idcargo_seq'::regclass) NOT NULL,
"nombrecargo" varchar(100) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cargos
-- ----------------------------
BEGIN;
INSERT INTO "cargos" VALUES ('1', 'Asesor de ventas');
INSERT INTO "cargos" VALUES ('2', 'Administrador');
INSERT INTO "cargos" VALUES ('3', 'Soporte');
COMMIT;

-- ----------------------------
-- Table structure for productos
-- ----------------------------
DROP TABLE IF EXISTS "productos";
CREATE TABLE "productos" (
"idproducto" int4 DEFAULT nextval('productos_idproducto_seq'::regclass) NOT NULL,
"nombreproducto" varchar(150) COLLATE "default" NOT NULL,
"fechacreacion" timestamp(6) NOT NULL,
"fechamodificacion" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of productos
-- ----------------------------
BEGIN;
INSERT INTO "productos" VALUES ('1', 'Pruebas1', '2021-05-09 22:49:38', null);
COMMIT;

-- ----------------------------
-- Table structure for resgistromercancia
-- ----------------------------
DROP TABLE IF EXISTS "resgistromercancia";
CREATE TABLE "resgistromercancia" (
"idregistro" int4 DEFAULT nextval('resgistromercancia_idregistro_seq'::regclass) NOT NULL,
"cantidad" int4 NOT NULL,
"fecharegistro" timestamp(6) NOT NULL,
"fechamodificacion" timestamp(6),
"fechaingresoegreso" date NOT NULL,
"tiporegistro" varchar(10) COLLATE "default" NOT NULL,
"usuarioidregistrador" int4 NOT NULL,
"usuarioidmodificador" int4,
"productoidproducto" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of resgistromercancia
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS "usuarios";
CREATE TABLE "usuarios" (
"idusuario" int4 DEFAULT nextval('usuarios_idusuario_seq'::regclass) NOT NULL,
"nombreusuario" varchar(200) COLLATE "default" NOT NULL,
"edad" int4 NOT NULL,
"fechaingreso" date NOT NULL,
"cargosidcargo" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
BEGIN;
INSERT INTO "usuarios" VALUES ('1', 'Edwin Gonzalez', '30', '2021-05-09', '1');
INSERT INTO "usuarios" VALUES ('2', 'Ruben Angel', '30', '2021-05-04', '2');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "cargos_idcargo_seq" OWNED BY "cargos"."idcargo";
ALTER SEQUENCE "productos_idproducto_seq" OWNED BY "productos"."idproducto";
ALTER SEQUENCE "resgistromercancia_idregistro_seq" OWNED BY "resgistromercancia"."idregistro";
ALTER SEQUENCE "usuarios_idusuario_seq" OWNED BY "usuarios"."idusuario";

-- ----------------------------
-- Primary Key structure for table cargos
-- ----------------------------
ALTER TABLE "cargos" ADD PRIMARY KEY ("idcargo");

-- ----------------------------
-- Primary Key structure for table productos
-- ----------------------------
ALTER TABLE "productos" ADD PRIMARY KEY ("idproducto");

-- ----------------------------
-- Primary Key structure for table resgistromercancia
-- ----------------------------
ALTER TABLE "resgistromercancia" ADD PRIMARY KEY ("idregistro");

-- ----------------------------
-- Primary Key structure for table usuarios
-- ----------------------------
ALTER TABLE "usuarios" ADD PRIMARY KEY ("idusuario");

-- ----------------------------
-- Foreign Key structure for table "resgistromercancia"
-- ----------------------------
ALTER TABLE "resgistromercancia" ADD FOREIGN KEY ("usuarioidmodificador") REFERENCES "usuarios" ("idusuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "resgistromercancia" ADD FOREIGN KEY ("productoidproducto") REFERENCES "productos" ("idproducto") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "resgistromercancia" ADD FOREIGN KEY ("usuarioidregistrador") REFERENCES "usuarios" ("idusuario") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "usuarios"
-- ----------------------------
ALTER TABLE "usuarios" ADD FOREIGN KEY ("cargosidcargo") REFERENCES "cargos" ("idcargo") ON DELETE NO ACTION ON UPDATE NO ACTION;
