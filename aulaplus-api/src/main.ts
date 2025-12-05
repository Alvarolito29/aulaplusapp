import { NestFactory } from '@nestjs/core';
import { FastifyAdapter, NestFastifyApplication } from '@nestjs/platform-fastify';
import { ValidationPipe } from '@nestjs/common';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';
import { ConfigService } from '@nestjs/config';
import * as multipart from '@fastify/multipart';
import * as fastifyStatic from '@fastify/static';
import { join } from 'path';

async function bootstrap() {
  const app = await NestFactory.create<NestFastifyApplication>(
    AppModule,
    // Desactivamos el logger propio de Fastify para no mostrar JSON de requests
    new FastifyAdapter({ logger: false }),
  );

  // Soporte para archivos (multipart)
  await app.register(multipart, {
    limits: { fileSize: 5 * 1024 * 1024 },
  });

  // Rutas estáticas
  const staticRoot = join(process.cwd(), 'uploads');

  await app.register(fastifyStatic, {
    root: join(__dirname, '..', 'uploads'),
    prefix: '/uploads/',
    decorateReply: false,
  });

  await app.register(fastifyStatic, {
    root: staticRoot,
    prefix: '/api/uploads/',
    decorateReply: false,
  });

  app.enableCors();

  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true,
      forbidNonWhitelisted: true,
      transform: true,
    }),
  );

  // Prefijo global
  app.setGlobalPrefix('api');

  const configService = app.get(ConfigService);

  // --------------------
  //    SWAGGER
  // --------------------
  const config = new DocumentBuilder()
    .setTitle(process.env.npm_package_name || 'API')
    .setDescription('Documentación completa de la API REST')
    .setVersion('1.0')
    .addBearerAuth(
      {
        type: 'http',
        scheme: 'bearer',
        bearerFormat: 'JWT',
        name: 'JWT',
        description: 'Ingresa tu token JWT',
        in: 'header',
      },
      'JWT-auth',
    )
    .addTag('Autenticación')
    .addTag('Upload')
    .build();

  // 🔥 FALTA EN TU CÓDIGO ORIGINAL → Crear el documento Swagger
  const document = SwaggerModule.createDocument(app, config);

  // 🔥 Configuración de Swagger compatible con Render
  SwaggerModule.setup('api/docs', app, document, {
    customSiteTitle: 'API Documentation',
  });

  // --------------------
  //    INICIAR APP
  // --------------------
  const port = process.env.PORT || configService.get<number>('PORT') || 3000;
  await app.listen(port, '0.0.0.0');

  console.log(`🖼️ Imágenes: http://localhost:${port}/uploads/<filename> o /api/uploads/<filename>`);
  console.log('\n🚀 API: http://localhost:' + port + '/api');
  console.log('📚 Swagger: http://localhost:' + port + '/api/docs\n');
}

bootstrap();
