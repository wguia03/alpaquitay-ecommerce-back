## Ecommerce API
**Curso:**  Taller de Construcción de Software Web  
**Ciclo:** 2025-0  
**Profesor:** Sarmiento Calisaya, Edgar

### Integrantes ###
- Guia Muñoz, Wilfredo
- Ccora Quispe, Holiver Jhunior
- Collachagua Poma, Airton
- Escudero Principe, Álvaro
- Balceda Delgado, Mariana Alejandra
- Sifuentes Castillo, Luis
- Cumpa Pareja, Santiago
- Tupac Agüero, Kevin

### Propósito ###
Desarrollar un backend escalable y seguro para un ecommerce dirigido al sector de artesanos, facilitando la gestión de productos, pedidos y pagos, optimizando la visibilidad y comercialización de sus creaciones mediante una plataforma eficiente y accesible.

### Vista General de Arquitectura ###
![DDD-Diagram](./diagrams/DDD_Diagram.png)


### Prácticas de Desarrollo ###

####  Construcción Automática ####
- La integración y despliegue continuo para Alpakitay se implementa utilizando Jenkins como herramienta de automatización y Maven como gestor de construcción del proyecto.
 <div align="center">
      <img src="https://github.com/wguia03/alpaquitay-ecommerce-back/blob/dev/Jenkins/An%C3%A1lisis%20Est%C3%A1tico.png" alt="construccion" width="600">
   </div>

####  Análisis Estático ####
El análisis estático fue realizado para garantizar la calidad, seguridad y mantenibilidad del código del proyecto. Esto se implementó utilizando herramientas especializadas que nos ayudaron a identificar defectos en las etapas iniciales del desarrollo.
- Herramientas Utilizadas:
  - SonarQube: Para generar informes detallados sobre métricas de calidad y posibles errores en el código.
  - Jenkins: Integrado con SonarQube para automatizar el análisis estático dentro del pipeline de CI/CD.

####  Pruebas Unitarias ####
- Las pruebas unitarias garantizan que cada componente individual del sistema funcione correctamente de manera aislada.
####  Pruebas de APIs ####
