import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { CursoService } from './curso/curso.service';
import { Public } from './auth/decorators/public.decorator';

@Controller() // Usamos solo el prefijo global 'api' definido en main.ts
export class AppController {
  constructor(
    private readonly appService: AppService,
    private readonly cursoService: CursoService, // inyectamos CursoService
  ) {}

  // Endpoint principal de prueba
  @Public()
  @Get('health')
  healthCheck() {
    return {
      status: 'ok',
      timestamp: new Date().toISOString(),
    };
  }

  // Endpoint temporal para probar la conexión con MongoDB y listar cursos
  @Public()
  @Get('test-curso')
  async testCurso() {
    const cursos = await this.cursoService.findAll();
    return cursos;
  }

  // Endpoint de ejemplo para el AppService
  @Public()
  @Get()
  getHello(): string {
    return this.appService.getHello();
  }
}
