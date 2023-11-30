DROP TABLE IF EXISTS "public"."Roi2Source" CASCADE;

CREATE TABLE "public"."Roi2Source"
(
	id bigserial NOT NULL,
	"desc" varchar(500) NULL,
	"sourceId" BIGINT NOT NULL,
	"fileId" BIGINT NOT NULL, -- plik roi w Fedora
	"dateFrom" DATE NULL,
	"dateTo" DATE NULL,
	"isActive" boolean NOT NULL DEFAULT TRUE,
	"type" VARCHAR(16) NOT NULL,
	"userId" VARCHAR(255) NULL,
    "realm" varchar(500) NOT NULL,
	"createdAt" timestamp with time zone NOT NULL,
	"modifiedAt" timestamp with time zone NOT NULL
);

ALTER TABLE "public"."Roi2Source" ADD CONSTRAINT "PK_Roi2Source"	PRIMARY KEY (id);