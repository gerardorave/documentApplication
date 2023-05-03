drop table if exists "ORDER_PRODUCT" CASCADE;
drop table if exists "SALE_POINT_ORDER" CASCADE;
drop table if exists "ORDER_SALE" CASCADE;
drop table if exists "SALE_POINT" CASCADE;
drop table if exists "TYPE_OF_ORDER" CASCADE;
drop table if exists "PRODUCT" CASCADE;
drop table if exists "COMMERCIAL_PARTNER" CASCADE;
drop sequence if exists order_product_id_seq;
drop sequence if exists order_sale_id_seq;
drop sequence if exists sale_point_id_seq;
drop sequence if exists type_of_order_id_seq;
drop sequence if exists product_id_seq;
drop sequence if exists commercial_partner_id_seq;

CREATE SEQUENCE  order_product_id_seq;
CREATE SEQUENCE  order_sale_id_seq;
CREATE SEQUENCE  sale_point_id_seq;
CREATE SEQUENCE  type_of_order_id_seq;
CREATE SEQUENCE  product_id_seq;
CREATE SEQUENCE  commercial_partner_id_seq;


CREATE TABLE "order_product" (
    "id" bigint not null DEFAULT nextval('order_product_id_seq'),
    "product_id" bigint NOT NULL, --change
    "order_sale_id" bigint NOT NULL,
    "quantity" bigint NOT NULL,
     primary key ("id")

);
CREATE TABLE "order_sale" (
    "id" bigint not null DEFAULT nextval('order_sale_id_seq'),
    "type_of_order_id" bigint NOT NULL, --change
    "start_date" timestamp without time zone NOT NULL
     DEFAULT (current_timestamp AT TIME ZONE 'UTC'),
     "end_date" timestamp without time zone DEFAULT NULL,
     "sale_point_id" bigint,
     primary key ("id")

);
CREATE TABLE "sale_point" (
    "id" bigint not null DEFAULT nextval('sale_point_id_seq'),
    "commercial_partner_id"  bigint NOT NULL, --change
    "name"  character varying(100) NOT NULL,
    "address"  character varying(100) NOT NULL,
    "city"  character varying(100) NOT NULL,
    "country"  character varying(100) NOT NULL,
     primary key ("id")

);
CREATE TABLE "type_of_order" (
    "id" bigint not null DEFAULT nextval('type_of_order_id_seq'),
    "type" character varying(100) NOT NULL, -- Client OR SUPPLIER
     primary key ("id")

);
create table  "product" (
    "id" bigint not null DEFAULT nextval('product_id_seq'),
    "name" character varying(100) NOT NULL,
    "description" character varying(100),
     primary key ("id")
);
create table  "commercial_partner" (
    "id" bigint not null DEFAULT nextval('commercial_partner_id_seq'),
    "name" character varying(100) NOT NULL,
    "description" character varying(100),
     primary key ("id")
);

alter table "order_product" add constraint "FKgwdq976r5nwtgf7smd9beq1ix" foreign key ("product_id") references "product";
alter table "order_product"  add constraint "FKgwdq976r5nwtgf7smd9beq1ik" foreign key ("order_sale_id") references "order_sale";
alter table "order_sale" add constraint "FKgwdq976r5nwtgf7smd9beq1ie" foreign key ("type_of_order_id") references "type_of_order";
alter table "order_sale" add constraint "FKgwdq976r5nwtgf7smd9beq1iy" foreign key ("sale_point_id") references "sale_point";
alter table "sale_point" add constraint "FKgwdq976r5nwtgf7smd9beq1ir" foreign key ("commercial_partner_id") references "commercial_partner";
