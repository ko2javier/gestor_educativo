package com.example.gestoreducativo;

public class Usuario {

    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String fechaNacimiento;   // String a propósito
    private String tipoUsuario;        // profesor | alumno

    // 🔹 Constructor vacío (OBLIGATORIO para Firebase)
    public Usuario() {
    }

    // 🔹 Constructor completo
    public Usuario(String nombre,
                   String apellidos,
                   String email,
                   String password,
                   String fechaNacimiento,
                   String tipoUsuario) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsuario = tipoUsuario;
    }

    // ===== GETTERS & SETTERS =====

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}


