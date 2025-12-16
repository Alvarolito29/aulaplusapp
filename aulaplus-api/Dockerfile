# Build stage
FROM node:20-alpine AS builder

WORKDIR /app

# Copiar package files
COPY package*.json ./

# Instalar dependencias
# Agregamos --ignore-scripts para evitar que intente compilar sharp aquí si falla
RUN npm ci

# Copiar código fuente
COPY . .

# Build de la aplicación
RUN npm run build

# Production stage
FROM node:20-alpine

WORKDIR /app

# Instalar dependencias necesarias para sharp en Alpine
# vips-dev y build-base son pesados, intentaremos usar binarios precompilados primero
RUN apk add --no-cache \
    libc6-compat \
    python3 \
    make \
    g++ 

# Copiar package files
COPY package*.json ./

# TRUCO: Forzar plataforma linuxmusl para sharp en Alpine para evitar compilación
# Esto descarga el binario correcto en lugar de intentar compilarlo
RUN npm install --cpu=x64 --os=linux --libc=musl sharp
RUN npm ci --only=production --ignore-scripts

# Copiar build desde builder stage
COPY --from=builder /app/dist ./dist

# Crear directorios para uploads con permisos correctos
RUN mkdir -p uploads/thumbnails && \
    chmod -R 777 uploads

# Exponer puerto
EXPOSE 3008

# Variables de entorno por defecto
ENV NODE_ENV=production
ENV PORT=3008

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s \
  CMD node -e "require('http').get('http://localhost:3008/api/health', (r) => {process.exit(r.statusCode === 200 ? 0 : 1)})"

# Iniciar aplicación
CMD ["node", "dist/main"]
