# Test Software Developer

FACTURAS
GET /api/facturas/persona/{idPersona}
POST /api/facturas/persona → Crea una factura.
Body:
{
"idPersona": 1,
"fecha": "2025-11-20",
"monto": 789.99
}

DIRECTORIO
GET /api/directorio → Consulta el listado paginado del directorio.

GET /api/directorio/{identificacion} → Consulta un registro del directorio por identificacion.

POST /api/directorio.
Body:
{
"nombre": "EJEMPLO",
"apellidoPaterno": "EJEMPLO",
"apellidoMaterno": "EJEMPLO",
"identificacion": "DFL123442"
}
DELETE /api/directorio/{identificacion}.

# GetechnologiesMx
[![Build and Push Docker Image](https://github.com/DanielFlores97/GM/actions/workflows/docker-build.yml/badge.svg)](https://github.com/DanielFlores97/GM/actions/workflows/docker-build.yml)

Imagen

docker pull danielgod/backend:latest

Este proyecto es un backend en Java 21 con Spring Boot.
