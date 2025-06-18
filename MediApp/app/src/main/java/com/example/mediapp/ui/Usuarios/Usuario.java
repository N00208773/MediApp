package com.example.mediapp.ui.Usuarios;

public class Usuario {
    int id_usuarios;
    int id_cargos;
    String user_usuario;
    String pass_usuario;
    String nom_usuario;
    String apePa_usuario;
    String apeMa_usuario;
    String email_usuario;
    String cel_usuario;
    int edad_usuario;
    String fc_re_usuario;
    int est_usuario;
    String nom_cargo; // Nuevo campo para el nombre del cargo

    public Usuario(int id_usuarios, int id_cargos, String user_usuario, String pass_usuario,
                   String nom_usuario, String apePa_usuario, String apeMa_usuario,
                   String email_usuario, String cel_usuario, int edad_usuario,
                   String fc_re_usuario, int est_usuario, String nom_cargo) {
        this.id_usuarios = id_usuarios;
        this.id_cargos = id_cargos;
        this.user_usuario = user_usuario;
        this.pass_usuario = pass_usuario;
        this.nom_usuario = nom_usuario;
        this.apePa_usuario = apePa_usuario;
        this.apeMa_usuario = apeMa_usuario;
        this.email_usuario = email_usuario;
        this.cel_usuario = cel_usuario;
        this.edad_usuario = edad_usuario;
        this.fc_re_usuario = fc_re_usuario;
        this.est_usuario = est_usuario;
        this.nom_cargo = nom_cargo;
    }

    // Getters y Setters
    public int getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public int getId_cargos() {
        return id_cargos;
    }

    public void setId_cargos(int id_cargos) {
        this.id_cargos = id_cargos;
    }

    public String getUser_usuario() {
        return user_usuario;
    }

    public void setUser_usuario(String user_usuario) {
        this.user_usuario = user_usuario;
    }

    public String getPass_usuario() {
        return pass_usuario;
    }

    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getApePa_usuario() {
        return apePa_usuario;
    }

    public void setApePa_usuario(String apePa_usuario) {
        this.apePa_usuario = apePa_usuario;
    }

    public String getApeMa_usuario() {
        return apeMa_usuario;
    }

    public void setApeMa_usuario(String apeMa_usuario) {
        this.apeMa_usuario = apeMa_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getCel_usuario() {
        return cel_usuario;
    }

    public void setCel_usuario(String cel_usuario) {
        this.cel_usuario = cel_usuario;
    }

    public int getEdad_usuario() {
        return edad_usuario;
    }

    public void setEdad_usuario(int edad_usuario) {
        this.edad_usuario = edad_usuario;
    }

    public String getFc_re_usuario() {
        return fc_re_usuario;
    }

    public void setFc_re_usuario(String fc_re_usuario) {
        this.fc_re_usuario = fc_re_usuario;
    }

    public int getEst_usuario() {
        return est_usuario;
    }

    public void setEst_usuario(int est_usuario) {
        this.est_usuario = est_usuario;
    }

    public String getNom_cargo() {
        return nom_cargo;
    }

    public void setNom_cargo(String nom_cargo) {
        this.nom_cargo = nom_cargo;
    }
}