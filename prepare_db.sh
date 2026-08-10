#!/bin/bash

DB_SERVICE="postgres"
DB_NAME="calculator_db"
DB_USER="postgres"

echo "1. Levantando contenedores en segundo plano..."
docker compose up -d

echo "2. Esperando a que PostgreSQL responda..."
until docker compose exec $DB_SERVICE pg_isready -U $DB_USER -d $DB_NAME > /dev/null 2>&1; do
  echo "Esperando a la base de datos..."
  sleep 2
done

echo "3. Ejecutando script SQL para crear la tabla..."
docker compose exec -T $DB_SERVICE psql -U $DB_USER -d $DB_NAME <<EOF
CREATE TABLE IF NOT EXISTS operation (
    id SERIAL PRIMARY KEY,
    operand_a FLOAT NOT NULL,
    operand_b FLOAT NOT NULL,
    operator CHAR(1) NOT NULL,
    result FLOAT NOT NULL
);
EOF

echo "¡Completado! La tabla 'operation' se ha creado correctamente."