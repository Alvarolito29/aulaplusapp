# --- Etapa 1: Builder ---
FROM node:20-alpine AS builder

WORKDIR /app

# Copiar archivos de dependencias
COPY package*.json ./

# Instalar TODAS las dependencias (incluyendo dev y herramientas de compilación implícitas)
# Esto suele funcionar mejor aquí porque no está restringido a --only=production
RUN npm ci

# Copiar el código fuente
COPY . .

# Construir la aplicación (NestJS build)
RUN npm run build

# Limpiar dependencias de desarrollo para dejar solo las necesarias para producción
# Esto prepara la carpeta node_modules final
RUN npm prune --production

# --- Etapa 2: Producción ---
FROM node:20-alpine

WORKDIR /app

# Instalar dependencias del sistema necesarias para runtime (Sharp y otros)
# Mantenemos las que usaba el profesor para asegurar compatibilidad
RUN apk add --no-cache \
    libc6-compat \
    vips-dev \
    fftw-dev \
    build-base \
    python3 \
    && rm -rf /var/cache/apk/*

# Copiar archivos de configuración
COPY package*.json ./

# --- EL CAMBIO CLAVE ---
# En lugar de ejecutar 'npm ci --only=production' (que falla compilando Sharp),
# copiamos los node_modules YA listos y compilados desde la etapa builder.
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/dist ./dist

# Crear directorios para uploads con permisos
RUN mkdir -p uploads/thumbnails && \
    chmod -R 777 uploads

# Exponer puerto
EXPOSE 3008

# Variables de entorno
ENV NODE_ENV=production
ENV PORT=3008

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s \
  CMD node -e "require('http').get('http://localhost:3008/api/health', (r) => {process.exit(r.statusCode === 200 ? 0 : 1)})"

# Iniciar la aplicación
CMD ["node", "dist/main"]