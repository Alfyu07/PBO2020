    package com.tugas;

    public class User {
        protected String nama;
        protected String email;
        protected String idUser;
        protected String password;

        public User(String nama, String email, String idUser, String password) {
            this.nama = nama;
            this.email = email;
            this.idUser = idUser;
            this.password = password;
        }

        public boolean login(String idUser, String password){
            if(idUser.equals(this.idUser) && password.equals(this.password)){
                return true;
            }
            return false;
        }

        public void details(){
            System.out.println("Nama\t: " + nama);
            System.out.println("Email\t: " + email);
        }


        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdUser() {
            return idUser;
        }

        public void setIdUser(String idUser) {
            this.idUser = idUser;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }
