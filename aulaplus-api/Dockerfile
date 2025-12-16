# Build stage
FROM node:20-alpine AS builder

WORKDIR /app

# Copiar package files
COPY package*.json ./

# Instalar dependencias
RUN npm ci

# Copiar código fuente
COPY . .

# Build de la aplicación
RUN npm run build

# Production stage
FROM node:20-alpine

WORKDIR /app

# Instalar dependencias necesarias para sharp en Alpine
# Requerimos dependencias nativas completas para evitar problemas de compilación
RUN apk add --no-cache \
    libc6-compat \
    python3 \
    make \
    g++ \
    vips-dev \
    fftw-dev \
    build-base

# Copiar package files
COPY package*.json ./

# Instalar dependencias
# Eliminamos el truco de sharp precompilado y dejamos que npm ci maneje todo con ignore-scripts para devDeps
# Pero para prod, necesitamos que sharp se construya si es necesario o baje el binario correcto
# El error indica que npm ci --only=production falla compilando.
# Vamos a instalar sharp explícitamente para linux musl antes de npm ci
RUN npm install --arch=x64 --platform=linux --libc=musl sharp

# Ahora instalamos el resto, ignorando scripts para evitar re-compilaciones innecesarias de otros paquetes
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
