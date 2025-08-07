# Prueba Técnica - Desarrollador Mobile

Esta aplicación fue desarrollada como parte de una prueba técnica para el puesto de **Desarrollador Mobile**.  
El proyecto implementa autenticación, listado y detalle de Pokémon utilizando **PokeAPI**, siguiendo buenas prácticas y principios de **Material Design**.

---

## 📱 Características

- **Inicio de sesión** con Firebase Authentication.
    - Usuario de prueba:
        - **Email:** `elegidocodes@gmail.com`
        - **Contraseña:** `123456`
    - También es posible registrarse con otro correo, siempre que la contraseña tenga **mínimo 6 caracteres**.
- **Listado paginado** de Pokémon con nombre e imagen (Paging Library).
- **Detalle** de cada Pokémon con características relevantes.
- **Cierre de sesión** desde la barra de acciones.
- Diseño basado en **Material Design**.

---

## 📂 Contenido del repositorio

En la carpeta raíz encontrarás:

- **`app-release.apk`** → versión lista para instalar y probar en un dispositivo Android.
- **`demo.mp4`** → video demostrativo del funcionamiento de la aplicación.

---

## 🛠️ Tecnologías y librerías utilizadas

- **Lenguaje:** Java
- **Arquitectura:** MVVM
- **Autenticación:** Firebase Authentication
- **Consumo de API:** Retrofit
- **Paginación:** Paging Library
- **Carga de imágenes:** Glide
- **Programación reactiva:** RxJava
- **Inyección de dependencias:** Dagger Hilt
- **Backend de datos:** PokeAPI (https://pokeapi.co/)

---

## 🚀 Ejecución del proyecto

1. Clonar este repositorio:
   ```bash
   git clone https://github.com/elegidocodes/macropay-pokemon
