# AulaPlus App

## Descripción
AulaPlus App es una aplicación móvil diseñada para mejorar la gestión académica escolar, permitiendo a estudiantes y profesores acceder a horarios, perfiles y herramientas de gestión en tiempo real.

## Integrantes del Equipo
*   **Integrante 1:** Alvaro Rivera
*   **Integrante 2:** Joaquin Tapia

## Funcionalidades Principales
1.  **Gestión de Usuarios:** Registro, Inicio de Sesión y Perfil de Usuario.
2.  **Roles Diferenciados:** Estudiante, Profesor, Admin, Apoderado.
3.  **Horario Escolar:** Visualización de clases, salas y profesores en tiempo real.
4.  **Recursos Nativos:** Uso de Cámara y Galería para foto de perfil.
5.  **Frase del Día:** Integración con API externa (Quotable) para mostrar frases inspiradoras en el Login.
6.  **Recuperación de Contraseña:** Flujo simulado para recuperar acceso.

## Tecnologías Utilizadas
*   **Android:** Kotlin, Jetpack Compose, MVVM, Retrofit, Coil, DataStore.
*   **Backend:** NestJS, TypeScript (Microservicios).
*   **API Externa:** Quotable API (https://api.quotable.io/).

## Endpoints Utilizados
### Propios (Backend NestJS)
*   `POST /auth/login`: Iniciar sesión.
*   `POST /auth/register`: Registrar nuevo usuario.
*   `GET /schedule`: Obtener horario de clases.

### Externos
*   `GET https://api.quotable.io/random`: Obtener frase aleatoria.

## Instrucciones de Ejecución
1.  **Backend:**
    *   Navegar a la carpeta `aulaplus-api`.
    *   Ejecutar `npm install`.
    *   Ejecutar `npm run start:dev`.
    *   El servidor correrá en `http://localhost:3015`.

2.  **App Móvil:**
    *   Abrir el proyecto en Android Studio.
    *   Verificar la IP en `RetrofitClient.kt` (si usas emulador: `10.0.2.2`, si es físico: tu IP local).
    *   Ejecutar en un dispositivo o emulador (API 26+).

## APK Firmado
El archivo APK firmado (`app-release.apk`) se encuentra en la carpeta `app/release/`.
La llave `upload-keystore.jks` se encuentra en la raíz del módulo `app`.
**Clave del Keystore:** `123456` (Ejemplo académico).


